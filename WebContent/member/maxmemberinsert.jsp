<%@page import="member.MoimMemberDAO"%>
<%@page import="member.joindto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	joindto dto = new joindto();
	MoimMemberDAO mdao= new MoimMemberDAO();
	int maxmember = mdao.selectmaxmember();
	int point = mdao.selectpoint();
%>
<script type="text/javascript">
function addmaxmember(sdf,abb){
	document.getElementById("addmaxmember").value = sdf;
	document.getElementById("addpoint").value = abb;
	document.fr.submit();
	return true;
}
</script>
<article>
	<form action="maxmemberinsertAction.me" method="post" name="fr">
		<h1>10명 추가시 1000포인트 차감</h1><br>				
		<input type="hidden" value="addmaxmember" name="addmaxmember" id="addmaxmember">
		<input type="hidden" value="addpoint" name="addpoint" id="addpoint">
		
		<span onclick="addmaxmember(10,1000);">10명 추가</span><br>
		<span onclick="addmaxmember(20,1700);">20명 추가</span><br>
		<span onclick="addmaxmember(30,2500);">30명 추가</span><br>
		<span onclick="addmaxmember(40,3000);">40명 추가</span><br>
		<span onclick="addmaxmember(50,3800);">50명 추가</span><br>
		
		<%-- 
		<input type="hidden" value="<%=dto.getMoim_Num()%>" name="Moim_Num">
		<input type="hidden" value="<%=maxmember%>" name="maxmember">
		<input type="hidden" value="<%=point%>" name="point"> --%>
		<%-- <%
		for(int addmaxmember=10; addmaxmember<60; addmaxmember+=10){
			if(addmaxmember==10){
				int addpoint=1000;		
		%>		
			<span onclick="addmaxmember(<%=addmaxmember%>,<%=addpoint%>);"><%=addmaxmember%>명 추가</span><br>
		<%}else if(addmaxmember==20){
			int addpoint=1700;	 %>
			<span onclick="addmaxmember(<%=addmaxmember%>,<%=addpoint%>);"><%=addmaxmember%>명 추가</span><br>
		<%}else if(addmaxmember==30){
			int addpoint=2500;	 %>
			<span onclick="addmaxmember(<%=addmaxmember%>,<%=addpoint%>);"><%=addmaxmember%>명 추가</span><br>
		<%}else if(addmaxmember==40){
			int addpoint=3000;	 %>
			<span onclick="addmaxmember(<%=addmaxmember%>,<%=addpoint%>);"><%=addmaxmember%>명 추가</span><br>
		<%}else if(addmaxmember==50){
			int addpoint=3800;	 %>
			<span onclick="addmaxmember(<%=addmaxmember%>,<%=addpoint%>);"><%=addmaxmember%>명 추가</span><br>
		<%} %>
		<%} %> --%>
		
	</form>
</article>

</body>
</html>