<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>

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
		
		var obj = {"bungaeName":bungaeName, "bungaeRef":bungaeRef, "bungaeDate":bungaeDate, "bungaeMax":bungaeMax};
		dbParam = JSON.stringify(obj);
		
		var xhttp;
		
		if (window.XMLHttpRequest) {
			xhttp = new XMLHttpRequest();
		} else {
			xhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xhttp.onreadystatechange = function() {
			if(this.readyState == 4 && this.status == 200) {
				
			}
		};
		xhttp.open("POST", "<%=path%>/insert.bg", true);
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xhttp.send("x="+dbParam);
	}
</script>