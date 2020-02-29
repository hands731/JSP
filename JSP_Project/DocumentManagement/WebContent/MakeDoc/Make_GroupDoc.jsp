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
<div align="left">
<form action="/DocumentManagement/make_GroupDoc.do" onkeydown="return captureReturnKey(event)">
문서 분류 : Group <br>
그룹 선택 : <select name="groupName">
<!--자바 문법에서 value값 for문등을 이용하여 dao에서 값 가져오기. -->
<%
if(request.getParameter("parentId")!=null){
%>
	<option selected="selected" value="<%=request.getParameter("parentGroupName")%>"><%=request.getParameter("parentGroupName")%></option>
<%
}else{
if (groupList != null)
	for (String groupName : groupList) {
		%>
			<option value="<%=groupName%>"><%=groupName%></option>
<%}} %>
		</select> <br>
       제목 : <input type="text" name = "title" style="width:70%;" > <br>
       내용 : <textarea name = "content" style="width:70%; height:400px; text-align:left" ></textarea> <br>
       첨부 : <input type="file"/> <br><br>
<input type="text" name="parentId" value="<%=request.getParameter("parentId")%>" style="display:none;"/>
<input type="submit" value="등록" style="width:70px; height:30px;"> 
<input type="reset" value="취소" style="width:70px; height:30px;"> 
</form>
</div>
</body>
</html>