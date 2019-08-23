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
		MoimMemberBean bean = new MoimMemberBean();

		int count = dao.getMemberCount();
		int count1 = dao.getMemberCount1();
		
		int memberSize=10;
		int memberSize1=10;
		 String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		String pageNum1 = request.getParameter("pageNum1");
		if(pageNum1 == null){
			pageNum1 = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int currentPage1 = Integer.parseInt(pageNum1);
		int startRow = (currentPage - 1) * memberSize;	
		int startRow1 = (currentPage1 - 1) * memberSize1;
	 	List<joindto> list = null;
	 	List<joindto> list1 =null;
	 	if(count1 > 0){
			list1 = dao.getMemberList1(startRow, memberSize); 
		}  
		 if(count > 0){
			list = dao.getMemberList(startRow, memberSize); 
		}  
		 int level=dto.getLevel();
		// if(level>=1){
		int enter= dto.getEnter();
			 System.out.println(count1);
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
					<% 
					//if(level>=2){
					
						%>
						<form action="deletemoimmemberAction.me" method="post">
							<input type="hidden" value=<%=dto3.getNUM()%> name="NUM">
							<a href="neiBoardDelete.jsp?usercount=<%=dto3.getUsercount()%>&pageNum=<%=pageNum%>"
							onclick="return confirm('정말 강퇴 하시겠습니까?')">		
							<input type="submit" value="강퇴하기">
							</a>
						</form>
						<form action="deletemoimmemberAction1.me?usercount=<%=dto3.getUsercount()%>" method="post">	
							<input type="hidden" value=<%=dto3.getNUM()%> name="NUM1">					
							<a href="neiBoardDelete.jsp?usercount=<%=dto3.getUsercount()%>&pageNum=<%=pageNum%>"
							onclick="return confirm('정말 경고 주시겠습니까?')"><input type="submit" value="<%=dto3.getUsercount()%>번 경고"></a>	
						</form>
							<%//if(level==3){ %>
							<form action="moimmemberlevelAction.me?Level=<%=dto3.getLevel()%>" method="post">
								<input type="hidden"value=<%=dto3.getNUM()%> name="NUM">
								<a href="neiBoardDelete.jsp?usercount=<%=dto3.getUsercount()%>&pageNum=<%=pageNum%>"
							onclick="return confirm('정말 등업 시키시겠습니까?')">
							<input type="submit" value="levelup">	
							</a>					
							</form>
						<%	//	}
						//	}						
						}
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
						<a href="Memberinfo.jsp?pageNum=<%=i %>">[<%=i %>]</a>
						<%
					}
					if(endPage<pageCount){
						%>
						<a href="Memberinfo.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>
						<%
					}
				
				}
			
	//	 }
			%>
			</div>
		</div>	
		<% 
		%>
	<!-- 
			enter 컬럼 추가
			
			list count 추가 
			기존 list count where절 enter 0/1에 따른 조건 추가 2선택 시 회원가입 불가 추가. -->
			
			<h1>회원대기목록</h1><h1>[ 모임 가입 회원 대기 수 : <%=count1%> ]</h1>
			<div id=memberindex>
			<%
				if(count1 > 0){%>
					
					<%
					for(int i=0; i<list1.size(); i++){
						joindto dto3 = list1.get(i);	
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
					<% 
					//if(level>=2){
					
					 %>
							<form action="moimmemberenterAction.me?enter=<%=dto3.getEnter()%>" method="post">
								<input type="hidden"value=<%=dto3.getNUM()%> name="NUM">
								<input type="submit" value="가입승인">				
							</form>
						<%	//	
						//	}						
						}
					}
				 %>
		<div class="clear"></div>
			<div id="page_control">
			<%
				if(count1 > 0){ 
					int pageCount1 = count1 / memberSize1 + (count1%memberSize1==0 ? 0 : 1);
					int pageBlock1 = 4;
					int startPage1 = ((currentPage1/pageBlock1)-(currentPage1%pageBlock1==0?1:0)) * pageBlock1+1;
					int endPage1 = startPage1 + pageBlock1 -1;
					if(endPage1< pageCount1){
						endPage1=pageCount1;
					}
					if(startPage1>pageBlock1){
						%>
						<a href="Memberinfo.jsp?pageNum=<%=startPage1-pageBlock1%>">[이전]</a>
						<%
					}
					for(int i=startPage1; i<=endPage1; i++){
						%>
						<a href="Memberinfo.jsp?pageNum=<%=i %>">[<%=i %>]</a>
						<%
					}
					if(endPage1<pageCount1){
						%>
						<a href="Memberinfo.jsp?pageNum=<%=startPage1+pageBlock1%>">[다음]</a>
						<%
					}
				
				}
	//	 }
			%>			
			</div>
		</div> 
	</article>
</div>
</body>
</html>