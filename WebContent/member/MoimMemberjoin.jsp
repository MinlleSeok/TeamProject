<%@page import="member.joindto"%>
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
	response.setContentType("text/html;charset=utf-8");
	joindto dto= new joindto();
	%>
	
	<form action="MoimMemberjoinAction.me" method="post">
	<input type="hidden" name="Moim_Num" value="3">
	<input type="hidden" name="UserNum" value="3">
	<input type="hidden" name="Level" value="1">
	<input type="hidden" name="UserCount" value="0">
	<input type="hidden" name="enter" value="0">
		
	<input type="submit" value="가입">
	</form>
	
	<%
	int Level = dto.getLevel();
	if(Level==3){
	%>
	<a href="../board/Memberinfo.jsp"><input type="button" value="회원정보보기"></a>
	<%}else{}%>
	
</body>
</html>