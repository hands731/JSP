<%@page import="VO.UserVO"%>
<%@page import="VO.SimpleBinListVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../BasicUI/UIStyle.css">
<meta charset="UTF-8">
<title>휴지통</title>
<%@include file="./Bin_ListStyle.jspf" %>
</head>
<body>

<form style="margin:10px;">
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
	<td class="type">문서 타입</td> 
	<td class="title">제목</td>    
	<td class="sender">작성자</td>     
	<td class="ctime">날짜</td>
	<td class="etc" colspan="2">비고</td>
</tr>

<!-- 문서 리스트 -->
<%
UserVO userVO = (UserVO)session.getAttribute("UserInfo");
	List<SimpleBinListVO> docList = (List<SimpleBinListVO>)request.getAttribute("docList");
	if(docList != null)
	for(SimpleBinListVO doc : docList){
%>

<tr class="docList">
	<td class="docId"><%=doc.getDocId() %></td>    
	<td class="type"><%=doc.getType() %></td> 
	<td class="title"><%=doc.getTitle() %></td>    
	<td class="sender"><%= doc.getSender()%></td>         
	<td class="ctime"><%=doc.getCtime() %></td>
	<td class="etc"><a href="/DocumentManagement/recoveryDoc.do?docid=<%=doc.getDocId() %>&userid=<%=userVO.getUserid()%>">복구</a></td>
	<td class="etc"><a href="/DocumentManagement/dropDoc.do?docid=<%=doc.getDocId() %>&userid=<%=userVO.getUserid()%>">삭제</a></td>
</tr>
<%} %>
<!-- 문서 리스트 끝 -->
</table>

</body>
</html>