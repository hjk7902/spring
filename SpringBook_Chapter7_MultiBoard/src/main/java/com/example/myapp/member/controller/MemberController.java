package com.example.myapp.member.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.myapp.member.model.Member;
import com.example.myapp.member.service.IMemberService;

@Controller
public class MemberController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IMemberService memberService;

	@RequestMapping(value="/member/insert", method=RequestMethod.GET)
	public String insertMember(HttpSession session) {
		String csrfToken = UUID.randomUUID().toString();
        session.setAttribute("csrfToken", csrfToken);
		logger.info("/member/insert, GET", csrfToken);
		return "member/form";
	}
	
	@RequestMapping(value="/member/insert", method=RequestMethod.POST)
	public String insertMember(Member member, String csrfToken, HttpSession session, Model model) {
		if(csrfToken==null || "".equals(csrfToken)) {
			throw new RuntimeException("CSRF 토큰이 없습니다.");
		}else if(!csrfToken.equals(session.getAttribute("csrfToken"))) {
			throw new RuntimeException("잘 못된 접근이 감지되었습니다.");
		}
		try {
			if(!member.getPassword().equals(member.getPassword2())) {
				model.addAttribute("member", member);
				model.addAttribute("message", "MEMBER_PW_RE");
				return "member/form";
			}
			memberService.insertMember(member);
		}catch(DuplicateKeyException e) {
			member.setUserid(null);
			model.addAttribute("member", member);
			model.addAttribute("message", "ID_ALREADY_EXIST");
			return "member/form";
		}
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(String userid, String password, HttpSession session, Model model) {
		Member member = memberService.selectMember(userid);
		if(member != null) {
			logger.info(member.toString());
			String dbPassword = member.getPassword();
			if(dbPassword.equals(password)) { // 비밀번호 일치
				session.setMaxInactiveInterval(600); // 세션 타임아웃 10분
				session.setAttribute("userid", userid);
				session.setAttribute("name", member.getName());
				session.setAttribute("email", member.getEmail());
			}else {	// 비밀번호가 다름
				model.addAttribute("message", "WRONG_PASSWORD");
			}
		}else { // 아이디가 없음
			session.invalidate();
			model.addAttribute("message", "USER_NOT_FOUND");
		}
		return "member/login";
	}
	
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.GET)
	public String updateMember(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "UPDATE_USER_INFO");
			return "member/update";
		}else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}
	
	@RequestMapping(value="/member/update", method=RequestMethod.POST)
	public String updateMember(Member member, HttpSession session, Model model) {
		try{
			memberService.updateMember(member);
			model.addAttribute("message", "UPDATED_MEMBER_INFO");
			model.addAttribute("member", member);
			session.setAttribute("email", member.getEmail());
			return "member/login";
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "member/error";
		}
	}
	
	@RequestMapping(value="/member/delete", method=RequestMethod.GET)
	public String deleteMember(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null && !userid.equals("")) {
			Member member = memberService.selectMember(userid);
			model.addAttribute("member", member);
			model.addAttribute("message", "MEMBER_PW_RE");
			return "member/delete";
		}else {
			model.addAttribute("message", "NOT_LOGIN_USER");
			return "member/login";
		}
	}
	
	@RequestMapping(value="/member/delete", method=RequestMethod.POST)
	public String deleteMember(String password, HttpSession session, Model model) {
		try {
			Member member = new Member();
			member.setUserid((String)session.getAttribute("userid"));
			String dbpw = memberService.getPassword(member.getUserid());
			if(password != null && password.equals(dbpw)) {
				member.setPassword(password);
				memberService.deleteMember(member) ;
				session.invalidate(); // 회원정보가 삭제되면 로그아웃 처리
				return "member/login";
			}else {
				model.addAttribute("message", "WRONG_PASSWORD");
				return "member/delete";
			}
		}catch(Exception e){
			model.addAttribute("message", "DELETE_FAIL");
			e.printStackTrace();
			return "member/delete";
		}
	}
}