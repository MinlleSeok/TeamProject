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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
		request.setCharacterEncoding("UTF-8");

		MemberDAO dao = new MemberDAO();
		joindto dto = new joindto();

		int count = dao.getMemberCount(); 
		int memberSize=20;
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
			if(count > 0){
				for(int i=0; i<list.size(); i++){
					joindto dto3 = list.get(i);	
		%>
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
				<td colspan="2" rowspan="3"><%=dto.getUserPhoto()%></td>
				<td><%=dto.getUserName()%></td>
				<td><%=dto.getUserId() %></td>
				<td><%=dto.getUserEmail() %></td>
				<td><%=dto.getUserNickname() %></td>
				<td><%=dto.getUserGender() %></td>
				<td><%=dto.getUserBirth() %></td>
				</tr>
				<tr>
				<td>회원지역1</td>
				<td>회원지역2</td>
				<td>회원가입일</td>
				<td>회원아이피</td>
				<td><a href="../member/memberlevel.jsp"
						onclick="window.open(this.href, '_blank', 'width=100px,height=100px,toolbars=no,menubar=no,location=no,scrollbars=no,status=no,resizeable=no');return false;">
						멤버level</a></td>
				<td>회원간단소개</td>
				</tr>
				<tr>
				<td><%=dto.getUserDistrict1() %></td>
				<td><%=dto.getUserDistrict2() %></td>
				<td><%=dto.getJoinDate() %></td>
				<td><%=dto.getUserIp() %></td>
				<td><%=dto.getLevel() %></td>
				<td><%=dto.getUserText() %></td>
				</tr>
			</table>
			<%
				}
			}else{
			%>
				<tr>
					<td> 가입된 회원이 없습니다.</td>
				</tr>
				<%
				 
			}
		
		%>
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
						<a href="memberinfo.jsp?pageNum=<%=i %>">[<%=i %>]</a>
						<%
					}
					if(endPage<pageCount){
						%>
						<a href="memberinfo.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
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