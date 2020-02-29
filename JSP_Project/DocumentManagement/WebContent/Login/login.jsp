<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/menu.css">
<title>로그인</title>
<style>
		#head {
		height:200px;
		width:1000px;
		border: solid 8px #97d2cb ;
		text-align: center;
		font-size:30px;
		background-color:#ffffff;
	}
</style>

</head>
<body>

<div id="head">
<p style="margin-top:60px">DOCUMENT MANAGEMENT SYSTEM</p>
</div>


<div style="margin-left:350px">
<%
	session.setAttribute("islogin", false);
%>
<br>
		<form action="/DocumentManagement/login.do" method="post">
			<table style="width:350px">
				<tr>
					<td colspan='2' align="center">로그인 정보를 입력하세요</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userid"></td>
					<td rowspan='2'><input type="submit" value="로그인"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="userpassword"></td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>