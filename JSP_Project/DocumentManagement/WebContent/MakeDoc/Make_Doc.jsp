<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../menu.css">
<%@ include file="../title.jspf" %>
<%@ include file="../menu.jspf" %>

</head>

<body>
<p>
	생성하실 문서의 종류를 선택하여 주십시오. <br><br>
	<a href="./Make_PersonalDoc.jsp"><button class="button_make_doc_sort">개인 문서</button></a> &nbsp;
	<a href="./Make_ApprovalDoc.jsp"><button class="button_make_doc_sort">결재 문서</button></a> &nbsp;
	<a href="./Make_GroupDoc.jsp"><button class="button_make_doc_sort">그룹 문서</button></a> &nbsp;
</p>


</body>
</html>