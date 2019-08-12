
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/moim/moimContent.css" type="text/css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!-- jstl 라이브러리 사용을 위한 선언  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%
	String pageCall=request.getParameter("pageCall");
	if(pageCall == null){pageCall = "moimInfo.jsp";}	
%>

<body>
<div class="bodyWrap">	
	<!-- 헤더영역 -->	
	<jsp:include page="../inc/header.jsp" />		
		
		
	<div class="moimContentWrap">
		<div class ="moimTitPic" >
			<img src ="<%=path%>/images/somoim.jpg">
		</div>
		<div class="moimContentMenu">
			<a href="./moimInfo.mo"><span>모임정보</span></a>
			<a href="moimBoard.mo"><span>게시판</span></a>
			<a href=""><span>회원정보</span></a>
		</div>
	
	
	<!-- 클릭 시 페이지 정보 얻어 오기 -->
	<jsp:include page="<%=pageCall%>"/>
	
	<%
	int pageCallNo=1; 
	if(pageCall.equals("moimBoard.jsp")){pageCallNo=2;}
	else if(pageCall.equals("moimMember.jsp")){pageCallNo=3;}
	%>
	
	</div>
	
<style>
	.moimContentMenu a:NTH-CHILD(<%=pageCallNo%>) span{color:#64B9FF; font-weight:bold; border-bottom: 4px solid #64b9ff;}

</style>
	
	
	
</div>
</body>
</html>