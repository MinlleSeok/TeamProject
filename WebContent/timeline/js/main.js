$(function(){


/*
콘텐츠 영역 개발하기
-콘텐츠 영역은 크게 
  비주얼배너, 
  알림판, 
  최근게시물, 
  알림배너, 
  베스트Book, 
  페이스북,
  마케팅, 
  온라인서점
 으로 나뉩니다.
 
-레이아웃은 비주얼 배너가 들어가는 visual영역과
  나머지 주제 소스들이 들어가는 content영역으로 나뉘었음.  
*/


//-----------------------------------------------------------
/*
 주제 : 비주얼 배너 터치 슬라이드 만들기
 비주얼 배너 영역은 배너 중 한개만 노출되어 이루어져 있으며,
 [이전/다음]버튼을 누르면 배너가 이동되어 바뀌게 됨.
 스마트폰에서는 손가락으로 터치 했을때 도 배너가 바뀔수 있도록 제작 하자
 */
	
/*	swipejs 플러그인 활용하기
	
	1.다운로드 사이트 주소 : https://swipejs.com
	2.swipejs플러그인의 기본 메서드 적용 방법
	
	기본문법
			window.mySwipe = $(요소선택).Swipe({옵션설정})
							.data('Swipe');
	3.옵션 종류
	종류										설명
	startSlide:숫자(defalut:0)				인덱스 값으로 초기에 열릴 슬라이드를 지정합니다.
	auto : 밀리초								자동슬라이드 이동 시간을 설정합니다.
	continuous : 논리값(defalut:true) 		슬라이드 이동시 연속적으로
											반복, 롤링되어 나오게 할것인지 설정함.
	disableScroll : 논리값(defalut:true)  	브라우저에 스크롤바를 사용할것인지 여부를 결정함.
	stopPropagation : 논리값(defalut:true)	이벤트 발생시
											하위요소에 이벤트가 전달되는 것을
											차단시킬수 있습니다.
	callback : 익명함수						이벤트가 완전히 완료되면 익명함수에 스크립트 코드가 실행됨.
	transitionEnd : 익명함수					슬라이드 전환이 완전히 완료되면 익명수에 스크립트 코드가 실행됨.	
*/
	
	
	/*터치 슬라이드 비주얼 영역*/
	window.mySwipe = $("#mySwipe").Swipe(
										{
											auto:3000,//배너가 3초간격으로 이동
											continuous:true, //반복되서 롤링되게 하기
											//배너의 슬라이드 이동이 완료될때 마다 중괄호내의 실행문이 실행됩니다.
											//이때 index매개변수에는 노출된 배너 이미지를 포함하는<li>태그의 
											//인덱스 위치값이 할당됩니다.
											callback:function(index,element){//이벤트가 완료되면 실행합니다.
												/*
												 class속성값이 "active"를 포함하는 불릿버튼을 비활성화 버튼이미지로 
												 바꾸고 class속성값 active를 삭제 합니다. 
												 */
												//클래스 속성이 active인 
												//배너 위치 표시 동그라미 이미지<img>태그의 활성화(컬러) 버튼이미지를
												//attr("속성",새값)를 이용해 비활성화된 이미지로 바꿉니다.
												//그리고 removeClass("클래스속성값")를 이용해
												//"active"클래스속성을 삭제 합니다.
												$(".touch_bullet .active")
												.attr("src", $(".touch_bullet .active").attr("src")
														.replace("on.png","off.png"))
														.removeClass("active");
												
												/*index위치에 해당하는 불릿 버튼을 활성화된 버튼이미지로 만들고
												 "active"클래스 속성을 생성합니다.
												*/
												//클래스 속성값이 touch_bullet인 태그의 하위 <img>태그중
												//index위치에 해당하는 <img>태그의 비활성화 불릿 버튼 이미지를선택하여 
												//활성화(컬러)된 버튼 이미지로 바꿉니다.
												//그리고 선택한 이미지 태그에 "active"클래스를 생성합니다.
												$(".touch_bullet img")
												.eq(index)
												.attr("src", $(".touch_bullet img").eq(index).attr("src")
												.replace("off.png","on.png")).addClass("active");		
												}
										}
										)
										.data('Swipe');
    

    
  /*비주얼 이전, 다음 버튼*/
	//class=touch_left_btn인 <p>태그의 하위<a>태그를 클릭했을때..
	//요약 : <이전 이미지 <a>태그 링크를 클릭했을때..
	$(".touch_left_btn a").click(function(){
		//이전 배너이미지로 이동되게 하는 메소드 호출 !
		mySwipe.prev();
		//a링크 기능 차단
		return false;
	});
	
	
	//class=touch_right_btn인 <p>태그의 하위 <a>태그를 클릭했을때.. 
	//요약: >다음 이미지 <a>태그 링크를 클릭했을때..
	$(".touch_right_btn a").click(function(){
		//다음 배너 이미지로 이동하는 메소드 호출
		mySwipe.next();
		//a링크 기능 차단
		return false;
	});
    
  //-----------------------------------------------------------

  /*
   주제 : 자동 롤링 배너와 제어 버튼을 활용한 알림판 만들기
  
   알림판은 일정 시간 간격으로 자동으로 배너 이미지가 바뀌면서 해당하는 배너에 버튼이 활성화 됨.
   이때 버튼을 마우스로 클릭하면 버튼에 해당하는 배너로 이동 됨.
   그리고 정지 ■ 버튼을 누르면 자동으로 넘어가던 배너가 정지되거, 재상 ▶ 버튼을 누르면 다시 배너가 넘어가게 됨 
   */
  
  /*롤링 버튼 배너*/
	//id속성값이 roll_banner_wrap인 <div id="roll_banner_wrap">
	//하위<dd>태그를 선택해 오는데..
	//첫번째 <dd>태그(첫번째 배너)를 제외한 나머지 배너들을 최종적으로 선택해
	//첫번째 배너를 제외하고 숨깁니다.
	//결론 : 사이트를 실행하면 pop_banner_1.gif이미지가 먼저 화면에 나타남
	$("#roll_banner_wrap dd").not(":first").hide();
	
	//첫번째 버튼의 <a>를 onBtn변수에 저장
	var onBtn = $("#roll_banner_wrap dt a:first");
	
	//id가 roll_banner_warp인 
	//<div>태그의 하위 모든 <dt>태그의 하위 모든 <a>태그를 선택해..
	//그중에서 (1~4번 버튼) 하나를 클릭했을때..
	$("#roll_banner_wrap dt a").on("click", function(){
		
		//노출되어 있는 배너 이미지를 숨김.
		$("#roll_banner_wrap dd:visible").hide();
		
		//onBtn변수에 저장된 <a>태그의 하위 <img>를 선택해 ..
		//<img>태그의 src속성값을 비활성화된 이미지경로로 바꿉니다.
		$("img", onBtn).attr("src", $("img",onBtn).attr("src").replace("over.gif","out.gif"));
		//1~4번 버튼중 클릭한 <a>태그의 인덱스 위치값 (0 1 2 3)을 구해 옵니다.
		//예를 들어, 1번 버튼을 클릭했을때.. 변수 num에 0이라는 인덱스 값이 저장되고,
		//			3번 버튼을 클릭했을때..변수 num에 2라는 인덱스 값이 저장됩니다.
		var num = $("#roll_banner_wrap dt a").index(this);
		
		//클릭한 버튼에 해당하는 <a>태그의 인덱스 위치값과 
		//일치하는 <dd>태그 영역(배너이미지)만 나타냅니다.(보여준다.)
		//-> 변수 num에 저장된 클릭한 <a>태그의 인덱스값을 이용해
		//	  그에 해당하는 배너이미지를 포함하는 <dd>태그를 선택합니다.
		//	  그리고 show()메소드를 이용하여 노출 시킵니다.
		$("#roll_banner_wrap dd").eq(num).show();
		
		//클릭한 <a>태그의 하위 <img>태그를 선택해 "src"속성값을 변경하여 
		//활성화된 버튼이미지경로로 바꿉니다.
		$("img",this).attr("src",$("img",this).attr("src").replace("out.gif","over.gif"));
		//(1~4)버튼 이미지를 감싸고 있는 
		//클릭이벤트가 발생한 <a>태그를 onBtn 변수에 저장
		onBtn = $(this);
		
		//<a>에 링크 기능을 차단
		return false;
	});
 
  
  /* 참고 : setTimeout(실행문, 일정한 시간간격)
  autoPlay 함수가 4초 간격으로 호출되어 1~4번 버튼이 순차적으로 강제로 클릭되어 자동으로 배너가 바뀝니다.
  */
	var btnNum = 0;
	
 //함수의 전체 흐름
 //최초 한번은 3초 간격으로 autoPlay함수를 호출하여 실행문을 실행하고,
 //다음번에는 4초 간격으로  반복적으로 재귀함수(autoPaly()함수)를 호출하여 실행문을 실행 시킴.
 
function autoPlay(){ //1~4버튼<a>태그영역이 순서대로 
					 //강제 클릭 이벤트를 발생시키는 함수
	//일정한 간격으로 실행문이 실행될때마다 변수 btnNum의 값이 1씩 증가 되도록
	btnNum++;
	/*
	 Auto 동작 원리에 대해서 알아보자 
	 버튼은 총 4개로, 1번부터 4번까지 차례대로 일정한 간격으로 강제 클릭 이벤트가
	 반복되어 발생된다면 자동으로 배너이미지가 바뀌게 될것입니다.
	 이때 버튼<a>태그의 인덱스 값은 0부터 ~ 3까지입니다.
	 그러므로 일정한 간격으로 인덱스 값이 0부터 1씩 증가되고, 
	 값이 4이상이되면 다시 9로 돌려 강제 이벤트를 발생 시킴. 
	 */
	if(btnNum >=4){ //btnNum변수의 값이 4이상이 되면 
					//btnNum변수의 값을 0으로 초기화함
		btnNum = 0;
	}
	//-변수 btnNum에 저장된 값을 이용해 버튼<a>태그를 차례로 선택해 온 후에
	//trigger("이벤트명")함수를 적용하여 
	//강제로 클릭 이벤트를 발생시키게 합시다.
	//-1~4버튼의 <a>태그가 btnNum변수 값에 따라서 순서대로 선택되고
	// 강제로 클릭되게 하기
	$("#roll_banner_wrap dt a").eq(btnNum).trigger("click");
	//4초 간격으로 재귀함수(autoPlay()함수) 호출이 발생되게 하기
	//그리고 auto1변수에 저장되게 ~
	auto1 = setTimeout(autoPlay ,4000);
}

//최초 로딩시 3초 이후에 function autoPlay(){..}를 실행 합니다.
var auto1 = setTimeout(autoPlay,3000);
 
 
 /* 재생 버튼 ▶ 을 클릭했을때 다시 배너가 일정한 간격으로 바뀌게 하기  */  
 $(".playBtn").on("click",function(){ //재생 버튼을 클릭했을때..
	 //방문자가 재생 버튼▶을 여러번 클릭하게 되면 auto1스택에 있는 
	 //setTimeout()실행문이 여러번 쌓여 문제가 될수 있음.
	 //이문제를 해결하기 위해서 clearTimeout()메소드를 사용해
	 //이미 앞에 지정된 setTimeout()메소드 실행을 삭제한후
	 //setTimeout()메소드를 실행 해야 합니다.
	 //이 결과, 여러번 중복되어 스택에 쌓이는 것을 방지 할수 있게 됨.
	 clearTimeout(auto1);//auto1변수에 할당된 setTimeout()를 제거
	 auto1 = setTimeout(autoPlay, 1000); //1초뒤에 autoPlay함수를 실행
	 
	 //재생버튼의 img태그의 "src"속성값을 활성화된 버튼 이미지로 바꿉니다.
	 $("img",this).attr("src",$("img",this).attr("src").replace("off.gif","on.gif"));
	 //활성화된 정지 버튼의 img태그의 "src"속성값을 비활성화된 버튼 이미지로 바꿈.
	 $(".stopBtn img").attr("src",$("img",".stopBtn img").attr("src").replace("on.gif","off.gif"));
	 
	 //<a>태그 링크 기능 차단
	 return false; 
 });
 
 
  
  
/*
 정지 버튼 ■을 클릭 했을때 일정한 간격으로 함수를 실행하여 버튼을 순차적으로 클릭 되게 하는 setTimeout()메서드를 제거하고,
 정지 버튼 ■을 활성화 시킵니다. 즉, 자동 배너를 정지 시킵니다.  
 */  
$(".stopBtn").on("click",function(){
	
	//auto1변수에 저장되어 있는 setTimeout함수를 제거 합니다.
	clearTimeout(auto1);
	
	//정지버튼이미지를 나타내는 <img>태그의 src속성값을 변경하여
	//활성화된 버튼 이미지로 바꿉니다.
	$("img",this).attr("src",$("img",this).attr("src").replace("off.gif","on.gif"));
	//재생버튼이미지를 나타내는 <img>태그의 src속성값을 변경하여
	//비활성화된 버튼 이미지로 바꿉니다.
	$(".playBtn img").attr("src",$(".playBtn img").attr("src").replace("on.gif","off.gif"));
	//<a>링크기능 차단
	return false;
	
});
  


 //-----------------------------------------------------------
  /*
   주제 : 탭 메뉴를 이용해 최근 게시물 리스트 만들기
  
  - 탭메뉴의 경우 최초 탭버튼인[공지사항]이 활성화되어 보입니다.
        만일 방문자가 [질문과답변]탭을 클릭했을 때는 [공지사항]은 숨겨져야 하고,
    [질문과 답변]의 내용은 활성화되어 보여야 합니다.
    
  - 먼저 탭버튼에 <a>에 on()메서드를 사용하여 mouseover,focus,click이벤트를 등록하였고,
        이벤트 핸들러에는 이벤트가 발생 했을때 마우스를 올린 탭 버튼과 탭에 해당하는 게시물 목록이 활성화되어 보이도록 만들자. 
   */
  
 
  /*탭메뉴*/
	//요약 : 초기에 index.html페이지를 요청해 화면에 index.html응답페이지가 보이면
	//			 첫번째 탭 버튼<a>만 변수 onTab에 할당
	//	id속성값이 tabmenu인 <dl id="tabmenu">태그의 하위 <dt>태그를 모두 선택하여 가져와서
	//	모든 <dt>태그 중에서 <dt>태그의 첫번째 <a>태그를 선택하여 onTab변수에 저장
	var onTab =$("#tabmenu dt a:first"); //[공지사항] 탭
	
	//탭버튼의 <a>태그들(공지사항, 질문과 답변, 저자문의) 중에..
	//하나의 <a>태그에 마우스 포인터가 올리거나, 포커스 또는 클릭했을 경우
	$("#tabmenu dt a").on("mouseover focus click", function(){
		//먼저 현재 노출(:visible) 되어 있는 <dd>태그를 선택하여...
		//게시물 리스트의 <dd>태그를 숨깁니다.
		$("#tabmenu dd:visible").hide();
		//onTab변수에 할당된 [공지사항]탭의 <a>태그의 하위 <img>태그를 선택해
		//<img>태그의 "src"속성값을 모두 가져와서..
		//"over.gif"문자열 부분을 "out.gif"로 치환한 값을 다시 src속성의 값으로 주자
		$("img",onTab).attr("src",$("img",onTab).attr("src").replace("over.gif","out.gif"));
		//마우스 포인터를 올리거나 클릭한 <a>(공지사항, 질문과 답변, 저장문의의 중 하나)
		//태그의 부모 요소인 <dt>태그를 선택해 <dt>태그 다음에 오는 <dd>태그를 선택하여
		//부모 요소인 <dt>태그를 선택해 <dt>태그 다음에 오는 <dd>태그를 선택하여
		//노출시킴
		$(this).parent().next().show();
		
		//이벤트가 발생한 <a>태그 (공지사항, 질문과답변, 저자문의)를
		//onTab변수에 할당
		onTab = $(this);
		$("img",onTab).attr("src",$("img",onTab).attr("src").replace("out.gif","over.gif"));

		return false;
		
	});

 //-----------------------------------------------------------
  
/*  
주제 : 자동 슬라이드 배너 를 이용한 베스트 Book영역   
	 https://bxslider.com/ 접속하여 사용법 보기 
*/
  /* 베스트북 슬라이더 */
	
	//베스트 Book영역 $("best_bg ul")로 목록 태그인 <ul>태그를 선택하여
	//bxSlider()메소드를 호출하여 적용하고 슬라이드 옵션을 지정
	var mySlider=$("#best_bg ul").bxSlider({
							mode:"horizontal",//수평방향(horizontal)으로 슬라이드 나타내기
							speed:500, //이동속도(500:0.5초)
							pager:false, //페이징 표시를 제어(false:숨김, true:노출)
							moveSlides:1,//이동 슬라이드 수 설정
							slideWidth:125, //슬라이드폭
							minSlides:2,//최소 노출 슬라이드 수
							maxSlides:4, //최대 노출 슬라이드 수
							slideMargin:30, //슬라이드 간의 간격입니다.
							auto: true, //자동 슬라이드 여부(true:자동, false:수동)를 설정
							autoHover:true, //마우스 오버시 자동 정지 시키는 설정
											//(true:노출, false:숨김)ㅈㅇ
	});
	
	// <이전 을 클릭할때마다
	$(".prev_btn").on("click",function(){
		//goToPrevSlide()를 이용하여 슬라이드를 한단계 이전으로 이동 되게 하기
		mySlider.goToPrevSlide();
		
		return false;
	});
	
	// >다음 을 클릭할때마다
	$(".next_btn").on("click",function(){
		//goToNextSlide()를 이용하여 슬라이드를 한단계 이전으로 이동 되게 하기
		mySlider.goToNextSlide();
		
		return false;
	});

  
 
  //-----------------------------------------------------------

  /*  
  주제 : 제이쿼리 UI플러그인과 쿠키 플러그인 사용 하기
  - 팝업창을 드래그 하여 이동시키여면, 제이쿼리 UI플러그인을 사용함.
  - [하루동안 이창 열지 않기]버튼 기능을 하용하기 위해서는 쿠키 플러그인을 사용함
  
  참고 : 쿠키 플러그인 사용법
  	       
  	    <쿠키를 생성 하는 기본 사용법>
  	  	 $.cookie("쿠키명","쿠키값",{expires:만료일});
  	  	 설명 : 쿠키명은 나중에 저장된 쿠키의 값을 불러올때 구분하기 위한 이름임.
  	  	           생성된 쿠키는 현재 부터 며칠동안 클라이언트 컴퓨터의 웹브라우저에 보관할건지 만료일(expires)을 지정할수 있음.

			예)
	 	     $.cookie("pop","no",1);
	 	         설명: 브라우저에는 "pop"라는 이름으로 "no"라는 값이 1일 동안 쿠키가 보관 됩니다.
 	         
 	    <쿠키 플러그인을 이용하여  브라우저에 저장된 쿠키를 불러오는 기본 사용법>
 	    $.cookie("쿠키명");
 	    
	 	       저장된 쿠키값인 "no" 불러오는 방법의 예)
	 	    $.cookie("pop");
 	    
 	    <쿠키 플러그인을 이용하여 브라우저에 저장된 쿠키를 삭제하는 기본 사용법>
 	    $.cookie("쿠키명",null);
 	    
	 	    "pop"에 저장된 쿠키값 삭제의 예)
 	         $.cookie("pop",null);
  */
  
   /*팝업 연동*/
	//설명 : 저장된 "pop"에 쿠키값을 검사하여 만일 쿠키값이 저장되어 있지 않으면
	//		숨기져 있던 팝업창이 보이도록함.
	//		그리고 방문객이 [하루동안 이창 열지 않기]를 눌렀을때는 쿠키가 생성되어 하루동안 클라이언트 PC에 보관 되도록함.
	
	//만약 "pop"쿠키값이 "no"가 저장되어 있지 않으면 숨겨져 있던 팝업창 $("#pop_wrap")을 노출~show() 시킵니다.
	if($.cookie("pop") !="no"){
		
		$("#pop_wrap").show();	
	}
	
	//팝업창에 커서를 올리면 커서모양으로 바뀌게 됩니다.
	//그리고 draggable()메소드를 이용하여 마우스로 드래그 했을때.. 이동되도록함.
	$("pop_wrap").css("cursor","move").draggable();
	
	//팝업창 $("pop_wrap")의 하위 <area>태그중 0번째 인덱스 위치에 있는 [창닫기]버튼을 클릭 했을때..
	$("#pop_wrap area:eq(0)").on("click",function(){
		
		//팝업창이 점점 투명해지면서 사라지게 하기 (효과속도 slow)
		$("#pop_wrap").fadeOut("slow");
		
		
		return false;
	
	});
	
	//팝업창 <p id="pop_wrap">태그영역의 하위 <area>태그중 
	//1번째 인덱스 위치에 있는 <area><--[하루동안 창닫기]
	//클릭했을때...
	$("#pop_wrap area:eq(1)").on("click",function(){
		//쿠키를 생성
		//쿠키명 "pop"에 "no"를 저장합니다.
		//만료일(expires)은 오늘로 부터 하루가 지나야 합니다.
		$.cookie("pop","no",{expires:1});
		
		//팝업창은 fadeOut("fast")를 이용하여 팝업창이 점점 투명해지면서 사라지게 됨
		$("#pop_wrap").fadeOut("fast");
		
		return false;
	});


  //크롬(Chrome)브라우저로 쿠키를 확인하는 방법을 알아보도록 해요. 
//  - 개발자도구(F12) 를 연후 Appliecation -> Storage -> Cookies 에서 확인 가능하다.


});