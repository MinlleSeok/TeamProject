<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="668617944084-5is0mb0j9q7p9pp2rugimnttsfi6mdld.apps.googleusercontent.com">
</head> 
<body>
 <!-- 로그인 페이지 url 설정 -->		

<header class="header">		
	<div class="headerWrap">
		<h1>
			<a href="#">우리동네 반려견 커뮤니티</a>
		</h1>
		
		<nav class="headLeft">
			<ul>
				<li><a href="#a"><span id="seeAll">카테고리</span></a></li>				
				<li><a href="#a"><span>카테고리</span></a></li>
				<li><a href="#"><span>카테고리</span></a></li>
				<li><a href="#"><span>카테고리</span></a></li>
			</ul>
		</nav>
		
		<div class="searchBar">
			<div>
				<span class=""></span>
				<form action="#" method="post">
					<input action = "#" type="text" method="post" placeholder="검색을 원하시나요?">
				</form>
			</div>
		</div>
		
	
	<nav class="headRight">
		<ul>
		<% if(session.getAttribute("id") == null) { %>
			<li class="userJoin"><a href="/mmProject/member/userJoin.jsp">회원가입</a></li>			
			<li><a href="/mmProject/member/login.jsp">로그인</a></li>	
			<% } else { %>			
			<li><%=session.getAttribute("id")%></li>
			<li><a href="#" onclick="signOut();">Sign out</a></li>
			<% } %>
		</ul>
	</nav>					

	</div>	
</header>
  <script>
  function signOut() {
	    
	    	var xhr = new XMLHttpRequest();
	          xhr.open('GET', '/mmProject/Logout', true);
	          //xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	          xhr.onload = function() {
	          	location.href = "/mmProject/index.jsp";
	          };
	          xhr.send();
	  }
  </script>
  </body>
  </html>