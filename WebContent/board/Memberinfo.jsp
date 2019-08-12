
<%@page import="java.sql.Timestamp"%>
<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 운영자만 접속가능한 페이지 .. 강퇴기능/회원정보 이름 나이 닉네임 프로필 사진 보여짐 -->
<%

	request.setCharacterEncoding("UTF-8");

	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
	
	String UserId = dto.getUserId();
	String UserEmail = dto.getUserEmail();
	String UserNickname = dto.getUserNickname();
	String UserGender = dto.getUserGender();
	String UserDistrict1 = dto.getUserDistrict1();
	String UserDistrict2 = dto.getUserDistrict2();
	int UserBirth = dto.getUserBirth();
	Timestamp JoinDate = dto.getJoinDate();
	String UserIp = dto.getUserIp();
	String UserPhoto = dto.getUserPhoto();
	String UserName = dto.getUserName();
	int UserLevel = dto.getUserLevel();
	String UserText =dto.getUserText();
	
	


%>
<div id="memberinfo">
	<article>
	<h1>회원목록</h1>
	<form action="MemberinfoPro.bo" method="post">
		<div id=memberindex>
			<table border="1">
				<tr>
				<td colspan="2">회원사진</td>
				<td>회원이름</td>
				<td>회원아이디</td>
				<td>회원이메일</td>
				<td>회원닉네임</td>
				<td>회원성별</td>
				<td>회원생일</td>
				</tr>
				<tr>
				<td colspan="2" rowspan="3"><%=UserPhoto%></td>
				<td><%=UserName%></td>
				<td><%=UserId %></td>
				<td><%=UserEmail %></td>
				<td><%=UserNickname %></td>
				<td><%=UserGender %></td>
				<td><%=UserBirth %></td>
				</tr>
				<tr>
				<td>회원지역1</td>
				<td>회원지역2</td>
				<td>회원가입일</td>
				<td>회원아이피</td>
				<td>회원level</td>
				<td>회원간단소개</td>
				</tr>
				<tr>
				<td><%=UserDistrict1 %></td>
				<td><%=UserDistrict2 %></td>
				<td><%=JoinDate %></td>
				<td><%=UserIp %></td>
				<td><%=UserLevel %></td>
				<td><%=UserText %></td>
				</tr>
			</table>
		</div>
	</form>
	</article>
</div>
</body>
</html>