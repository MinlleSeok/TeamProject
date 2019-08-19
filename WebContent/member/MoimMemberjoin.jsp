<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;char=utf-8");
	%>
	
	<form action="MoimMemberjoinAction.me" method="post">
	<input type="hidden" name="Moim_Num" value="3">
	<input type="hidden" name="UserNum" value="3">
	<input type="hidden" name="Level" value="1">
	
	<input type="submit" value="가입">
	</form>
	
</body>
</html>