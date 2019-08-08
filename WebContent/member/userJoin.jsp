<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>  
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mmProject</title>

<link href="<%=path%>/css/member/userJoin.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
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


<script type="text/javascript">
/* check박스 전체선택 jQuery */
$(document).ready(function(){
	$("#checkAll").on("click", function(){
        if($("#checkAll").prop("checked")){
            $("input[class=agree]").prop("checked",true);
        }else{
            $("input[class=agree]").prop("checked",false);
        }
    });
  $("input[class=agree]").on("click", function(){
  if($(this).is(":checked") == false){
   $("#checkAll").prop("checked",false)
  }
  });
});

</script>





</head>
<body>
	<div class ="loginSec" >	
		<form class="idLogin loginSec">
				<div class="loginHead">					
			    	<p class="loginIntro">mmProject</p>
			  	</div>
			  	
			  	<p class="snsLogTit">신규 회원가입</p>
			  	
				
				<div class="agreeBox">
				  	<div class="checkBox">
			        	<span class="agreeAll">약관 동의</span>
			        	<input type="checkbox" class="agreeAll" id="checkAll">
			        	<label class="agreeAll" for="agreeAll">전체 동의</label>
			        </div>
			        <div class="checkBox">
			        	<a href="">이용약관</a>
			        	<input type="checkbox" class="agree" name="agree1" id="userAgreement">
			        	<label class="" for="userAgreement">동의(필수)</label>
			        </div>
			        <div class="checkBox">
			        	<a href="">개인정보 취급방침</a>
			        	<input type="checkbox" class="agree" name="agree2"  id="privacy">
			        	<label class="" for="privacy">동의(필수)</label>
			        </div>    
			 	 </div>

			 	 
			 	<button class="clickBtn kakao mouseHand" onclick="agreeCheck2()">카카오톡으로 가입하기</button>
			 	<button class="clickBtn naver mouseHand" onclick="agreeCheck()">네이버로 가입하기</button>
			 	<button class="clickBtn google mouseHand" onclick="agreeCheck()">google로 가입하기</button>
			 	<a class="clickBtn idJoin mouseHand" onclick="agreeCheck1()">아이디로 가입하기</a>
			 	
			</form>	
	 		<p class="joinLink">아이디가 있으신가요?&nbsp; <a href="<%=path%>/member/login.jsp"> 로그인</a></p>	
	 		<p class="copyR">&copy; mmProject :: since 2019</p>
	</div>
	
	

</body>
</html>