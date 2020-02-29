<%@page import="VO.DetailDocVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	DetailDocVO docVO = (DetailDocVO)request.getAttribute("doc");
	String title = docVO.getTitle();
	String group = docVO.getGroupName();
	String sender = docVO.getSender();
	String ctime = docVO.getCtime();
	String content = docVO.getContent();
	int docId = docVO.getDocId();
%>
<script>
	function receive(){
		form = document.getElementById("form");
		form.action = "./MakeDoc/Make_GroupDoc.jsp";
		form.submit();
	}
	
	
</script>

<div align="center">
<form action="" id="form" style="border-color: skyblue;" >


       제목 <input type="text" style="width:700px;" readonly value=<%=title %>> <br>
       그룹 <input type="text" style="width:300px;" readonly value=<%=group %>>
작성자 <input type="text" style="width:300px;" readonly value=<%=sender %>> <br>
첨부 파일 <input type="text" style="width:385px;" readonly value="예시 다운로드 링크">
작성일 <input type="text" style="width:195px;" readonly value=<%=ctime %>><br>  
<hr style="border-color:skyblue">
<textarea style="width:720px; height:400px; text-align:left" readonly><%=content %></textarea> <br>

<input type="text" value="<%=docId %>" style="display:none;" name="parentId"/>
<input type="text" value="<%=group %>" style="display:none;" name="parentGroupName"/>


<input type="button" value="답글" style="width:70px; height:30px;" onclick="receive();"> 
<a href='/DocumentManagement/viewGroupList.do?groupName=<%=group%>'><input type="button" value="취소" style="width:70px; height:30px;" ></a> 
</form>
</div>
</body>
</html>