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
<%--

	<% 
		int Moim_Num = dto.getMoim_Num();
		int UserNum = dto.getUserNum();
		//MoimNum,UserNum 버튼 클릭시 값들이 넘어감 해당 값이 한개라도 존재하지 않는경우에만 가입 가능
		//(ex:4번 모임 2번 회원 db 존재일때, 4번 모임 3번회원 가능 3번모임 2번 회원 가능, 4번 모임 2번회원 불가능)
		//(구현 시 db에서select 해와서 해당 값들이 입력된 값들과 비교 해서 결정)
		if(Moim_Num(dto에서 넘어 온 insert 된 값)==<%Moim_Num%>(table에서 select해서 넘어온 값) 
		&& UserNum(dto에서 넘어 온 insert 된 값)==<%UserNum%>(table에서 select해서 넘어 온 값)
			if(&& enter(table에서 select해서 넘어온 값)==0))
				alert("이미 가입 신청된 아이디입니다.")	
			else if(&& enter(table에서 select해서 넘어온 값)==1)
				alert("이미 가입된 아이디입니다.")
			else if(&& enter(table에서 select해서 넘어온 값)==2)
				alert("강퇴당한 아이디는 재가입이 불가능 합니다.")		
		//구현하려면? dao.MoimMemberjoin()에서 select 구문 추가 후 거기서 직접 비교 가능한가? 가능할듯!
		//안되면 select 구문을 하나 더 생성해서 action에서 insert와 select 비교 후 alert로 거부 할 수 있음.
		//그렇게되면 jsp에서는 굳이 안건드려도됨 다행인듯..
	%>
	
--%>
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