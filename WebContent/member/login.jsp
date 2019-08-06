<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : mmProject</title>
<link href="/mmProject/css/member/login.css" type="text/css" rel="stylesheet">
<link href="/mmProject/css/index/common.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->
<!-- <meta name="google-signin-client_id" content="668617944084-jm7kn0qjgu77fea46b01at3osp43182a.apps.googleusercontent.com">	 -->
<script src="https://apis.google.com/js/api:client.js"></script>
  <script>
  var googleUser = {};
  var startApp = function() {
    gapi.load('auth2', function(){
      // Retrieve the singleton for the GoogleAuth library and set up the client.
      auth2 = gapi.auth2.init({
        client_id: '668617944084-5is0mb0j9q7p9pp2rugimnttsfi6mdld.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        // Request scopes in addition to 'profile' and 'email'
        // scope: 'profile'
      });
      attachSignin(document.getElementById('customBtn'));
    });
  };

  function attachSignin(element) {
    console.log(element.id);
    auth2.attachClickHandler(element, {},
        function(googleUser) {
          document.getElementById('name').innerText = "Signed in: " +
              googleUser.getBasicProfile().getName();
          
       // The ID token you need to pass to your backend:
          var id_token = googleUser.getAuthResponse().id_token;
          // console.log("ID Token: " + id_token);
          
          var xhr = new XMLHttpRequest();
          xhr.open('POST', '/mmProject/LoginCheck');
          xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
          xhr.onload = function() {
          	location.href = "/mmProject/index.jsp";
          };
          xhr.send('idtoken=' + id_token);
        
        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });
  }
  </script>

</head>
<body>
	

	<div class ="loginSec" >	
		<form class="idLogin loginSec" action="#" method="post">
				<div class="loginHead">					
			    	
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
			<button class="clickBtn naver mouseHand" >네이버 로그인</button> 
			
			<button class="clickBtn google mouseHand customGPlusSignIn" id="customBtn">구글 로그인</button>
			<div id="name"></div>
			<div class="g-signin2" data-onsuccess="onSignIn"></div>
			 	 
			<p class="copyR">&copy; mmProject :: since 2019</p>			
	 		<p class="joinLink">아이디가 없으신가요?&nbsp; <a href="/mmProject/member/userJoin.jsp"> 회원가입</a></p>	
	 	
	</div>
	<!-- 
	<script type="text/javascript">
function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	
	  // The ID token you need to pass to your backend:
      var id_token = googleUser.getAuthResponse().id_token;
      console.log("ID Token: " + id_token);
      
      var xhr = new XMLHttpRequest();
      xhr.open('POST', './LoginCheck');
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
      xhr.onload = function() {
        console.log('Signed in as: ' + xhr.responseText);
      };
      xhr.send('idtoken=' + id_token);
	}
	
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
  
  function onSuccess(googleUser) {
      console.log('Logged in as: ' + googleUser.getBasicProfile().getName());
      var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
	
	  // The ID token you need to pass to your backend:
      var id_token = googleUser.getAuthResponse().id_token;
      console.log("ID Token: " + id_token);
      
      var xhr = new XMLHttpRequest();
      xhr.open('POST', './LoginCheck');
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
      xhr.onload = function() {
        console.log('Signed in as: ' + xhr.responseText);
      };
      xhr.send('idtoken=' + id_token);
    }
    function onFailure(error) {
      console.log(error);
    }
    function renderButton() {
      gapi.signin2.render('my-signin2', {
        'scope': 'profile email',
        'width': 240,
        'height': 50,
        'longtitle': true,
        'theme': 'dark',
        'onsuccess': onSuccess,
        'onfailure': onFailure
      });
    }
</script>
 -->
<script>startApp();</script>
</body>
</html>