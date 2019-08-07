<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>

<link href="/mmProject/css/member/userJoin.css" type="text/css" rel="stylesheet">
<link href="/mmProject/css/index/common.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>


<script type="text/javascript">
	function agreeCheck1(){ // 약관에 동의하지 않으면 다음으로 진행 안되도록.
		var vancoUserJoin = document.vancoUserJoin;
		var agreeChk1=$("input[name=agree1]");
		var agreeChk2=$("input[name=agree2]");
		
		// 미입력 시 입력요청
		if(agreeChk1.is(":checked") && agreeChk2.is(":checked")){
			location.href="idUserJoin.me";					
		}else{
			alert("이용약관과 개인정보 취급방침에 동의해 주세요.")
			return false;
		}	
	}  // joinCheck() 끝
	
	
	function agreeCheck2(){ // 약관에 동의하지 않으면 다음으로 진행 안되도록.
		var vancoUserJoin = document.vancoUserJoin;
		var agreeChk1=$("input[name=agree1]");
		var agreeChk2=$("input[name=agree2]");
		
		// 미입력 시 입력요청
		if(agreeChk1.is(":checked") && agreeChk2.is(":checked")){
			loginWithKakao();	
		}else{
			alert("이용약관과 개인정보 취급방침에 동의해 주세요.")
			return false;
		}	
	}  // joinCheck() 끝
	
	
</script>





</head>
<body>
	<div class ="loginSec" >	
		<form class="idLogin loginSec">
				<div class="loginHead">					
			    	<p class="loginIntro">mmProject</p>
			  	</div>
			  	
			  	<p class="snsLogTit">신규 회원가입</p>
			  	
				<%@ include file="agreeBox.jsp" %>
			 	 
			 	<button class="clickBtn kakao mouseHand" onclick="agreeCheck2()">카카오톡으로 가입하기</button>
			 	<button class="clickBtn naver mouseHand" onclick="agreeCheck()">네이버로 가입하기</button>
			 	<button class="clickBtn google mouseHand" onclick="agreeCheck()">google로 가입하기</button>
			 	<a class="clickBtn idJoin mouseHand" onclick="agreeCheck1()">아이디로 가입하기</a>
			 	
			</form>	
	 		<p class="joinLink">아이디가 있으신가요?&nbsp; <a href="/mmProject/member/login.jsp"> 로그인</a></p>	
	 		<p class="copyR">&copy; mmProject :: since 2019</p>
	</div>
	
	

</body>
</html>