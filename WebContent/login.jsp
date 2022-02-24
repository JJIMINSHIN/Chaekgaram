<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="dao.*" %>
<%@ page import="util.*" %>
<%
	String jsonstr = null, uid = null,upass = null;
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
			if (name.equals("ps")) upass = value;
		}
		out.print(uid);
		out.print(upass);

	}
	

    UserDAO dao = new UserDAO();
    if (dao.login(uid,upass) == 1) 
        out.print("NE");
    
    else if (dao.login(uid,upass) == 2) 
        out.print("PE");
    
    else {
    	out.print("OK");
        session.setAttribute("id", uid);
        response.sendRedirect("community.html");
    	}
%>
