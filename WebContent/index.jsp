<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- CSS 연결 -->
<link href="/mmProject/css/index/index.css" type="text/css" rel="stylesheet">
<link href="/mmProject/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/mmProject/css/inc/header.css" type="text/css" rel="stylesheet">


<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- bxSlider 플러그인 연동 -->
<link href="/mmProject/css/plugin/jquery.bxslider.css" type="text/css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<script type="text/javascript">
/* bx슬라이더 */
$(document).ready(function(){
	$('.slider').bxSlider({
		speed:500, //0.5초
		pager:true, //페이지 숫자 표시를 노출을 제어(false:숨김, true:노출)
		moveSlides:1, //슬라이드 이동 갯수 설정
		slideWidth:660, // 슬라이드 폭
		minSlides:1,	//최소 노출 슬라이드
		maxSlides:4, // 최대 노출 슬라이드
		slideMargin:30, // 슬라이드간 간격
		auto:true,	// 자동으로 넘어감
		autoHover:true, // 마우스가 올라오면 자동정지 시키는 설정
		controls:true, // 이전/다음 텍스트 숨김(false), 노출(true)
		/* mode:'fade', */
	});
});
</script>


<title>mm 프로젝트</title>
</head>
<body>

<!-- 헤더영역 -->
	<jsp:include page="inc/header.jsp"/>
	
<div class="bodyWrap">

<!------------------------------- 왼쪽 사이드 부분 ------------------------------------>
	
<div class="bodyWrapLeft">
	
	<!-- 메인 슬라이드 -->	
		<jsp:include page="inc/mainSlide.jsp"/>	
	<!-- 위클리 인기 갤러리 -->	
		<jsp:include page="inc/weeklyPop.jsp"/>
	<!-- 훈련과 행동교정 매거진 -->	
		<jsp:include page="inc/mainMagazine.jsp"/>		
	<!-- 견종별 커뮤니티 -->		
		<jsp:include page="inc/dogKindBoard.jsp"/>		
	<!-- 오늘의 조회수 베스트 -->		
		<jsp:include page="inc/bestClick.jsp"/>	
	
</div>
	
	
<!------------------------------- 오른쪽 사이드 부분 ------------------------------------>	
<div class="bodyWrapRight">
	<!-- 오른쪽 구역 1. 최신글 -->		
		<jsp:include page="inc/todayPost.jsp"/>
	<!-- 오른쪽 구역 2. 오늘의 인기 사진 -->	
		<jsp:include page="inc/todayPic.jsp"/>	
</div>	



<!-- ------------------------------------푸터영역---------------------------------- -->
		<jsp:include page="inc/footer.jsp"/>
		
</div>
</body>
</html>