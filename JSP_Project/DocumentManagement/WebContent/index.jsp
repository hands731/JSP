<%@page import="java.util.ArrayList"%>
<%@page import="VO.SimplePersonalListVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="menu.css">
</head>

<body>
<%
	System.out.println("33");
%>
<%@ include file="title.jspf" %>
<%
	System.out.println("33");
%>
<%@ include file="menu.jspf" %>
<%
	System.out.println("33");
%>
<div id="content">
<center>
<%
	System.out.println("33");
%>
<%if(request.getAttribute("page")!=null) %>
<jsp:include page='<%=(String)request.getAttribute("page") %>'></jsp:include>
<%
	System.out.println("33");
%>
</center>
</div>
</body>
</html>