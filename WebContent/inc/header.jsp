<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>   
 <%String path = request.getContextPath();%>
 
 <!-- 로그인 페이지 url 설정 -->		

<header class="header">		
	<div class="headerWrap">
		<h1>
			<a href="./mm.me">mmProject</a>
		</h1>
		
		<nav class="headLeft">
			<ul>
				<li><a href=""><span id="seeAll">전체보기</span></a></li>				
				<li><a href="<%=path%>/moim/moimIndex.jsp"><span>작업1</span></a></li>
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
	<% if(session.getAttribute("id") == null) { %>
		<ul>
			<li class="userJoin"><a href="<%=path%>/member/userJoin.jsp">회원가입</a></li>			
			<li><a href="<%=path%>/member/login.jsp">로그인</a></li>				
		</ul>
		<%} else { %>
			<ul>
			<li class="userJoin"><%=session.getAttribute("id") %></li>			
			<li><a href="<%=path%>/member/login.jsp">로그아웃</a></li>				
			</ul>
		<% } %>
	</nav>					

	</div>	
</header>
	
