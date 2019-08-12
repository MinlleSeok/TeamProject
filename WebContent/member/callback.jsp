<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>

<title>Insert title here</title>
<body>
	<script type="text/javascript">
          var naver_id_login = new naver_id_login("WHKw6TzMKV_kjWOzr7Aa", "http://localhost:8090/mmProject/member/callback.jsp");
          // 접근 토큰 값 출력
          //alert(naver_id_login.oauthParams.access_token);
          
          // 네이버 사용자 프로필 조회
          naver_id_login.get_naver_userprofile("naverSignInCallback()");
          
          // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
          function naverSignInCallback() {
            var userId = naver_id_login.getProfileData('id')+"n";
            var userPwd = naver_id_login.getProfileData('id')+"@n";
            var userEmail = naver_id_login.getProfileData('email');
            var userNickname = naver_id_login.getProfileData('nickname');
            var userName = naver_id_login.getProfileData('userName');
            var params = '?userId='+userId;
            params += '&userPwd='+userPwd;
            params += '&userNickname='+userNickname;
            params += '&userEmail='+userEmail;
            $.ajax({
                 url : "snsIdChkAction.me?userId="+userId+"&userPwd="+userPwd+"&userNickname="+userNickname+"&userEmail="+userEmail+"&userName="+userName,//action = proc
                		 type:'post',
                		 dataType:'text',
                     headers : {
                     "Accept" : "application/json",
                     "Content-Type" : "application/json"
                  },
                  success : function(idChk){
                      
                     if(idChk=="noId"){ //DB에 아이디가 없을 경우 => 회원가입
                          alert("회원가입중...");
                          /* $.ajax({
                              url : "userJoin.me",
                              method : "POST",
                              headers : {
                                "Accept" : "application/json",
                                "Content-Type" : "application/json"
                              },
                              data : JSON.stringify({
                                  userId : id,
                                userName : nickname,
                                password : "naver123",
                              }),
                              success : function(JSONData){
                                 alert("회원가입이 정상적으로 되었습니다.");
                                 window.close();
                                 top.opener.location="/user/snsLogin/"+id;
                              }
                          }) */
                      }
                      if(idChk=="yesId"){ //DB에 아이디가 존재할 경우 => 로그인
                    	  alert("로그인중"); 
                    	  location.href="./mm.me"; 
                    	  
                    }
                  }
            })    
            
          }
    </script>

	<form name="defaultForm"></form>

</body>
</html>
