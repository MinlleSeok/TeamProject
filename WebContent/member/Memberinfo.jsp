<%@page import="member.joindto"%>
<%@page import="member.MoimMemberDAO"%>
<%@page import="member.MoimMemberBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
function NUM(){
	document.getElementById("NUM").action = "/deletemoimmemberAction.me";
	document.getElementById("NUM").submit();
}



</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
		request.setCharacterEncoding("UTF-8");

		MemberDAO dao = new MemberDAO();
		joindto dto = new joindto();
		MoimMemberBean bean = new MoimMemberBean();

		int count = dao.getMemberCount(); 
		int memberSize=10;
		 String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * memberSize;		
	 	List<joindto> list = null;
		 if(count > 0){
			list = dao.getMemberList(startRow, memberSize); 
		}  
%>
<body>
<!-- 운영자만 접속가능한 페이지 .. 강퇴기능/회원정보 이름 나이 닉네임 프로필 사진 보여짐 -->
<div id="memberinfo">
	<article>
	<h1>회원목록</h1><h1>[ 모임 가입 회원 수 : <%=count%> ]</h1>
		<div id=memberindex>
		<%
			if(count > 0){%>
				
				<%
				for(int i=0; i<list.size(); i++){
					joindto dto3 = list.get(i);	
				%>
		
			<form action="deletemoimmemberAction1.me?UserCount="<%=dto3.getUserCount()%> method="post">
					<table border="1">
						<tr>
						<td colspan="2">회원사진</td>
						<td>회원이름</td>
						<td>회원아이디</td>
						<td>회원이메일</td>
						<td>회원닉네임</td>
						<td>회원성별</td>
						<td>회원생일</td>
						</tr>
						<tr>
						<td colspan="2" rowspan="3"><%=dto3.getUserPhoto()%></td>
						<td><%=dto3.getUserName()%></td>
						<td><%=dto3.getUserId() %></td>
						<td><%=dto3.getUserEmail()%></td>
						<td><%=dto3.getUserNickname()%></td>
						<td><%=dto3.getUserGender()%></td>
						<td><%=dto3.getUserBirth()%></td>
						</tr>
						<tr>
						<td>회원지역1</td>
						<td>회원지역2</td>
						<td>회원가입일</td>
						<td>회원아이피</td>
						<td>멤버level</td>
						<td>회원간단소개</td>
						</tr>
						<tr>
						<td><%=dto3.getUserDistrict1() %></td>
						<td><%=dto3.getUserDistrict2() %></td>
						<td><%=dto3.getJoinDate() %></td>
						<td><%=dto3.getUserIp() %></td>
						<td><%=dto3.getLevel() %></td>
						<td><%=dto3.getUserText() %></td>
						</tr>
					</table>
					<input type="hidden" value=<%=dto3.getNUM()%> name="NUM">	
					<input type="button" value="<%=dto3.getUserCount()%>번 경고">				
					<input type="Button" value="강퇴하기" onclick="NUM()">
					</form>
			<%		}
				} %>
		<div class="clear"></div>
			<div id="page_control">
			<%
				if(count > 0){ 
					int pageCount = count / memberSize + (count%memberSize==0 ? 0 : 1);
					int pageBlock = 4;
					int startPage = ((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0)) * pageBlock+1;
					int endPage = startPage + pageBlock -1;
					if(endPage < pageCount){
						endPage=pageCount;
					}
					if(startPage>pageBlock){
						%>
						<a href="Memberinfo.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
						<%
					}
					for(int i=startPage; i<=endPage; i++){
						%>
						<a href="Memberinfo.jsp?pageNum=<%=i %>">[<%=i %>]</a>
						<%
					}
					if(endPage<pageCount){
						%>
						<a href="Memberinfo.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
						<%
					}
				}
			%>
			</div>
		</div>
	</article>
</div>
</body>
</html>