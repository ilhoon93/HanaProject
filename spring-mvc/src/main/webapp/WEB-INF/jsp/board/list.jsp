<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/board/css/NewFile.css"/>
</head>
<body>

	<table>
		<tr>
			<th width="7%">번호</th>
			<th>번호</th>
			<th width="16%">번호</th>
			<th width="20%">번호</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
		
			<tr>
				<td width="7%">${board.no }</td>
				<td>${board.title }</td>
				<td width="16%">${board.content }</td>
				<td width="20%">${board.regDate }</td>
			</tr>
		</c:forEach>
			
	</table>

</body>
</html>