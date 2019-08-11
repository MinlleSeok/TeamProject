<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<% String path = request.getContextPath(); %>
<% String id = "test"; 
	String mmNum = "1";%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div>
	<div id="bungaeButton">
		<input type="button" name="bungae" id="bungae" value="번개개설" onclick="bungaeInsertToggle()">
	</div>
	<div id="bungaeInsert" style="display:none;">
		<ul>
			<li><input type="text" name="bungaeName" id="bungaeName" placeholder="번개명"></li>
			<li style="display:none;"><p id="bungaeNameP"></p></li>
			<li><input type="text" name="bungaeRef" id="bungaeRef" placeholder="번개 간략 설명"></li>
			<li><input type="date" name="bungaeDate" id="bungaeDate"> </li>
			<li>
			<select id="bungaeMax">
					<%
						for (int i = 1; i < 16; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
			</select>
			</li>
			</ul>
		<input type="button" value="번개!" onclick="bungaeInsert()">
	</div>
	<div id="bungaeList">
	
	</div>
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
					
					list.innerHTML += "<ul class='bungae' id='"+objj.item[y].bungaeNum+"'>"
								+ "<li id='listc"+objj.item[y].bungaeNum+"'><input type='checkbox' id='bungaeJoin"+objj.item[y].bungaeNum+"' onclick='bungaeJoin("+objj.item[y].bungaeNum+")' value='번개참가'></li>"
								+ "<li>"+objj.item[y].bungaeName+"</li>"
								+ "<li>"+objj.item[y].bungaeRef+"</li>"
								+ "<li>"+objj.item[y].bungaeDate+"</li>"
								+ "<li><span id='lista"+objj.item[y].bungaeNum+"'></span> / "+objj.item[y].bungaeMax+"</li>"
								+ "<li id='listb"+objj.item[y].bungaeNum+"'></li>"
								+ "</ul>";
					printBungaeUser(mmNum, objj.item[y].bungaeNum);
				}

			}
		};
		xhttp.open("POST", "<%=path%>/list.bg?t="+ new Date(), true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhttp.send("mmNum="+mmNum);
		
		
	}
	
	function bungaeJoin(bgNum){
		var bj = document.getElementById("bungaeJoin"+bgNum);
		
		// var aa = document.querySelectorAll("#bungaeJoin"+data+":checked");
	
		if(bj.checked == true) {
			
			// document.getElementById("list"+data).innerHTML = "<img src='../images/sliderdog1.jpg' width='50px' height='50px'>";
			
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
					
					listb.innerHTML += "<span>"
								+ "<img src='"+objj.item[y].userPhoto+"' width='50px' height='50px'>"
								+ "<span>";
				
								var checkName = objj.item[y].userName;
								var userId = "<%=id%>";
								
								if (userId === checkName) {
									listc.innerHTML = "<input type='checkbox' id='bungaeJoin"+bgNum+"' onclick='bungaeJoin("+bgNum+")' value='번개참가' checked>";
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
						
								data2 += "<ul class='bungae' id='"+objj.item[y].bungaeNum+"'>"
									+ "<li id='listc"+objj.item[y].bungaeNum+"'><input type='checkbox' id='bungaeJoin"+objj.item[y].bungaeNum+"' onclick='bungaeJoin("+objj.item[y].bungaeNum+")' value='번개참가'></li>"
									+ "<li>"+objj.item[y].bungaeName+"</li>"
									+ "<li>"+objj.item[y].bungaeRef+"</li>"
									+ "<li>"+objj.item[y].bungaeDate+"</li>"
									+ "<li><span id='lista"+objj.item[y].bungaeNum+"'></span> / "+objj.item[y].bungaeMax+"</li>"
									+ "<li id='listb"+objj.item[y].bungaeNum+"'></li>"
									+ "</ul>";
						printBungaeUser(mmNum, objj.item[y].bungaeNum);
					}
					
					$(".bungae:last").after(data2);
				}
			})
	
}
</script>
<script type="text/javascript">printBungaeList(<%=mmNum%>)</script>