<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HANA 1Q MOIM</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/layout.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/mypage.css">

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/js/main.js"></script>
</head>
<script>

$(document).ready(function() {
	//btnSend 버튼을 눌렀을 때 msg를 socket에다가 send하겠다는 의미
	$('#btnSend').on('click', function(evt) {
		evt.preventDefault();
		if(socket.readyState !== 1) return;
		let msg = $('input#msg').val();
		socket.send(msg);
	});
});
	
</script>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/include/topMenu.jsp"/>
	</header>
	<section>
	
	<div id="fileUploadModalLayer">
		<div class="fileUploadModalLayer">
			
		</div>
	</div>
	
<!-- 		친구목록 모달 -->
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
			    			<a>${friends.userNo }</a>
			    			
<!-- 			    		<form action="../websocket"> -->
<%-- 			    			<input type="submit" name="addFriendsBtn" value="${friends.userNo }"/>	 --%>
<!-- 			    		</form> -->
			    		
			    		</td>
			    	</tr>
		    	</c:forEach>
		    </table>
		  </div>
		</div>
		
	    <div id="myPageSidebar">
	        <img src="/resources/images/blank_profile.png" id="myProfileImage"/><br/>
	        <div id="profileText">
	
	            <span id="profileName">${userVO.userName}</span><br/><br/>
	            <span id="profileId">${userVO.userEmail}</span>
	        </div>
	        <div id="profileButton">
	            <a class="myPageButton" id="fListModalLink" href="#">친구목록</a><br/><br/>
	            <a class="myPageButton" href="#">이체하기</a><br/><br/>
	            <a class="myPageButton" href="#">내 자산 현황</a><br/><br/>
	            <a class="myPageButton" href="#">카드 이용내역</a>
	        </div>
	    </div>
	
		<div id="myPageMain">
			<c:forEach items="${accountList }" var="account">
				<c:choose>
					<c:when test="${account.accountType eq 0 }">
						<a href="#" class="accountInfo" style="background:#FFFFE0">
							<div class="accountInfoHeader">${userVO.userName} 님의 개인 계좌입니다.
								<input type="button" value="모임계좌전환" style=" background: white;"/>
							</div>
					</c:when>

					<c:otherwise>
						<a href="${pageContext.request.contextPath }/account/group/${account.accountNo}/${userVO.userNo}" class="accountInfo">
							<div class="accountInfoHeader">${userVO.userName} 님이 관리하는 모임계좌입니다.</div>
					</c:otherwise>
				</c:choose>
					
					<div class="accountInfoMain" style="position:relative; top:30px;">
		                <span><c:out value="${account.accountNo }"/></span><br/><br/>
		                <span style="font-size:50px;"><fmt:formatNumber value="${account.accountTotalAmount }" pattern="#,###"/>원</span>
		              
		            </div>
				</a>
			</c:forEach>

			<c:forEach items="${moimAccountList }" var="moimAccount">
				<a href="${pageContext.request.contextPath }/account/group/${moimAccount.accountNo}/${userVO.userNo}" style="background:tomato" class="accountInfo">
					<div class="accountInfoHeader">${userVO.userName} 님이 참여 중인 모임계좌입니다.</div>
					<div class="accountInfoMain" style="position:relative; top:30px;">
		                <span><c:out value="${moimAccount.accountNo }"/></span><br/><br/>
		                <span style="font-size:50px;"><fmt:formatNumber value="${moimAccount.accountTotalAmount }" pattern="#,###"/>원</span>
		              
		            </div>
				</a>
			</c:forEach>
	        <a id="addAccount" style="background:lightskyblue;" href="#" class="accountInfo">
	            <span style="position: relative; top:35px;  text-decoration: none; font-size: 100px; display:block">+</span>
	            <span style="position:relative; top:20px;">새 계좌 추가하기</span></a>
	    </div>
	</section>

	<footer>
		<jsp:include page="/WEB-INF/jsp/include/footer.jsp"/>
	</footer>
</body>
</html>