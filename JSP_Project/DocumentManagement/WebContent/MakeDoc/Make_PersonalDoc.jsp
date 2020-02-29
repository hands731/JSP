<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function captureReturnKey(e) { 
	  if(e.keyCode==13 && e.srcElement.type != 'textarea') 
	  return false; 
}
</script>
<link rel="stylesheet" type="text/css" href="../menu.css">
<%@ include file="../title.jspf" %>
<%@ include file="../menu.jspf" %>
</head>
<body>
<%
	String temp = (String)request.getParameter("des1");
	if(temp==null)temp="";
%>
<div align="left">
<form action="/DocumentManagement/make_PersonalDoc.do" onkeydown="return captureReturnKey(event)">
문서 분류 : Personal <br>
받는 사람 : <input type="text" name = "des1" value="<%=temp%>"style="width:67.25%;"> <br>
       제목 : <input type="text" name = "title" style="width:70%;" > <br>
       내용 : <textarea name = "content" style="width:70%; height:400px; text-align:left" ></textarea> <br>
       첨부 : <input type="file"/> <br><br>
<input type="submit" value="등록" style="width:70px; height:30px;"> 
<input type="reset" value="취소" style="width:70px; height:30px;"> 
</form>
</div>
</body>
</html>