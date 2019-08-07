<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>  
<!DOCTYPE html>
<html>
<head>

<link href="/mmProject/css/member/login.css" type="text/css" rel="stylesheet">
<link href="/mmProject/css/index/common.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
   


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : mmProject</title>
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
         <head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
</head>

<body>
<!-- 네이버아이디로로그인 버튼 노출 영역 -->
<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
   var naverLogin = new naver.LoginWithNaverId(
      {
         clientId: "EOjVmFxdUM3hM4PTERSN",
         callbackUrl: "http://localhost:8090/naver/callback.jsp",
         isPopup: false, /* 팝업을 통한 연동처리 여부 */
         loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
      }
   );
   
   /* 설정정보를 초기화하고 연동을 준비 */
   naverLogin.init();
   
</script>
<!-- // 네이버아이디로로그인 초기화 Script -->
         
         <button class="clickBtn google mouseHand" >구글 로그인</button>
              
         <p class="copyR">&copy; mmProject :: since 2019</p>         
          <p class="joinLink">아이디가 없으신가요?&nbsp; <a href="/mmProject/member/userJoin.jsp"> 회원가입</a></p>   
       
   </div>
</body>
</html>