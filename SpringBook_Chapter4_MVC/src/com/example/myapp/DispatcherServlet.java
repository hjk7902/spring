package com.example.myapp;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.myapp.controller.CommandHandler;
import com.example.myapp.controller.NullHandler;

// 이 서블릿은 URL에 특정 문자열을 포함하면 실행되도록 XML에 설정
// url-mapping이 /*.do이면 
// URL이 .do 로 끝나면 항상 디스패처서블릿이 실행된다.
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	// 이 서블릿은 web.xml 파일에 초기화 파라미터가 설정되 있고
	// 서블릿이 로드될 때 실행되면서, 맵에 커맨드와 커맨드객체 쌍을 저장
	public void init() throws ServletException {
		// XML 파일에 설정되어 있는 서블릿 초기화 파라미터를 읽는다.
		// command URL과 커맨드 핸들러 클래스 이름을 설정한 파일의 경로
		String configFile = getInitParameter("configFile");
		
		// 프로퍼티 객채 생성
		Properties prop = new Properties();
		
		// 설정 파일의 절대경로를 찾는다.
		String configFilePath = getServletContext().getRealPath(configFile);
		
		// 파일에서 스트림을 통해 프로퍼티를 읽어들인다.
		try(FileReader inStream = new FileReader(configFilePath)) {
			prop.load(inStream); //입력스트림에서 데이터를 읽어 프로퍼티로 설정
		}catch(IOException e) {
			throw new ServletException(e);
		}

		// 프로퍼티에서 키 집합을 나열객체로 반환
		Iterator<?> keys = prop.keySet().iterator();
		while(keys.hasNext()) {	//키의 개수만큼 실행
			//설정 파일 예 /hello.do=mvc.command.hello.HelloHandler
			String command = (String)keys.next(); //커맨드(URL)
			String handlerClassName = prop.getProperty(command); //클래스명
			try {
				// 클래스 이름으로 객체를 생성한다.
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler)handlerClass.getDeclaredConstructor().newInstance();

				// 커맨드와 커맨드객체를 맵에 키, 벨류 쌍으로 저장
				commandHandlerMap.put(command, handlerInstance);
			} catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processServlet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processServlet(request, response);
	}

	private void processServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 인코딩설정
		
		// 요청 URI를 반환 /WebPrj/hello.do <- /WebPrj는 컨텍스트 이름
		String command = request.getRequestURI();
		
		// URI에서 컨텍스트 이름을 제거한다.
		if(command.indexOf(request.getContextPath())==0) {
			command = command.substring(request.getContextPath().length());
		}
				
		// URI를 이용해서 맵으로부터 커맨드핸들러 객체를 찾는다.
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler==null) {
			handler = new NullHandler();
		}

		String viewPage = null;
		try {
			// process()는 명령을 처리하고 뷰페이지를 반환한다.
			viewPage = handler.process(request, response);
			if((viewPage != null) && (viewPage.indexOf("redirect:")==0)) {
				// 뷰 이름 앞에 redirect:가 붙으면 sendRedirect 한다.
				// 뷰가 redirect:/list.do이면 /list.do로 sendRedirect한다.
				viewPage = viewPage.substring(9); // 9는 "redirect:"의 길이
				response.sendRedirect(request.getContextPath() + viewPage);
				return;
			}
		} catch(Throwable e) {
			throw new ServletException(e);
		}
		
		// 뷰로 포워드 시킨다.
		if(viewPage != null) {
			viewPage = "/WEB-INF/views/" + viewPage;
		}else {
			viewPage = "index.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(viewPage);
		disp.forward(request, response);
	}
}
