<%@page import="java.net.URLEncoder"%>
<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>  
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>VANCO : mmProject</title>
<link href="<%=path%>/css/member/login.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://apis.google.com/js/api:client.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>

</head>
<body>
	

	<div class ="loginSec" >	
		<form class="idLogin loginSec" action="#" method="post">
		<input type="hidden">
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
			<a href="javascript:loginWithKakao()" id="custom-login-btn" class="clickBtn kakao mouseHand">카카오 로그인</a>   
			
			<%
			    String clientId = "uKiP2ZjjUHfxjMGZTcN7";
			    String redirectURI = URLEncoder.encode("http://localhost:8090/mmProject/NaverLoginCheck", "UTF-8");
			    SecureRandom random = new SecureRandom();
			    String state = new BigInteger(130, random).toString();
			    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
			    apiURL += "&client_id=" + clientId;
			    apiURL += "&redirect_uri=" + redirectURI;
			    apiURL += "&state=" + state;
			    session.setAttribute("state", state);
			 %>
  			
			<button class="clickBtn naver mouseHand" onclick="window.location.href='<%=apiURL%>'">네이버 로그인</button> 
			
			<button class="clickBtn google mouseHand customGPlusSignIn" id="customBtn">구글 로그인</button>
			<div id="name"></div>
			<div class="g-signin2" data-onsuccess="onSignIn"></div>
			 	 
			<p class="copyR">&copy; mmProject :: since 2019</p>			
	 		<p class="joinLink">아이디가 없으신가요?&nbsp; <a href="<%=path%>/member/userJoin.jsp"> 회원가입</a></p>	
	 	
	</div>

	
	
	
	
<!-- 카카오 아이디로 로그인 초기화 Script -->
<script type='text/javascript'>
var xhr = null;
// var test = "test 입니다";  


function getXMLHttpRequest() {

	if (window.ActiveXObject) {

		try {

			return new ActiveXObject("Msxml2.XMLHTTP");

		} catch (e) {

			try {

				return new ActiveXObject("Microsoft.XMLHTTP");

			} catch (e1) {

				return null;

			}

		}

} else if(window.XMLHttpRequest){

	return new XMLHttpRequest();

} else {

	return null;

}

}
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('05d13a0c3036d9bc69e650dda71cf4f9');
    function loginWithKakao() {
      // 로그인 창을 띄웁니다.
      Kakao.Auth.login({
        success: function(authObj) {
//           alert(JSON.stringify(authObj));

       // 로그인 성공시, API를 호출합니다.
          Kakao.API.request({
            url: '/v2/user/me',
            success: function(res) {
//                 console.log(JSON.stringify(res));
//                 alert(JSON.stringify(res));
//                 alert(JSON.stringify(res.id));
//                 alert(JSON.stringify(res.properties['nickname']));

//                 if(JSON.stringify(res.kakao_account['has_email'])) {
// 	                alert(JSON.stringify(res.kakao_account['email']));
// 				}
              
				
// 			alert(JSON.stringify(res.properties.thumbnail_image));
// 			alert(JSON.stringify(res.properties.profile_image));
//               console.log(res);
//               console.log(res.properties);
              
              
//               console.log("-----");
//               console.log("id : " + res.id);//<---- 콘솔 로그에 id 정보 출력(id는 res안에 있기 때문에  res.id 로 불러온다)
//               console.log(JSON.stringify(res.properties.profile_image));
//               console.log("nickname : " + res.properties['nickname']);//<---- 콘솔 로그에 닉네임 출력(properties에 있는 nickname 접근 
//               console.log(res.properties['profile_image']);
              
//                               alert("멈추기");
                              
                              Kakao.API.request({ 
                            	  url :'/v1/api/talk/profile',
                              
                               success: function(res1) {
                             
//                                    console.log(JSON.stringify(res1.profileImageURL));
//                                    alert(JSON.stringify(res1.profileImageURL));
                              
                              
                              xhr = getXMLHttpRequest();
                              xhr = new XMLHttpRequest();
                              xhr.open('POST', '../KakaoLoginCheck');
                              xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                              xhr.onreadystatechange = function() { // onreadystatechange 이벤트 핸들러를 작성함.

                            	    // 서버상에 문서가 존재하고 요청한 데이터의 처리가 완료되어 응답할 준비가 완료되었을 때
                            	    if(this.status == 200 && this.readyState == this.DONE) {

                            	        // 요청한 데이터를 문자열로 반환함.
                            	    	location.href= "../index.jsp";         

                            	    } 

                            	};
                              /*xhr.onload = function() {
                                console.log('Signed in as: ' + xhr.responseText);
                                location.href= "../index.jsp";
                              };*/
//                               alert(JSON.stringify(res.properties['nickname']));

                              xhr.send('idtoken=' + res.id + '&nicknametoken=' + res.properties['nickname'] + '&emailtoken=' + res.kakao_account['email'] + '&profile_imagetoken=' + JSON.stringify(res1.profileImageURL));
                 

                              },
                              fail: function(error) {
                                  alert(JSON.stringify(error));
                                }
                              }); 
                              
              /* xhr = getXMLHttpRequest();
              xhr = new XMLHttpRequest();
              xhr.open('POST', '../KakaoLoginCheck');
              xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
              xhr.onreadystatechange = function() { // onreadystatechange 이벤트 핸들러를 작성함.

            	    // 서버상에 문서가 존재하고 요청한 데이터의 처리가 완료되어 응답할 준비가 완료되었을 때
            	    if(this.status == 200 && this.readyState == this.DONE) {

            	        // 요청한 데이터를 문자열로 반환함.
            	    	location.href= "../index.jsp";         

            	    } 

            	};
              	//xhr.onload = function() {
                //console.log('Signed in as: ' + xhr.responseText);
                //location.href= "../index.jsp";
              	//};
              
              xhr.send('idtoken=' + res.id + '&nicknametoken=' + res.properties['nickname'] + '&emailtoken=' + res.kakao_account['email']);
  */             
            },
            fail: function(error) {
              alert(JSON.stringify(error));
            }
          });
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
      });
    };
  //]]>
</script>
<!-- // 카카오 아이디로 로그인 초기화 Script -->










<!-- 네이버 아이디로 로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "uKiP2ZjjUHfxjMGZTcN7",
			callbackUrl: "http://localhost:8090/mmProject/NaverLoginCheck.jsp",
			isPopup: true, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "green", type: 2, height: 30} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	
</script>
<!-- // 네이버 아이디로 로그인 초기화 Script -->

<!-- 구글 아이디로 로그인 초기화 Script -->
<script type="text/javascript">
var xhr = null;

function getXMLHttpRequest() {

	if (window.ActiveXObject) {

		try {

			return new ActiveXObject("Msxml2.XMLHTTP");

		} catch (e) {

			try {

				return new ActiveXObject("Microsoft.XMLHTTP");

			} catch (e1) {

				return null;

			}

		}

} else if(window.XMLHttpRequest){

	return new XMLHttpRequest();

} else {

	return null;

}

}

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
          console.log("ID Token: " + id_token);
          
          xhr = getXMLHttpRequest();
          xhr.open('POST', '<%=path%>/GoogleLoginCheck');
          xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
          xhr.onreadystatechange = function() { // onreadystatechange 이벤트 핸들러를 작성함.

      	    // 서버상에 문서가 존재하고 요청한 데이터의 처리가 완료되어 응답할 준비가 완료되었을 때

      	    if(this.status == 200 && this.readyState == this.DONE) {

      	        // 요청한 데이터를 문자열로 반환함.
      	        
      	    	// location.href = "../index.jsp";
      	    	window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "<%=path%>/index.jsp");

      	    }

      	};
          xhr.send('idtoken=' + id_token);
        
        }, function(error) {
          alert(JSON.stringify(error, undefined, 2));
        });
  }
  </script>
  <!-- // 구글 아이디로 로그인 초기화 Script -->
  
<script>startApp();</script>
</body>
</html>