<%@page import="VO.UserVO"%>
<%@page import="VO.SimpleApprovalListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@include file="./Approval_SendListStyle.jspf" %>

</head>
<body>

<form style="margin:10px;">
<h4>발신문서함</h4>
<input type="checkbox"/> <span style="font-size:12px">읽지 않은 메일만 보기</span>
<select style="width:110px;margin-left:250px;height:22px;">
	<option>제목</option>
	<option>받는 사람</option>
	<option>내용</option>
</select>
<input type="text" style="width:150px"/>
<input type="button" value="검색" style="width:70px;text-align:center;"/>
</form>

<table class="listTable" border="3px" bordercolor="#77b2ab" rules="rows">

<!-- 리스트 메뉴 -->
<tr class="titlemenu">
	<td class="docId">문서 번호</td>    
	<td class="title">제목</td>    
	<td class="des1">결제자</td> 
	<td class="state">결제 상태</td>     
	<td class="ctime">날짜</td>
	<td class="etc">비고</td>
</tr>

<!-- 문서 리스트 -->
<%
UserVO userVO = (UserVO)session.getAttribute("UserInfo");
String num = null;
	List<SimpleApprovalListVO> docList = (List<SimpleApprovalListVO>)request.getAttribute("docList");
	if(docList != null)
	for(SimpleApprovalListVO doc : docList){
%>

<tr class="docList">
	<td class="docId"><%=doc.getDocId() %></td>    
	<td class="title"><a href="/DocumentManagement/viewApprovalDoc.do?docid=<%=doc.getDocId()%>&userid=<%=userVO.getUserid()%>"><%=doc.getTitle() %></a></td>    
	<td class="des1"><%=doc.getDes1()+", "+doc.getDes2()+", "+doc.getDes3() %></td> 
	<td class="state"><%= doc.getState() %></td>         
	<td class="ctime"><%=doc.getCtime() %></td>
	<td class="etc"><a href="/DocumentManagement/deleteDoc.do?docid=<%=doc.getDocId()%>&listid=sendList&userid=<%=userVO.getUserid()%>&num=2">지우기</a></td>
</tr>
<%} %>
<!-- 문서 리스트 끝 -->
</table>

</body>
</html>