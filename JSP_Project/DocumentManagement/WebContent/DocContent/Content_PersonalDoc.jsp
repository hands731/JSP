<%@page import="VO.DetailDocVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
</head>
<body>
<%
	DetailDocVO docVO = (DetailDocVO)request.getAttribute("doc");
	String title = docVO.getTitle();
	String sender = docVO.getSender();
	String ctime = docVO.getCtime();
	String content = docVO.getContent();
%>
<div align="center">
<form action="" style="border-color: skyblue;">
       제목 <input type="text" style="width:400px;" readonly value=<%=title %>>
보낸 사람 <input type="text" style="width:200px;" readonly value=<%=sender %>> <br>
첨부 파일 <input type="text" style="width:385px;" readonly value="예시 다운로드 링크">
작성일 <input type="text" style="width:195px;" readonly value=<%=ctime %>><br>  
<hr style="border-color:skyblue">
<textarea style="width:720px; height:400px; text-align:left" readonly><%=content %></textarea> <br>
<input type="button" value="답장" onclick="location='/DocumentManagement/MakeDoc/Make_PersonalDoc.jsp?des1=<%=sender%>'"style="width:70px; height:30px;"> 
<input type="button" value="삭제" style="width:70px; height:30px;"> 
</form>
</div>
</body>
</html>