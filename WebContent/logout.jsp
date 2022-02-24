<%@ page contentType="text/html" pageEncoding="utf-8"%>
<%
	session.invalidate();
	out.println("로그아웃이 완료되었습니다.");
%>