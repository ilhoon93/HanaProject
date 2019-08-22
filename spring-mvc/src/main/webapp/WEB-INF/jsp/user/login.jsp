<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	self.location = "/mypage/"+ ${userVO.userNo};
</script>
<h1>
<%-- ${userVO.userNo} --%>
</h1>

</body>
</html>