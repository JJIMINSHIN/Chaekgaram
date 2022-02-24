<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>

<%
	String str=(new contentDAO()).getGroup("6");
	out.print(str);
	
%> 