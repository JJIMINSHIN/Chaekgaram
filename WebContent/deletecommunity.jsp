<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="dao.*" %>
<%@ page import="util.*" %>
<%

	String jsonstr = null, uid = null,no = null;
	int No = 0;
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
			if (name.equals("no")) {
				no = value;
				No = Integer.parseInt(no);
			}
		}	
	}
	
	
	contentDAO dao = new contentDAO();
	if (dao.delete(No)) {
		out.print("OK"); 
		}
	else {
		out.print("ER");
		}
%>