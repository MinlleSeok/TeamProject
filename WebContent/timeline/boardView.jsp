<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form id="like_form">
		<table id="list">
			<input type="hidden" name="command" value="like_it">
			<input type="hidden" name="board_num" value="${board.num}">
			<tr>
				<input type="button" value="좋아요!" onclick="return like()">
			</tr>
			<tr>
				<div id="like_result">${board.like_it}</div>
			</tr>
		</table>
	</form>

</body>
</html>