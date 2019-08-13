<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%
	String id = "test";
	String mmNum = "1";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 메타태그1. 모바일 뷰포트용 메타태그 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">

<!-- CSS 연결 -->
<link href="<%=path%>/css/inc/header.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/css/index/index.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/css/index/common.css" type="text/css"
	rel="stylesheet">
<link href="<%=path%>/css/moim/moimContent.css" type="text/css"
	rel="stylesheet">


<!-- 자바스크립트파일 연동 -->
<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<title>mmProject</title>
</head>
<body>
<input type="hidden" id="mmNum" value="<%=mmNum%>">
<input type="hidden" id="path" value="<%=path%>">
<input type="hidden" id="id" value="<%=id%>">

	<div class="moimInfo">
		<span>소모임 주제 제목 최대 20자20자20자20자20자</span> <span>소모임 상세내용 최대
			500자소모임 상세내용 최대 500자소모임 상세내용 최대 500자<br> 소모임 상세내용 최대 500자소모임
			상세내용 최대 500자<br>
		<br> 소모임 상세내용 최대 500자<br> 소모임 상세내용 최대 500자소모임 상세내용 최대 500자<br>
		</span>
	</div>

	<div id="bungaeButton">
		<input type="button" name="bungae" id="bungae" value="번개개설"
			onclick="bungaeInsertToggle()">
	</div>

	<div id="bungaeInsert" style="display: none;">
		<ul>
			<li><input type="text" name="bungaeName" id="bungaeName"
				placeholder="번개명"></li>
			<li style="display: none;"><p id="bungaeNameP"></p></li>
			<li><input type="text" name="bungaeRef" id="bungaeRef"
				placeholder="번개 간략 설명"></li>
			<li><input type="date" name="bungaeDate" id="bungaeDate">
			</li>
			<li><select id="bungaeHour">
					<%
						for (int i = 0; i < 24; i++) {
							if(i < 10){
					%>
								<option value="0<%=i%>">0<%=i%></option>
					<%
							} else {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
							}
						}
					%>
			</select>시
			<select id="bungaeMinute">
					<%
						for (int i = 0; i < 60; i=i+10) {
							if(i < 10){
					%>
								<option value="0<%=i%>">0<%=i%></option>
					<%
							} else {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
							}
						}
					%>
			</select>분
			</li>
			<li><select id="bungaeMax">
					<%
						for (int i = 1; i < 16; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
			</select>명</li>
			
		</ul>
		<input type="button" value="번개!" onclick="bungaeInsert()">
	</div>
	
	<div id="bungaeList">
	
	</div>
	
<script src="<%=path%>/js/bungae.js"></script>
<script type="text/javascript">printBungaeList(<%=mmNum%>)</script>

</body>
</html>