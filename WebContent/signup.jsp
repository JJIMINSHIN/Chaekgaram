<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="dao.*" %>
<%@ page import="util.*" %>
<%
	String jsonstr = null, uid = null;
	request.setCharacterEncoding("utf-8");
	
	ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
	List items = sfu.parseRequest(request);
	Iterator iter = items.iterator();
	
	while(iter.hasNext()) {
		FileItem item = (FileItem) iter.next();
		String name = item.getFieldName();
		if(item.isFormField()) {
			String value = item.getString("utf-8");
			if (name.equals("jsonstr")) jsonstr = value;
			if (name.equals("id")) uid = value;
			}
		}
	
	UserDAO dao = new UserDAO();
    if (dao.exists(uid)) {
        out.print("EX");
        return;
    	}
    
	if (dao.insert(uid, jsonstr) == true) {
	    out.print("OK");
		}
	
	else {
	    out.print("ER");
		}
%>