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
%>
<script type="text/javascript">
function addmaxmember(sdf){
	alert(sdf);
	document.getElementById("addmaxmember").value = sdf;
	document.getElementById("point").value = sdf;
	document.fr.submit();
	return true;
}
</script>
<article>
	<form action="maxmemberinsertAction.me" method="post" name="fr">
		<h1>10명 추가시 1000포인트 차감</h1><br>
		<input type="hidden" value="<%=dto.getMoim_Num()%>" name="Moim_Num">	
		<input type="hidden" value="<%=maxmember%>" name="maxmember">
		<input type="hidden" value="addmaxmember" name="addmaxmember" id="addmaxmember">
		<input type="hidden" value="<%=dto.getPoint()%>" name="point">
		<%
		for(int addmaxmember=10; addmaxmember<60; addmaxmember+=10){
			if()
		%>		
		<span onclick="addmaxmember(<%=addmaxmember%>), addmaxmember(<%=point%>);"><%=addmaxmember%>명 추가</span><br>
		<span onclick=><%=point%></span>
		<%} %>
		
	</form>
</article>

</body>
</html>