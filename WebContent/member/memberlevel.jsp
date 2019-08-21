<%@page import="member.MoimMemberDAO"%>
<%@page import="member.MoimMemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

		request.setCharacterEncoding("UTF-8");
		MoimMemberBean mmdto = new MoimMemberBean();
		MoimMemberDAO mmdao = new MoimMemberDAO();

		int Level = mmdto.getLevel();

if(Level==3){
	%>
		<form action="memberlevelAction.me" method="post">
		<input type="button">		
		</form>
	
	<%
}

%>











</body>
</html>