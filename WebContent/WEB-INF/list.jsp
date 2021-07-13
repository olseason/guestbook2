<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="com.javaex.dao.GuestbookDao" %>
<%@ page import ="com.javaex.vo.GuestbookVo" %>
<%@ page import ="java.util.List" %>
    

<% //Get Attribute = Controller에서 리스트
	List<GuestbookVo> guestbookList = (List<GuestbookVo>)request.getAttribute("guestList"); 
%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./gbc" method="post">
		<table border="1" width="550">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="10" cols="73" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="add">
	</form>
	<br>
	<%
	for (int i = 0; i < guestbookList.size(); i++) {
	%>
	<table border = "1" width="550">
		<tr>
			<td><%=guestbookList.get(i).getGuestbook_no() %></td>
			<td><%=guestbookList.get(i).getName() %></td>
			<td><%=guestbookList.get(i).getReg_date() %></td>
			<td><a href="./gbc?action=dform&no=<%=guestbookList.get(i).getGuestbook_no() %>">[삭제]</a></td>
		</tr>
		<tr>
			<td colspan ="4"><%=guestbookList.get(i).getContent() %></td>
		</tr>
	</table>
	<br>
	<%
	}
	%>
</body>
</html>