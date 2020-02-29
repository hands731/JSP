<%@page import="VO.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="VO.SimplePersonalListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@include file="./Personal_SendListStyle.jspf" %>
</head>
<body>

<form action="/DocumentManagement/personalSearchComHan.do" style="margin:10px;">
<h4>발신문서함</h4>
<input name="listId" type="hidden" value="sendList">
<input name="readCheck" type="checkbox" value="0" onclick="location='/DocumentManagement/viewPersonalSendList.do?readCheck=0'">읽지 않은 메일만 보기</span>
<select name="searchId" style="width:110px;margin-left:250px;height:22px;">
	<option value="documenttbl.title">제목</option>
	<option value="documenttbl.des1">받는 사람</option>
	<option value="documenttbl.content">내용</option>
</select>
<input name="searchText" type="text" style="width:150px"/>
<input type="submit" value="검색" style="width:70px;text-align:center;"/>
</form>

<table class="listTable" border="3px" bordercolor="#77b2ab" rules="rows">

<!-- 리스트 메뉴 -->
<tr class="titlemenu">
	<td class="docId">문서 번호</td>    
	<td class="title">제목</td>    
	<td class="des1">받는 사람</td>      
	<td class="ctime">날짜</td>
	<td class="etc">비고</td>
</tr>

<!-- 문서 리스트 -->
<%
	UserVO userVO = (UserVO)session.getAttribute("UserInfo");
	String num= null;
	int pagecount = 1;
	pagecount = (int) request.getAttribute("pagecount");
	
	List<SimplePersonalListVO> docList = (List<SimplePersonalListVO>)request.getAttribute("docList");
	if(docList != null)
	for(SimplePersonalListVO doc : docList){
%>

<tr class="docList">
	<td class="docId"><%=doc.getDocId() %></td>    
	<td class="title"><a href="/DocumentManagement/viewPersonalDoc.do?docid=<%=doc.getDocId()%>&userid=<%=userVO.getUserid()%>&listid=sendList"><%=doc.getTitle() %></a></td>    
	<td class="des1"><%=doc.getDes1() %></td>        
	<td class="ctime"><%=doc.getCtime() %></td>
	<td class="etc"><a href="/DocumentManagement/deleteDoc.do?docid=<%=doc.getDocId()%>&listid=sendList&userid=<%=userVO.getUserid()%>&num=4">지우기</a></td>
</tr>
<%} %>
<!-- 문서 리스트 끝 -->
<tr>
			<td colspan='5'>|
				<%
					for(int i = 1; i<=pagecount; i++){
						%>
						 <a href="/DocumentManagement/viewPersonalSendList.do?selectpage=<%=i %>"><%=i%>|</a>
						<%
					}
				%>
			</td>
		</tr>
</table>


</body>
</html>