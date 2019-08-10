<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<% String path = request.getContextPath(); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
/* The switch - the box around the slider */
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>
<div>
	<div id="bungaeButton">
		<input type="button" name="bungae" id="bungae" value="번개개설" onclick="bungaeInsertToggle()">
	</div>
	<div id="bungaeInsert" style="display:none;">
		<ul>
			<li><input type="text" name="bungaeName" id="bungaeName" placeholder="번개명"></li>
			<li><p id="bungaeNameP"></p></li>
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
		
		var bungaeName = document.getElementById('bungaeName').value;
		var bungaeRef = document.getElementById('bungaeRef').value;
		var bungaeDate = document.getElementById('bungaeDate').value;
		var bungaeMax = document.getElementById('bungaeMax').value;
		
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
				
				printaa("1");
				
				
				
			}
		};
		xhttp.open("POST", "<%=path%>/insert.bg?t="+ new Date(), true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhttp.send("x="+dbParam);
	}
	
	function printaa(data){
		
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
				list.innerHTML = '';
				var y;
				for (y = 0; y < objj.item.length; y++){
					
					list.innerHTML += '<ul id="list'+objj.item[y].bungaeNum+'">';
					list.innerHTML += '<li>번개참가<label class="switch"><input type="checkbox" id="bungaeJoin'+objj.item[y].bungaeNum+'" onsubmit="bungaeJoin('+objj.item[y].bungaeNum+')"><span class="slider round"></span></label></li>';
					list.innerHTML += '<li>'+objj.item[y].bungaeName+'</li>';
					list.innerHTML += '<li>'+objj.item[y].bungaeRef+'</li>';
					list.innerHTML += '<li>'+objj.item[y].bungaeDate+'</li>';
					list.innerHTML += '<li>'+objj.item[y].bungaeMax+'</li>';
					list.innerHTML += '</ul>';
				}

			}
		};
		xhttp.open("POST", "<%=path%>/list.bg?t="+ new Date(), true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		xhttp.send("x="+data);
		
		
	}
	
	function bungaeJoin(data){
		var bj = document.getElementById('bungaeJoin'+data);
	
		if(bg.checked == true) {
			document.getElementById('list'+data).innerHTML += 'ㅋㄷ';
		} else {
			bg.disabled = false;
		}
	}
</script>
<script type="text/javascript">printaa("1")</script>