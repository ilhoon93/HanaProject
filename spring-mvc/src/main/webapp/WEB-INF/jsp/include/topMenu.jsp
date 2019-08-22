<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a id="topBarLogo" href="/">HANA
<span style="color:tomato">1</span><span style="color:mediumseagreen">Q</span>  MOIM</a>
<style>
#inviteMsgLayer{
	display:none;
	background: mediumseagreen;
	text-align: center;
	color:white;
	width:450px;
/* 	min-height:200px; */
	position: fixed;
	top:100px;
	z-index: 20;
	padding: 10px;
	border: 1px solid gray;
}


</style>
<a class="topBarButton" href="#">은행소개</a>
<a class="topBarButton" href="#">고객센터</a>
<a class="topBarButton" href="${pageContext.request.contextPath }/mypage/${userVO.userNo}">마이페이지</a>

<c:if test="${ not empty userVO }">
	<a style="font-family: HanaL; font-size:15px; margin-left:10%;" href="${pageContext.request.contextPath }/mypage/${userVO.userNo}">${userVO.userName}님 반갑습니다.</a>
	<a style="font-family: HanaL; font-size:15px; margin-left:10px;" href="${pageContext.request.contextPath }/user/logout">로그아웃</a>
</c:if>
<div id="inviteMsgLayer" style="display:none">
	<button type="button" >X</button>
	<div id="inviteMsg" style="display:none">

	</div>
	<input type="button" id="inviteAgree" value="수락"/>
	<input type="button" id="inviteDeny" value="거절"/>
</div>
<script>



var socket = null;
$(document).ready(function (){
	connectWS();
	console.log(socket);
	

	
	var msgLayer = $('#inviteMsgLayer');
	var socketAlert = $('div#inviteMsg');
	$("#inviteMsgLayer > button").click(function() {
		msgLayer.css('display','none');
		socketAlert.css('display','none');
//		fListModalLink.focus();
	});
	
});

function connectWS(){
// 	브라우저가 자체 지원
	var ws = new WebSocket("ws://192.168.43.86:8888/inviteEcho?no="+${userVO.userNo});
	socket = ws;
	
// 	커넥션이 연결 되었을 때 
	ws.onopen = function(){
		console.log('Info: connection opened.');
		console.log('socket : ' + socket);
	};
	
	ws.onmessage = function(event){
		console.log("ReceiveMessage:",event.data+'\n');
		var msgLayer = $('#inviteMsgLayer');
		var socketAlert = $('div#inviteMsg');

		msgLayer.css('display','block');
		socketAlert.css('display','block');
		
		var receiveMsg = event.data;
		var strs = receiveMsg.split(',');
		var account = receiveMsg.substring(2,11);
		var printText = receiveMsg.substring(12);
		socketAlert.text(printText);
		
		$("#inviteAgree").click(function (){
			let socketMsg = "inviteAgree, " + account + ", " + ${userVO.userNo};
			socket.send(socketMsg);
			msgLayer.css('display','none');
			socketAlert.css('display','none');
		});
		
		$("#inviteDeny").click(function (){
			msgLayer.css('display','none');
			socketAlert.css('display','none');
		});
		
	};
	
	ws.onclose = function (event) {
		console.log('Info: connection closed');
		// 커넥션이 종료되면 다시 커넥션을 맺는 코드
// 		setTimeout(function(){
// 			connection();
// 		}, 1000);
	};
	ws.onerror = function (err) {
		console.log('Error : ' + err);
	};
};
</script>