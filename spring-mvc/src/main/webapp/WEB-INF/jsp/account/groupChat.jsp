<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/layout.css">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/groupPage.css">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/chat.css">
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/main.js"></script>

        <meta charset="UTF-8">
        <!-- jQuery CDN-->
        <script
          src="https://code.jquery.com/jquery-1.9.0.js"
          integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
          crossorigin="anonymous"></script>
        <!-- Web socket CDN -->

<!--         <script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script> -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

</head>
<script type="text/javascript">

        $(document).ready(function() {
               $("#sendBtn").click(function() {
                       sendMessage();
                       $('#message').val('')
               });
               $("#message").keydown(function(key) {
                       if (key.keyCode == 13) {// 엔터
                              sendMessage();
                              $('#message').val('')
                       }
               });
        });

        // 웹소켓을 지정한 url로 연결한다.
        var sock = new SockJS("<c:url value="/echo"/>");
        sock.onmessage = onMessage;
        sock.onclose = onClose;
		
        // 메시지 전송
        function sendMessage() {
               sock.send($("#message").val());
        }

        // 서버로부터 메시지를 받았을 때
        function onMessage(msg) {
               var data = msg.data;
               $("#data").append(data + "<br/>");
        }

        // 서버와 연결을 끊었을 때
        function onClose(evt) {
               $("#data").append("연결 끊김");

        }

</script>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/include/topMenu.jsp"/>
	</header>
	
	<section style="text-align:center">
		<c:choose>
			<c:when test="${accountVO.accountOwnerNo eq userVO.userNo}">
				<div id="gListModalLayer" class="modalLayer">
					<div id="gListModalContent" class="modalContent">
					<div style="background: #464964; color:white">
						${accountVO.accountNo} 모임원 목록
					</div>
						<button type="button">X</button>
						<table style="text-align: center">
							<tr>
								<th>번호</th>
								<th>이름</th>
								<th>초대하기</th>
							</tr>
							<c:forEach items="${moimUserList}" var="user" varStatus="status">
								<tr>
									<td width="5%">${status.index +1 }</td>
									<td width="30%"><a>${user.userName }</a></td>
									<td width="20%">
										<form action="../websocket">
											<input type="submit" name="addFriendsBtn"
												value="${user.userNo }" />
										</form>
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
							관리자 : ${accountVO.accountOwnerNo } &nbsp; 
							<span style="font-family: sans-serif; font-size:15px">이자율 : ${accountVO.accountRate }%</span><br /><br /><br />
							<span style="font-size: 35px"><fmt:formatNumber value="${accountVO.accountTotalAmount }" pattern="#,###" />원 <br/>	</span>
						
						</div>

						<br/>
						<br/>
						<a class="myPageButton" href="#">회비걷기</a><br /> <br />
						<a class="myPageButton" id="gListModalLink" href="#">신규 모임원 추가</a><br /> <br /> 
						<a class="myPageButton" id="fileUploadBtn" href="#">영수증 등록</a><br /><br /> 
						<a class="myPageButton" id="" href="../websocket">그룹채팅</a><br /> <br />

					</div>
				</aside>
				<article>
					<div id="inputDiv">
						<input type="text" id="message" /> <input type="button"
							id="sendBtn" value="전송" />
					</div>

					<div id="data"></div>
					<a id="exitChat" href="${pageContext.request.contextPath }/account/group/${accountVO.accountNo}/${userVO.userNo}">채팅방 나가기</a>
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
						<th>초대하기</th>
					</tr>
					<c:forEach items="${moimUserList}" var="user" varStatus="status">
						<tr>
							<td width="5%">${status.index +1 }</td>
							<td width="30%"><a>${user.userName }</a></td>
							<td width="20%">
								<form action="../websocket">
									<input type="submit" name="addFriendsBtn"
										value="${user.userNo }" />
								</form>
							</td>
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
						<a class="myPageButton" id="" href="../websocket">그룹채팅</a><br /> <br />

					</div>
				</aside>
				<article>
					<div id="inputDiv">
						<input type="text" id="message" /> <input type="button"
							id="sendBtn" value="전송" />
					</div>

					<div id="data"></div>
					<a id="exitChat" href="${pageContext.request.contextPath }/account/group/${accountVO.accountNo}/${userVO.userNo}">채팅방 나가기</a>
				</article>
			</c:otherwise>
		</c:choose>

	</section>

    <footer>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"/>
	</footer>
</body>




</html>