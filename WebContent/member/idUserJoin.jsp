
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<%=path%>/css/member/userJoin.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>


</head>
<body>



<div class ="loginSec" >	
	<form onsubmit="return joinCheck()" class="idLogin loginSec" action="userJoinAction.me" method="post" name="vancoUserJoin"> <!--  userJoinProc.jsp -->
		<div class="loginHead">					
	    	<a href="./mm.me" class="vcLogo center">VANCO</a>
	    	<p class="loginIntro">mmProject</p>
	  	</div>
	  	
		<p class="snsLogTit">아이디로 회원가입</p>  
	
	  	<div class="insertBtn">
	  		<label for="userId">아이디</label>
	    	<input type="text" id="userId"   name="userId" class="insertBox" placeholder="아이디를 입력해 주세요." required autofocus>			 	   
	  	</div>
	
	  	<div class="insertBtn">
		  	<label for="userPwd">비밀번호</label>
		    <input type="password" id="userPwd" name="userPwd" class="insertBox" placeholder="숫자와 영문자 조합으로  6~20자리" required>			   	 	
		</div>
		<div class="insertBtn">
			<label for="userPwdChk">비밀번호 확인</label>
		    <input type="password" id="userPwdChk" name="userPwdChk" class="insertBox" placeholder="비밀번호를 한번 더 입력해 주세요." required>			   	 	
		</div>
		<div class="insertBtn">
			<label for="userNickname">닉네임</label>
		    <input type="text" id="userNickname" name="userNickname" class="insertBox" placeholder="한글로 2~7자 이내로 입력해 주세요." required>			   	 	
		</div>
		<div class="genderWrap">
		
		    <input type="radio" id="userGenderM" name="userGender" value="남" class="insertBox userGender">
		    <label for="userGenderM" id="labelM"></label>
		    
		    <input type="radio" id="userGenderF" name="userGender" value="여" class="insertBox userGender">
		    <label for="userGenderF" id="labelF"></label>		   	 	
		</div>
		
								
	 	<button class="clickBtn joinClick" type="submit">회원 가입하기</button>	
		 
		<p class="copyR">&copy; VANCO :: since 2019</p>
	</form>		
</div>


   
</body>
</html>