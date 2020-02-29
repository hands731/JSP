<%@page import="VO.UserVO"%>
<%@page import="VO.SimpleGroupListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.docId{
	text-align:center;
	width:100px;
}

.titlemenu .title{
	text-align:center;
	width:400px;
}

.docLsit .title{
	text-align:left;
	width:400px;
}
.sender{
	text-align:center;
	width:70px;
}
.groupname{
	text-align:center;
	width:70px;
}

.ctime{
	text-align:center;
	width:100px;
}
.titlemenu td{
	font-size: 13px;
}

.listTable{
	width:800px;
	margin-bottom: 100px;
}
</style>
<% UserVO userVO = (UserVO)session.getAttribute("UserInfo");
//int pagecount = 1;
//pagecount = (int) request.getAttribute("pagecount");

	List<SimpleGroupListVO> docList = (List<SimpleGroupListVO>)request.getAttribute("docList");
	String groupName = null;
	if(!docList.isEmpty()){
	groupName = docList.get(0).getGroupName();
   }
%>
	</head>
<body>
<form action="/DocumentManagement/groupSearchComHan.do" style="margin:10px;">
<input name="groupName" type="hidden" value="<%=groupName %>">
<select name ="searchId" style="width:110px;margin-left:250px;height:22px;">
	<option value="title">제목</option>
	<option value="sender">작성자</option>
	<option value="content">내용</option>
</select>
<input name = "searchText" type="text" style="width:150px"/>
<input type="submit" value="검색" style="width:70px;text-align:center;"/>
</form>

<table class="listTable" border="3px" bordercolor="#77b2ab" rules="rows">

<!-- 리스트 메뉴 -->
<tr class="titlemenu">
	<td class="docId">문서 번호</td>    
	<td class="title">제목</td>    
	<td class="sender">작성자</td> 
	<td class="groupname">그룹 이름</td>     
	<td class="ctime">날짜</td>
</tr>

<!-- 문서 리스트 -->
<%
	if(docList != null)
	for(SimpleGroupListVO doc : docList){
%>

<tr class="docList">
	<td class="docId"><%=doc.getDocId() %></td>    
	<td class="title"><a href="/DocumentManagement/viewGroupDoc.do?docid=<%=doc.getDocId()%>&userid=<%=userVO.getUserid()%>&title=<%=doc.getTitle()%>"><%=doc.getTitle() %></a></td>    
	<td class="sender"><%=doc.getSender() %></td> 
	<td class="groupname"><%= doc.getGroupName() %></td>         
	<td class="ctime"><%=doc.getCtime() %></td>
</tr>
<%} %>
<!-- 문서 리스트 끝 -->
<%-- <tr>
			<td colspan='5'>|
				<%
					for(int i = 1; i<=pagecount; i++){
						%>
						 <a href="/DocumentManagement/viewGroupList.do?selectpage=<%=i %>"><%=i%>|</a>
						<%
					}
				%>
			</td>
		</tr> --%>
</table>
</body>
</html>