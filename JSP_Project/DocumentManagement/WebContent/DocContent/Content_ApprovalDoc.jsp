<%@page import="VO.UserVO"%>
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
	UserVO userVO = (UserVO)session.getAttribute("UserInfo");
	DetailDocVO docVO = (DetailDocVO)request.getAttribute("doc");
	String title = docVO.getTitle();
	String sender = docVO.getSender();
	String approve_1 = docVO.getDes1();
	String approve_2 = docVO.getDes2();
	String approve_3 = docVO.getDes3();
	String ctime = docVO.getCtime();
	String content = docVO.getContent();
%>
<div align="center">
<form action="" style="border-color: skyblue;">
       제목 <input type="text" style="width:400px;" readonly value=<%=title %>>
작성자 <input type="text" style="width:200px;" readonly value=<%=sender %>> <br>
결제자 1<input type="text" style="width:200px;" readonly value=<%=approve_1 %>> <br>
결제자 2<input type="text" style="width:200px;" readonly value=<%=approve_2 %>> <br>
결제자 3<input type="text" style="width:200px;" readonly value=<%=approve_3 %>> <br>
첨부 파일 <input type="text" style="width:385px;" readonly value="예시 다운로드 링크">
작성일 <input type="text" style="width:195px;" readonly value=<%=ctime %>><br>  
<hr style="border-color:skyblue">
<textarea style="width:720px; height:400px; text-align:left" readonly><%=content %></textarea> <br>
<input type="button" value="결제" onclick="location='/DocumentManagement/acceptApprovalDoc.do?userid=<%=userVO.getUserid()%>&docid=<%=docVO.getDocId()%>&sender=<%=sender%>'"style="width:70px; height:30px;">
<input type="button" value="보류" onclick="location='/DocumentManagement/holdApprovalDoc.do?userid=<%=userVO.getUserid()%>&docid=<%=docVO.getDocId()%>'"style="width:70px; height:30px;">  
<input type="button" value="반송" onclick="location='/DocumentManagement/returnApprovalDoc.do?userid=<%=userVO.getUserid()%>&docid=<%=docVO.getDocId()%>'"style="width:70px; height:30px;"> 
<input type="button" value="삭제" style="width:70px; height:30px;"> 

</form>
</div>
</body>
</html>