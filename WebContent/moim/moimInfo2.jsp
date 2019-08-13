<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%
	String id = "test2";
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
	
<script type="text/javascript">

	function bungaeInsertToggle() {
		var bungaeInsert = document.getElementById('bungaeInsert');
		
		if(bungaeInsert.style.display === 'none') {
			bungaeInsert.style.display = 'block';
		} else {
			bungaeInsert.style.display = 'none';
		}
	}
	
	function bungaeInsert() {
		
		var bungaeName = document.getElementById("bungaeName").value;
		var bungaeRef = document.getElementById("bungaeRef").value;
		var bungaeDate = document.getElementById("bungaeDate").value;
		var bungaeHour = document.getElementById("bungaeHour").value;
		var bungaeMinute = document.getElementById("bungaeMinute").value;
		var bungaeMax = document.getElementById("bungaeMax").value;
		
		if(bungaeName.length == 0) {
			return;
		} else if (bungaeRef.length == 0) {
			return;
		} else if (bungaeDate.length == 0) {
			return;
		}
		
		var obj = {"bungaeName":bungaeName, 
					"bungaeRef":bungaeRef, 
					"bungaeDate":bungaeDate,
					"bungaeHour":bungaeHour,
					"bungaeMinute":bungaeMinute,
					"bungaeMax":bungaeMax};
		
		dbParam = JSON.stringify(obj);
		
		var xhttp;
		
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		
		
		xhttp.onreadystatechange = function() {
			if(this.readyState == 4 && this.status == 200) {
				
				printBungaeList(<%=mmNum%>);
				
				
				
			}
		};
		xhttp.open("POST", "<%=path%>/insert.bg?t="+ new Date(), true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhttp.send("x="+dbParam);
	}
	
	function printBungaeList(mmNum){
		
		var xhttp;
		
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		
		xhttp.onreadystatechange = function() {
			if(this.readyState == 4 && this.status == 200) {
				
				var objj = JSON.parse(this.responseText);
				var list = document.getElementById("bungaeList");
				list.innerHTML = "";
				var y;
				for (y = 0; y < objj.item.length; y++){
					
					list.innerHTML += "<div class='bungae thunderMoim' id='"+objj.item[y].bungaeNum+"'>"
					+ "<div class='calendarBox'>"
					+ "<span>"+objj.item[y].bungaeDay+"</span><span>"+objj.item[y].bungaeDate+"</span>"
					+ "</div>"
					//+ "<span id='listc"+objj.item[y].bungaeNum+"'><input type='checkbox' id='bungaeJoin"+objj.item[y].bungaeNum+"' onclick='bungaeJoin("+objj.item[y].bungaeNum+")' value='번개참가'></span>"
					+ "<span>"+objj.item[y].bungaeName+"</span> <span class='positionIcon'></span>"
					+ "<span>"+objj.item[y].bungaeRef+"</span>"
					+ "<span class='clockIcon'></span><span>"+objj.item[y].bungaeTime+"</span>"
					
					+ "<span class='personIcon'></span><span><i id='lista"+objj.item[y].bungaeNum+"' style='font-style:normal;'></i> / <i id='bgNum"+objj.item[y].bungaeNum+"' style='font-style:normal;'>"+objj.item[y].bungaeMax+"</i></span>"
					+ "<div id='listc"+objj.item[y].bungaeNum+"'><button onclick='bungaeJoin("+objj.item[y].bungaeNum+")' class='bglabel' id='label"+objj.item[y].bungaeNum+"'>번개 참여</button></div>"
					+ "<div class='profileWrap' id='listb"+objj.item[y].bungaeNum+"'></div>"
					+ "</div>";
					printBungaeUser(mmNum, objj.item[y].bungaeNum);
					
				}

			}
		};
		xhttp.open("POST", "<%=path%>/list.bg?t="+ new Date(), true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhttp.send("mmNum="+mmNum);
		
		
	}

	//var check = false;
	function bungaeJoin(bgNum){
		var la = document.getElementById("label"+bgNum);
		var bg = document.getElementById("bgNum"+bgNum);
		var listaNum = document.getElementById("lista"+bgNum);
		var listcc = document.getElementById("listc"+bgNum);
		//alert(bgNum);
		//alert(check);
		// var aa = document.querySelectorAll("#bungaeJoin"+data+":checked");
	
		if(la.className === "bglabel bgchecked") {
			// check = true;
			// document.getElementById("list"+data).innerHTML = "<img src='../images/sliderdog1.jpg' width='50px' height='50px'>";
			
			la.className = "bglabel";
			var xhttp;
			
			if (window.XMLHttpRequest) {
				xhttp = new XMLHttpRequest();
			} else {
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			var userId = "<%=id%>";
			var mmNum = "<%=mmNum%>";
			xhttp.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					
					printBungaeUser(mmNum, bgNum);
					
				}
			};
			xhttp.open("POST", "<%=path%>/out.bg?t="+ new Date(), true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			xhttp.send("id="+userId+"&mmNum="+mmNum+"&bgNum="+bgNum);
			
		} else {
			la.className = "bglabel bgchecked";
			if(parseInt(bg.innerHTML) > parseInt(listaNum.innerHTML)){
			var xhttp;
			
			if (window.XMLHttpRequest) {
				xhttp = new XMLHttpRequest();
			} else {
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			var userId = "<%=id%>";
			var mmNum = "<%=mmNum%>";
			xhttp.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					
					printBungaeUser(mmNum, bgNum);
					
				}
			};
			xhttp.open("POST", "<%=path%>/join.bg?t="+ new Date(), true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
			xhttp.send("id="+userId+"&mmNum="+mmNum+"&bgNum="+bgNum);
			} else {
				alert("풀방 ㅠㅜ");
				la.className = "bglabel";
			}
		}

	}
	
	function printBungaeUser(mmNum, bgNum){
		
		var xhttp;
		
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		
		xhttp.onreadystatechange = function() {
			if(this.readyState == 4 && this.status == 200) {
				
				var objj = JSON.parse(this.responseText);
				var lista = document.getElementById("lista"+bgNum);
				var listb = document.getElementById("listb"+bgNum);
				var listc = document.getElementById("listc"+bgNum);
				if(objj.item == null) {
					lista.innerHTML = "0";
				} else {
					lista.innerHTML = objj.item.length;
				
				
				listb.innerHTML = "";
				var y;
				for (y = 0; y < objj.item.length; y++){
					
					listb.innerHTML += "<img class='profilePhoto35' src='"+objj.item[y].userPhoto+"' width='50px' height='50px'>";
								
								var checkName = objj.item[y].userName;
								var userId = "<%=id%>";
								
								if (userId === checkName) {
									listc.innerHTML = "<button onclick='bungaeJoin("+objj.item[y].bgNum+")' class='bglabel bgchecked' id='label"+objj.item[y].bgNum+"'>번개 참여</button>";
								}
				
				}
				}
			}
		};
		xhttp.open("POST", "<%=path%>/user.bg?t="+ new Date(), true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhttp.send("mmNum="+mmNum+"&bgNum="+bgNum);
		
		
	}
</script>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript">
var timer;
$(window).scroll(function(){
    var scrolltop = parseInt ( $(window).scrollTop() );
    if( scrolltop >= $(document).height() - $(window).height()){
 
        if( !timer && $(".bungae:last").attr("id") > 1){
             
      //추가할 내용 1
      //실행되고 설정된시간(1000)후에 재실행된다. 1000초이내 중복실행 방지
         
      timer = setTimeout(function() {
        timer = null;
 
        //추가할 내용 2
        //설정된 시간(1000) 후에 실행됨
 		lastPostFunc();
      }, 200);
      //시간은 150~ 으로 알맞은 시간을 찾아야함
        }
    }
});

function lastPostFunc(){
	
	$.post( "<%=path%>/more.bg?t="+ new Date(),
			{idx: $(".bungae:last").attr("id"),
			mmNum: <%=mmNum%>},
			
			function(data){
				if(data != "") {
					
					var objj = JSON.parse(data);
					// alert(data);
					//var list = document.getElementById("bungaeList");
					var data2 = "";
					var mmNum = "<%=mmNum%>";
					var y;
					
					for (y = 0; y < objj.item.length; y++){
						
								data2 += "<div class='bungae thunderMoim' id='"+objj.item[y].bungaeNum+"'>"
								+ "<div class='calendarBox'>"
								+ "<span>"+objj.item[y].bungaeDay+"</span><span>"+objj.item[y].bungaeDate+"</span>"
								+ "</div>"
								//+ "<span id='listc"+objj.item[y].bungaeNum+"'><input type='checkbox' id='bungaeJoin"+objj.item[y].bungaeNum+"' onclick='bungaeJoin("+objj.item[y].bungaeNum+")' value='번개참가'></span>"
								+ "<span>"+objj.item[y].bungaeName+"</span> <span class='positionIcon'></span>"
								+ "<span>"+objj.item[y].bungaeRef+"</span>"
								+ "<span class='clockIcon'></span><span>"+objj.item[y].bungaeTime+"</span>"
								
								+ "<span class='personIcon'></span><span><i id='lista"+objj.item[y].bungaeNum+"' style='font-style:normal;'></i> / <i id='bgNum"+objj.item[y].bungaeNum+"' style='font-style:normal;'>"+objj.item[y].bungaeMax+"</i></span>"
								+ "<div id='listc"+objj.item[y].bungaeNum+"'><button onclick='bungaeJoin("+objj.item[y].bungaeNum+")' class='bglabel' id='label"+objj.item[y].bungaeNum+"'>번개 참여</button></div>"
								+ "<div class='profileWrap' id='listb"+objj.item[y].bungaeNum+"'></div>"
								+ "</div>";
						printBungaeUser(mmNum, objj.item[y].bungaeNum);
					}
					
					$(".bungae:last").after(data2);
				}
			})
	
}
</script>
<script type="text/javascript">printBungaeList(<%=mmNum%>)</script>

</body>
</html>