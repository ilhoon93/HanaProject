package moim.util.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import moim.account.service.AccountService;
import moim.account.vo.MoimUserVO;
import moim.moneyrequest.vo.MoneyRequestVO;
import moim.user.vo.UserVO;
public class InviteEchoHandler extends TextWebSocketHandler{
	
	
	@Autowired
	private AccountService accountService;
	
	/*
	 * 전체 사용자에게 메시지 보내기
	 * 1. 웹소켓 세션을 저장하는 리스트 생성
	 * 2. 모든 세션을 연결된 후 add 메시지로 리스트에 저장 
	 * 3. 모든 세션을 for문으로 돌면서 send메시지 날려줌 
	 */
	
	/*
	 * 특정 친구에게 모임 초대장 보내기
	 * http세션을 웹소켓 세션에 실어주기
	 */
	List<WebSocketSession > sessions = new ArrayList<WebSocketSession>();
	Map<String, WebSocketSession> userSessions = new HashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("afterConnectionEstablished:" + session);
		sessions.add(session);
		String senderId = getId(session);
		userSessions.put(senderId, session); // 사용자의 아이디와 그에 해당하는 세션매핑


//		MoneyRequestVO mrVO = getMrVO(session);
//		System.out.println(mrVO);
		// 내가 원하는 특정 유저 접속시
//		for(Map.Entry<String, WebSocketSession> sess : userSessions.entrySet()) {
//			if(sess.getKey().equals("1")) {
//				TextMessage aa = new TextMessage("하이하이");
//				sess.getValue().sendMessage(aa);
//			}
//		}
		
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		System.out.println("handleTextMessage:" + session + " : " + message);
		
		//전체 세션 접속 유저 아이디, 세션값 출력
//		for(Map.Entry<String, WebSocketSession> sess : userSessions.entrySet()) {
//			System.out.println("userID : " + sess.getKey() + ", value : " + sess.getValue());
//		}
		// 브라우저 2개 띄우면 각기 다른 세션에서 아이디가 생긴다.
		String senderId = getId(session);
		String senderName = getName(session);
		
		
		/*
		 세션에 접속한 모든 참여자에게 메세지 보내기
		 js의 onmessage 부분을 탄다.
		for(WebSocketSession sess : sessions) {
			TextMessage txt = new TextMessage(senderId + " : "+ message.getPayload());
			sess.sendMessage(txt);
		}
		*/
		
		// 마이 protocol : cmd, 모임장, 초대 받은 유저, 모임 계좌 (invite, 1, 2, 5)
		// ex) protocol : cmd, 댓글 작성자, 게시글 작성자, bno (reply, user2, user1, 234)
		
		String msg = message.getPayload();
		
		System.out.println("보낸사람 id : " + senderId + " 보낸 메시지 : " + msg);
		
		if(!StringUtils.isEmpty(msg)) {
			String[] strs = msg.split(",");
			//초대글 날리기
			if(strs != null && strs.length == 4) {
				String cmd = strs[0].trim();
				String moimOwner = strs[1].trim();
				String invitedUser = strs[2].trim();
				String accountNo = strs[3].trim();
				TextMessage tmpMsg = new TextMessage(senderId + "," + accountNo + "," +senderName + "님이" + accountNo + "모임계좌에 초대했습니다.");
				
				//초대받은 유저가 온라인일 때만
				WebSocketSession invitedUserSession = userSessions.get(invitedUser);
				
				if("invite".equals(cmd) && invitedUserSession != null) {
					invitedUserSession.sendMessage(tmpMsg);
				}
				

			}else if(strs != null && strs.length == 3) { //초대 수락
				String cmd = strs[0].trim();
				String accountNo = strs[1].trim();
				String invitedUser = strs[2].trim();

				// 수락 눌렀을 때
				if("inviteAgree".equals(cmd)) {
					MoimUserVO moimUser = new MoimUserVO();
					
					// 개인 계좌 가져오는 서비스
					int linkAccount = accountService.selectMyAccountOne(Integer.parseInt(invitedUser));
					moimUser.setAccountNo(Integer.parseInt(accountNo));
					moimUser.setNewUserNo(Integer.parseInt(invitedUser));
					moimUser.setNewUserAccountNo(linkAccount);
					
					System.out.println(moimUser);
					
					accountService.insertMoimUser(moimUser);
					
					System.out.println("초대수락");
				}
			}
		}
	}
	

	private String getId(WebSocketSession session) {
		// http세션에 있는 값을 실어서 웹소켓 세션에 보내준다. map으로 받음
		Map<String, Object> httpSession = session.getAttributes();
		UserVO loginUser = (UserVO)httpSession.get("userVO");
		
		//로그인 되었으면 로그인한 유저 번호, 아니면 세션 아이디 리턴
		if(loginUser == null) {
			return session.getId();
		}else {
			return String.valueOf(loginUser.getUserNo());
		}
	}
	
	private String getName(WebSocketSession session) {
		// http세션에 있는 값을 실어서 웹소켓 세션에 보내준다. map으로 받음
		Map<String, Object> httpSession = session.getAttributes();
		UserVO loginUser = (UserVO)httpSession.get("userVO");
		
		//로그인 되었으면 로그인한 유저 번호, 아니면 세션 아이디 리턴
		if(loginUser == null) {
			return session.getId();
		}else {
			return String.valueOf(loginUser.getUserName());
		}
	}

	
//	private MoneyRequestVO getMrVO(WebSocketSession session) {
//		Map<String, Object> httpSession = session.getAttributes();
//		MoneyRequestVO mrVO = (MoneyRequestVO)httpSession.get("moneyRequestVO");
//		return mrVO;
//	}

	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println("afterConnectionClosed" + session + " : " + status);
	}
}
