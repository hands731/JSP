<%@page import="VO.UserVO"%>
<%@page import="VO.SimplePersonalListVO"%>
<%@page import="VO.SimpleApprovalListVO"%>
<%@page import="VO.SimpleGroupListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="My_DocumentStyle.jspf"%>
</head>
<body>

	<form style="margin: 10px;">
		<input type="checkbox" /> <span style="font-size: 12px">읽지 않은
			문서만 보기</span> <select
			style="width: 110px; margin-left: 250px; height: 22px;">
			<option>개인 문서</option>
			<option>결재 문서</option>
			<option>그룹 문서</option>
		</select> <select style="width: 110px; margin-left: 250px; height: 22px;">
			<option>제목</option>
			<option>받는 사람</option>
			<option>내용</option>
		</select> <input type="text" style="width: 150px" /> <input type="button"
			value="검색" style="width: 70px; text-align: center;" />
	</form>

	<div id="List_Left">
		<%
			UserVO userVO = (UserVO)session.getAttribute("UserInfo");
			int pagecount = 1;
			pagecount = (int) request.getAttribute("pagecount");
			List<SimplePersonalListVO> personalList = (List<SimplePersonalListVO>)request.getAttribute("mydocList_1");
			List<SimpleApprovalListVO> approvalList = (List<SimpleApprovalListVO>)request.getAttribute("mydocList_2");
			List<SimpleGroupListVO> mygroupList = (List<SimpleGroupListVO>)request.getAttribute("mydocList_3");
			
			
		%>
		<table>
			<caption>개인문서</caption>
			<tr class="titlemenu">
				<td class="docId">번호</td>
				<td class="title">제목</td>
				<td class="des1">작성자</td>
				<td class="ctime">시간</td>
			</tr>
			<%
			if(personalList!=null)	
			for (SimplePersonalListVO temp : personalList) {
			%>
			<tr class="docList">
				<td class="docId"><%=temp.getDocId()%></td>
				<td class="title"><a href="/DocumentManagement/viewPersonalDoc.do?docid=<%=temp.getDocId()%>&userid=<%=userVO.getUserid()%>&listid='receiveList'"><%=temp.getTitle() %></a></td>
				<td class="des1"><%=temp.getDes1()%></td>
				<td class="ctime"><%=temp.getCtime()%></td>
			</tr>
			<%
				}
			%>

<tr>
			<td colspan='4'>|
				<%
					for(int i = 1; i<=pagecount; i++){
						%>
						 <a href="/DocumentManagement/viewMyDocList.do?selectpage=<%=i %>"><%=i%>|</a>
						<%
					}
				%>
			</td>
		</tr>
		</table>
	</div>

	<div id="List_Right">
		<table>
			<caption>그룹문서</caption>
			<tr class="titlemenu">
				<td class="docId">번호</td>
				<td class="groupName">그룹</td>
				<td class="title">제목</td>
				<td class="sender">작성자</td>
				<td class="ctime">시간</td>
			</tr>
			<%
				if(mygroupList!=null)
				for (SimpleGroupListVO temp : mygroupList) {
			%>
			<tr class="docList">
				<td class="docId"><%=temp.getDocId()%></td>
				<td class="groupName"><%=temp.getGroupName()%></td>
				<td class="title"><a href="/DocumentManagement/viewGroupDoc.do?docid=<%=temp.getDocId()%>&userid=<%=userVO.getUserid()%>&listid='receiveList'"><%=temp.getTitle() %></a></td>
				<td class="des1"><%=temp.getSender() %></td>
				<td class="ctime"><%=temp.getCtime() %></td>
			</tr>
			<%
				}
			%>
			<tr>
			<td colspan='5'>|
				<%
					for(int i = 1; i<=pagecount; i++){
						%>
						 <a href="/DocumentManagement/viewMyDocList.do?selectpage=<%=i %>"><%=i%>|</a>
						<%
					}
				%>
			</td>
		</tr>
		</table>
	</div>

	<div id="List_Center">
		<table>
			<caption>결재문서</caption>
			<tr class="titlemenu">
				<td class="docId">번호</td>
				<td class="title">제목</td>
				<td class="sender">작성자</td>
				<td class="ctime">시간</td>
			</tr>
			<%
				if(approvalList!=null)
				for (SimpleApprovalListVO temp : approvalList) {
			%>
			<tr class="docList">
				<td class="docId"><%=temp.getDocId()%></td>
				<td class="title"><a href="/DocumentManagement/viewApprovalDoc.do?docid=<%=temp.getDocId()%>&userid=<%=userVO.getUserid()%>&listid='receiveList'"><%=temp.getTitle() %></a></td>
				<td class="sender"><%=temp.getSender() %></td>
				<td class="ctime"><%=temp.getCtime() %></td>
			</tr>
			<%
				}
			%>
			<tr>
			<td colspan='4'>|
				<%
					for(int i = 1; i<=pagecount; i++){
						%>
						 <a href="/DocumentManagement/viewMyDocList.do?selectpage=<%=i %>"><%=i%>|</a>
						<%
					}
				%>
			</td>
		</tr>
		</table>
	</div>

</body>
</html>