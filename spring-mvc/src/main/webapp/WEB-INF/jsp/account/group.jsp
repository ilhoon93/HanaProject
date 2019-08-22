<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANA 1Q MOIM</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/layout.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/groupPage.css">
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/main.js"></script>
<!-- 데이트피커 -->
<script type="text/javascript" src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>

<div id="moneyRequestMsgLayer" style="display:none">
	<button type="button" >X</button>
	<div id="moneyRequestMsg" style="display:none">
	</div>
	<input type="button" id="moneyRequestAgree" value="출금확인"/>
	<input type="button" id="moneyRequestDeny" value="거절"/>
</div>
<script>

//그룹에 해당하는 웹소켓 (돈 출금 요청, 알림을 위한)
function connectMoim(){
	var moim_ws = new WebSocket("ws://192.168.43.86:8888/moneyRequestEcho");
	moim_ws.onopen = function(){
		console.log('Info: connection opened.');
		console.log('socket : ' + moim_ws);
	};
	
	moim_ws.onmessage = function(event){
		console.log("ReceiveMessage:",event.data+'\n');
		var msgLayer = $('#moneyRequestMsgLayer');
		var socketAlert = $('div#moneyRequestMsg');
		msgLayer.css('display','block');
		socketAlert.css('display','block');
		
		// 그룹에 접속했을 때 받는 메시지 (출금 요청이 있다면)
		var receiveMsg = event.data;
		var strs = receiveMsg.split(',');
		var msg = strs[0];
		var account = strs[1];
		var money = strs[2];
		var title = strs[3];
		socketAlert.text(msg);
		
		$("#moneyRequestAgree").click(function (){
			// 출금 동의 여부, 보낼 계좌, 보내는 사람, 금액, 보내는 계좌, 적요
			let socketMsg = "agree,"+account+","+ ${userVO.userNo} +","+money+","+${linkedAccount }+","+title;
			moim_ws.send(socketMsg);
			msgLayer.css('display','none');
			socketAlert.css('display','none');
		});
		
		$("#moneyRequestDeny").click(function (){
			msgLayer.css('display','none');
			socketAlert.css('display','none');
		});
		
		// 출금 알림창 끄기
		$("#moneyRequestMsgLayer > button").click(function() {
			msgLayer.css('display','none');
			socketAlert.css('display','none');
		});
	};
	
	moim_ws.onclose = function (event) {
			console.log('Info: connection closed');
			// 커넥션이 종료되면 다시 커넥션을 맺는 코드
//	 		setTimeout(function(){
//	 			connection();
//	 		}, 1000);
	};
	moim_ws.onerror = function (err) {
			console.log('Error : ' + err);
	};
};
$(document).ready(function() {
	// 데이트피커 옵션
	$("#requestMoneyDate").datepicker({
		dateFormat : 'yy-mm-dd',
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		dayNames:['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
		minDate:"+1D",
		maxDate:"+3M"
	});
	
	// 페이지 로딩 시 모임 소켓 연결
	connectMoim();
	
	
	// 출금 요청을 보내는 코드
	function sendMoneyList(){
		var requestedMoneyList = [];
		
		$("input[name='getPayList':checked").each(function(i) {
			requestedMoneyList.push($(this).val());
		});
		
		var objParams = {
			"trcTitle" 	: $("#requestMoneyTitle").val(),
			"trcAmount"	: $("#requestMoneyAmount").val(),
			"trcDate"	: $("#requestMoneyDate").val(),
			"getPayList": requestedMoneyList
		};
		
		//ajax 호출
		$.ajax({
			url				:	"/sendMoneyRequest",
			dataType		:	"json",
			contentType		:	"application/x-www-form-urlencoded; charset=UTF-8",
			type			:	"post",
			data			:	objParams,
			success			:	function(retVal){
				console.log(retVal);
				alert('성공');
// 				if(retVal.code == "OK"){
// 					alert(retVal.message);
// 				}else{
// 					alert(retVal.message);
// 				}
			},
			error			:	function(request, status, error){
				console.log("AJAX_ERROR");
			}
		});
	}
});


$(function(){
	// 친구가 이미 모임원인지 체크하는 함수
	function isInMoim(value){
		var moimList = new Array();
		<c:forEach items="${moimUserList}" var="user">
			moimList.push("${user.userNo}");
		</c:forEach>
		for(var i = 0; i<moimList.length;i++){
			if(value == moimList[i]){
				return true;
			}
		}
		return false;
	}
	
	
	$(".inviteBtn").click(function (){
		var inviteUserNo = $(this).val();
		isInMoim(inviteUserNo); // 이미 초대된 멤버인지 확인

		// 현재 모임 멤버가 아닌 친구 들중
		if(socket){
			if(!isInMoim(inviteUserNo)){
				let socketMsg = "invite, " + ${accountVO.accountOwnerNo} + ", " + inviteUserNo + ", " + ${accountVO.accountNo};
				console.log("invite::socket", socket);
				console.log("were",socketMsg);
				socket.send(socketMsg);
			}else{
				alert('이미 모임에 초대되어있습니다.');
			}
		}else{
		}
	});
	
	// 눌렀을 때 파일 업로드 폼 띄우기
// 	for(var i = 0; i<100; i++){
// 		$("#uploadFileBtn"+String(i)).click(function (){
// 			$("#fileUploadDiv"+String(i)).css('display','block');
// 		});
// 		$("#fileUploadFormCloseBtn"+String(i)).click(function (){
// 			$("#fileUploadDiv"+String(i)).css('display','none');
// 		});	
// 	}
	$("#uploadFileBtn1").click(function (){
		$("#fileUploadDiv1").css('display','block');
	});
	
	$("#fileUploadFormCloseBtn1").click(function (){
		$("#fileUploadDiv1").css('display','none');
	});
	
	$("#uploadFileBtn2").click(function (){
		$("#fileUploadDiv2").css('display','block');
	});
	
	$("#fileUploadFormCloseBtn2").click(function (){
		$("#fileUploadDiv2").css('display','none');
	});		
	
	$("#uploadFileBtn3").click(function (){
		$("#fileUploadDiv3").css('display','block');
	});
	
	$("#fileUploadFormCloseBtn3").click(function (){
		$("#fileUploadDiv3").css('display','none');
	});	
	$("#uploadFileBtn4").click(function (){
		$("#fileUploadDiv4").css('display','block');
	});
	
	$("#fileUploadFormCloseBtn4").click(function (){
		$("#fileUploadDiv4").css('display','none');
	});	
	
});
</script>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/include/topMenu.jsp"/>
	</header>
	
	<section>
		<div id="requestMoneyLayer" class="modalLayer">
		  <div id="requestMoneyContent" class="modalContent">
  			<div style="background: #464964; color:white; padding:5px; margin-bottom:5px;">
				${accountVO.accountNo} 모임 회비 걷기
			</div>
				<button type="button">X</button>
				<form method="post" action="/sendMoneyRequest">
				<table style="text-align: center; ">
					<tr>
						<th>번호</th>
						<th>이름</th>
						<th>회비 걷기</th>
					</tr>
					<c:forEach items="${moimUserList}" var="user" varStatus="status">
						<tr>
							<td width="5%">${status.index +1 }</td>
							<td width="30%"><a>${user.userName }</a></td>
							<td width="10%"><input type="checkbox" id="requestMoneyList[]" name="getPayList" value="${user.userNo }" 
												class="checkSelect" style="width:25px; height:25px"></td>
						</tr>
					</c:forEach>
					
					<tr style="border-top:1px solid black;text-align:left; padding-top:50px"><td colspan="3" style="padding-top:20px">출금 사유를 입력하세요<input style="margin-left:40px; margin-right:10px" type="text" name="trcTitle" maxlength="20"/></td></tr>
					<tr style="text-align:left"><td colspan="3">요청할 금액을 입력하세요
					<input style="margin-left:20px; margin-right:10px"type="number" id="requestMoneyAmount" name="trcAmount" maxlength="20"/></td></tr>
					<tr style="text-align:left"><td colspan="3">기한 날짜를 입력하세요
					<input id="requestMoneyDate" name="trcDate" style="margin-left:35px;"/></td></tr>
					<tr style="text-align:right"><td colspan="3">
							<input type="submit" value="확인" style="background: #464964; color:white; padding:5px; margin-left:12px" onclick="return sendMoneyList()"/>
					</td></tr>
				</table>
				<input type="hidden" name="trcAccountNo" value="${accountVO.accountNo}"/>
				</form>
		  </div>
		</div>
	
	<div id="fListModalLayer" class="modalLayer">
		  <div id="fListModalContent" class="modalContent">
		  			<div style="background: #464964; color:white; padding:5px; margin-bottom:5px;">
						${userVO.userName} 님의 친구 목록
					</div>
		    <button type="button">X</button>
		    <table style="text-align:center">
			    <tr>
				    <th>번호</th>
				    <th>이름</th>
				    <th>초대하기</th>
			    </tr>
		    	<c:forEach items="${fNameList}" var="friends" varStatus="status">
			    	<tr>
			    		<td width="5%">
			    			${status.index +1 }
			    		</td>
			    			
			    		<td width="30%">
			    			<a>${friends.userName }</a>
			    		</td>
			    		<td width="20%">
			    			<input type="button" id="inviteModalLink" name="addFriendsBtn" class="inviteBtn"
								value="${friends.userNo }"/>
			    		</td>
			    	</tr>
		    	</c:forEach>
		    </table>
		  </div>
		</div>
	
	
		<c:choose>
			<c:when test="${accountVO.accountOwnerNo eq userVO.userNo}">
				<div id="gListModalLayer" class="modalLayer">
					<div id="gListModalContent" class="modalContent">
					<div style="background: #464964; color:white; padding:5px; margin-bottom:5px;">
						${accountVO.accountNo} 모임원 목록
					</div>
						<button type="button">X</button>
						<table style="text-align: center">
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>독촉하기</th>
							</tr>
							<c:forEach items="${moimUserList}" var="user" varStatus="status">
								<tr>
									<td width="10%">${status.index +1 }</td>
									<td width="30%"><a>${user.userName }</a></td>
									<td width="20%" style="text-align:left;">
										<a>${user.userEmail }</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<aside>
					<div id="profileButton">
						<div id="groupInfo">
							<img src="/resources/images/blank_profile.png" id="ownerProfileImage"/><br/><br/>
							관리자 : ${userVO.userName } &nbsp; <br/><br/>
							<span style="font-family: sans-serif; font-size:15px">이자율 : ${accountVO.accountRate }%</span><br /><br /><br />
							<span style="font-size: 35px"><fmt:formatNumber value="${accountVO.accountTotalAmount }" pattern="#,###" />원 <br/></span>
						</div>
						<br/><br/>
						<a class="myPageButton" id="requestMoney" href="#">회비걷기</a><br /> <br />
						<a class="myPageButton" id="gListModalLink" href="#">전체 모임원 보기</a><br /> <br />
						<a class="myPageButton" id="fListModalLink" href="#">신규 모임원 추가</a><br /> <br />
						<a class="myPageButton" id="" href="${pageContext.request.contextPath }/account/group/${accountVO.accountNo}/chat">그룹채팅</a><br /> <br />
					</div>
				</aside>
				<article>
					<div id="trcHeader">
						<span id="groupTitle" style="font-size: 30px">★나의 그룹 페이지</span><span id="accountNo">${accountVO.accountNo }</span>
					</div>
					<div style="border-bottom:1px solid lightgray; border-right:1px solid lightgray; background: white; 
								color: black; padding-top:5px; padding-bottom:5px; font-weight: bold">
						입금내역 <span style="margin-left:400px">출금내역</span>
					</div>
					<div id="trcHistory">
						<div id="depositDiv">
							<table class="trcListTable">
								<tr style="text-align:center; background:black; color:white">
									<th width="15%" style="padding-top:5px; padding-bottom:5px">날짜</th>
									<th width="10%" style="padding-top:5px; padding-bottom:5px">보낸이</th>
									<th width="35%" style="padding-top:5px; padding-bottom:5px">적요</th>
									<th width="15%" style="padding-top:5px; padding-bottom:5px">금액</th>
								</tr>
								<tbody>
								<c:forEach items="${trcList }" var="trc" varStatus="status">
									<c:choose>
										<c:when test="${trc.trcReceiveAccount eq accountVO.accountNo}">
											<tr>
												<td>${trc.trcDate }</td>
												<td>${trc.trcSenderName }</td>
												<td style="font-family: bold">${trc.trcTitle }</td>
												<td style="color:blue">
													+<fmt:formatNumber value="${trc.trcAmount }" pattern="#,###" />
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr><td><br/></td></tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="withdrawlDiv">
							<table class="trcListTable">
								<tr style="text-align:center; background:black; color:white">
									<th width="30%" style="padding-top:5px; padding-bottom:5px">날짜</th>
									<th width="50%" style="padding-top:5px; padding-bottom:5px">적요</th>
									<th width="130px" style="padding-top:5px; padding-bottom:5px">금액</th>
								</tr>
								<tbody>
									<c:forEach items="${trcList }" var="trc" varStatus="status">

										<c:choose>
											<c:when test="${trc.trcReceiveAccount eq accountVO.accountNo}">
												
												<tr><td><br/></td></tr>
											</c:when>

											<c:otherwise>
												<tr>
													<td>${trc.trcDate }</td>
													<td style="font-family: bold">
													<div class="fileUploadDiv" id="fileUploadDiv${trc.trcNo }" style="display:none">
														<button style="float:right;" class="fileUploadFormCloseBtn${trc.trcNo }" id="fileUploadFormCloseBtn${trc.trcNo }">X</button>
														<h4>영수증 업로드</h4>
														<img width="200px" height="200px" src="${ pageContext.request.contextPath }/resources/file/${trc.trcServerFile}"/>
														<form method="post" action="${pageContext.request.contextPath }/account/group/${accountVO.accountNo}/${userVO.userNo }/file/${trc.trcNo}" enctype="multipart/form-data">
															<input type="file" name="attachfile">
															<input type="submit" value="업로드" style="border:none; background: cornflowerblue; color:white; float:right;">
														</form>
													</div>
														${trc.trcTitle }<button style="border:none; background:white; margin-left:5px" id="uploadFileBtn${trc.trcNo }" class="uploadFileBtn">영수증</button></td>
													<td style="color:red">-
														<fmt:formatNumber value="${trc.trcAmount }" pattern="#,###" />
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</article>
			</c:when>
			<c:otherwise>
			
				<div id="gListModalLayer" class="modalLayer">
					<div id="gListModalContent" class="modalContent">
					<span>${accountVO.accountNo} 모임원 목록</span>
						<button type="button">X</button>
				<table style="text-align: center">
					<tr>
						<th>번호</th>
						<th>이름</th>
					</tr>
					<c:forEach items="${moimUserList}" var="user" varStatus="status">
						<tr>
							<td width="10%">${status.index +1 }</td>
							<td width="30%"><a>${user.userName }</a></td>

						</tr>
					</c:forEach>
				</table>
				</div>
				</div>
				<aside>
					<div id="profileButton">
						<div id="groupInfo" style="background:tomato;">
							<img src="/resources/images/blank_profile.png" id="ownerProfileImage"/><br/><br/>
							관리자 : ${accountVO.accountOwnerNo } &nbsp; 
							<span style="font-family: sans-serif; font-size:15px">이자율 : ${accountVO.accountRate }%</span><br /><br /><br />
							<span style="font-size: 35px"><fmt:formatNumber value="${accountVO.accountTotalAmount }" pattern="#,###" />원 <br/>	</span>
						
						</div>
						<br/>
						<br/>
						<a class="myPageButton" id="gListModalLink" href="#">전체 모임원 보기</a><br /> <br /> 
						<a class="myPageButton" id="" href="${pageContext.request.contextPath }/account/group/${accountVO.accountNo}/chat">그룹채팅</a><br /> <br />

					</div>
				</aside>
					<article>
					<div id="trcHeader">
						<span id="groupTitle" style="font-size: 30px">★나의 그룹 페이지</span><span id="accountNo">${accountVO.accountNo }</span>
					</div>
					<div style="border-bottom:1px solid lightgray; border-right:1px solid lightgray; background: white; 
								color: black; padding-top:5px; padding-bottom:5px;">
						입금내역 <span style="margin-left:400px">출금내역</span>
					</div>
					<div id="trcHistory">
						<div id="depositDiv">
							<table class="trcListTable">
								<tr style="text-align:center; background:cornflowerblue">
									<th width="15%">날짜</th>
									<th width="10%">보낸이</th>
									<th width="35%">적요</th>
									<th width="15%">금액</th>
								</tr>
								<tbody>
								
								<c:forEach items="${trcList }" var="trc" varStatus="status">
									<c:choose>
										<c:when test="${trc.trcReceiveAccount eq accountVO.accountNo}">
											<tr>
												<td>${trc.trcDate }</td>
												<td>${trc.trcSenderName }</td>
												<td style="font-family: bold">${trc.trcTitle }</td>
												<td style="color:blue">
													+<fmt:formatNumber value="${trc.trcAmount }" pattern="#,###" />
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr><td><br/></td></tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="withdrawlDiv">
							<div class="fileUploadDiv">
								<button style="float:right;"id="fileUploadFormCloseBtn">X</button>
								<h4>영수증 업로드</h4>
								<form method="post" action="${pageContext.request.contextPath }/account/group/${accountVO.accountNo}/${userVO.userNo }/file" enctype="multipart/form-data">
									<input type="file" name="attachfile">
									<input type="submit" value="업로드" style="border:none; background: cornflowerblue; color:white; float:right;">
								</form>
							</div>
							<table class="trcListTable">
								<tr style="text-align:center; background:cornflowerblue">
									<th width="30%">날짜</th>
									<th width="50%">적요</th>
									<th width="130px">금액</th>
								</tr>
								<tbody>
									<c:forEach items="${trcList }" var="trc" varStatus="status">
										<c:choose>
											<c:when test="${trc.trcReceiveAccount eq accountVO.accountNo}">
												<tr><td><br/></td></tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td>${trc.trcDate }</td>
													<td style="font-family: bold">${trc.trcTitle }</td>
													<td style="color:red">-
														<fmt:formatNumber value="${trc.trcAmount }" pattern="#,###" />
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</article>

			</c:otherwise>
		</c:choose>
	</section>
	<footer>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"/>
	</footer>
</body>
</html>