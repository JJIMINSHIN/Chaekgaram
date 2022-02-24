<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.*" %>
<%

	request.setCharacterEncoding("UTF-8");
	
	String nick = request.getParameter("jsonstr");
	String ps = request.getParameter("id");
	String id = (String)session.getAttribute("uid");
	
	UserDAO dao = new UserDAO();
   

%>