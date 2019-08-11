<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>  
<%String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>

<link href="<%=path%>/css/member/login.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mmProject</title>
</head>
<body>
	

	<div class ="loginSec" >	
		<form class="idLogin loginSec" action="#" method="post">
				<div class="loginHead">					
			    	....................
			    	<p class="loginIntro">mmProject</p>
			  	</div>
			
			  	<div class="insertBtn">
			    	<input type="text" id="userId" name="userId" class="insertBox" placeholder="아이디" required autofocus>
			 	   <label for="userId"></label>
			  	</div>
			
			  	<div class="insertBtn">
				    <input type="password" id="userPwd" name="userPwd" class="insertBox" placeholder="비밀번호" required>
			   	 	<label for="userPwd"></label>
				</div>
			
			 	 <div class="checkbox mb-3">
			   		 <label>
			     	 <input type="checkbox" value="remember-me"> 자동 로그인
			   		 </label>
			 	 </div>
			 	 <button class="clickBtn mouseHand" type="submit">로그인</button>
			 	
			 	 <hr>   <!-- 줄긋기 --> 
			 	 
			    <p class="snsLogTit">SNS 계정으로 로그인</p>
			</form>	 	 
			<a  href="javascript:loginWithKakao()"  class="clickBtn kakao mouseHand">카카오 로그인</a>   
			<jsp:include page="../inc/naver.jsp"/>	 
			<button class="clickBtn google mouseHand" >구글 로그인</button>
			 	 
			<p class="copyR">&copy; mmProject :: since 2019</p>			
	 		<p class="joinLink">아이디가 없으신가요?&nbsp; <a href="<%=path%>/member/userJoin.jsp"> 회원가입</a></p>	
	 		
	 		<script type='text/javascript'>
			  // 사용할 앱의 JavaScript 키를 설정해 주세요.
			    Kakao.init('47912943f658f0a65798f337c2005d84');
			    function loginWithKakao() {
			    	alert("카카오호출");
			      // 로그인 창을 띄웁니다.
			      Kakao.Auth.login({
			        success: function(authObj) {
			          alert(JSON.stringify(authObj));
			        },
			        fail: function(err) {
			          alert(JSON.stringify(err));
			        }
			      });
			    };
			</script>
	 		
	 		
	 	
	</div>
</body>
</html>