<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	

	String id = request.getParameter("id");
	
	UserDAO dao = new UserDAO();
	String code = dao.usrDel(id);
	out.print(code);
   
    session.invalidate(); 

%>