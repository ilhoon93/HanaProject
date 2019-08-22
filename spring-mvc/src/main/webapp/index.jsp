<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html  id="indexHtm">
<head>
<meta charset="UTF-8">
<title>HANA 1Q MOIM</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/layout.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/index.css">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/main.js"></script>
<style type="text/css">

header{
	background: rgba(0,0,0,0);
}
header a{
	color: white;
}

.topBarButton:hover{
    border-bottom: 2px white solid;
    padding-bottom: 8px;
}
</style>


<script type="text/javascript">
$("#indexHtm").on('mousewheel',function(e){
    var wheel = e.originalEvent.wheelDelta; 
    if(wheel>0){

      $("header").css('background','rgba(0,0,0,0)');
      $("header > a").css('color','white');
      
      $(".topBarButton:hover").css('border-bottom','2px white solid');
      $(".topBarButton").css('padding-bottom','8px');
//       $(".topBarButton").css('border-bottom','2px white solid');
	} else {
			//스크롤  내릴때 
			$("header").css('background', 'white');
			$("header > a").css('color', 'black');
	
			$(".topBarButton:hover").css('border-bottom', '2px black solid');
			$(".topBarButton").css('padding-bottom', '8px');
		}
	});
</script>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/include/topMenu.jsp"/>
	</header>
	<section>
		<div id="imgContainer">

			<div id="overLay"></div>
			<div id="mainTextDiv">
			
				<h1 id="mainText">
					즐거운 모임, 깔끔한 송금<br/> 
					<span>HANA 
					<span style="color: tomato">1</span><span style="color: mediumseagreen">Q</span> 
					MOIM</span>
				</h1>
				<span style="color: gray"> 간편하게 모아보고 함께 나눠쓰는 모임 통장 </span>
				
<!-- 				로그인 정보 넘겨서 처리 -->
				<form method="post" action="user/login" id="loginForm">
					<h3 style="color: black">로그인</h3>
					<input type="text" id="inputEmail" name="userEmail" class="inputLogin" placeholder="Email" /><br/>
					<input type="password" id="inputPassword" name="userPassword" class="inputLogin" placeholder="Password" /><br /> 
					<input type="submit" id="loginButton" value="시작하기" />
				</form>
			</div>
			
		</div>
	</section>
	
	
	<footer>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"/>
	</footer>
</body>
</html>