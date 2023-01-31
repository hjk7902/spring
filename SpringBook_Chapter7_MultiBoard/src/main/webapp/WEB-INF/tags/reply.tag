<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ tag body-content="empty"%>
<%@ attribute name="replynum" type="java.lang.Integer"%>
<%@ attribute name="replystep" type="java.lang.Integer"%>
<%
if(replynum==0){ 
    out.print(""); 	//메인 글임을 나타냄
} else {
    for(int i=0; i<replystep; i++) {
        out.print("&nbsp;");  //공백
    }
    out.print("└");	//답변글임을 나타냄 
}//end if
%>