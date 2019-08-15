<%@page import="member.MoimMemberBean"%>
<%@page import="member.MoimMemberDAO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
	request.setCharacterEncoding("UTF-8");
	%>    
		<jsp:useBean id="Bean" class="member.MoimMemberBean"/>
		<jsp:setProperty property="*" name="Bean"/>
	<%
	
	int Moim_Num = Integer.parseInt(request.getParameter("Moim_Num"));
	int Usernum = Integer.parseInt(request.getParameter("Usernum"));
	
	MoimMemberDAO dao = new MoimMemberDAO();
	
	int insert = dao.insertMoimMember(Moim_Num, Usernum);
		if(insert==1){
%>	
	
	<script type="text/javascript">
		alert("추가 완료");
		location.href="MoimMemberjoin.jsp?Moim_Num=<%=Moim_Num%>&UserNum=<%=Usernum%>";		
	</script>
<%}%>













