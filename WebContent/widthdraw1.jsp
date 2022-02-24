<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>

<%
	request.setCharacterEncoding("utf-8");
	
	String uid= request.getParameter("id");
	
	UserDAO dao =new UserDAO();
	
	if(dao.exists(uid)){
		out.print("NA");
		return ;
		}
	if(dao.delete(uid)) {
	out.print("OK");
	}
	else{
	out.print("ER");
	}
	
%>
