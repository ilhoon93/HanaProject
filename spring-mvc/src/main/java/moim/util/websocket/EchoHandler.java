package moim.util.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.mybatis.logging.Logger;
//import org.mybatis.logging.LoggerFactory;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import moim.user.vo.UserVO;

public class EchoHandler extends TextWebSocketHandler{
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	Map<String, WebSocketSession> userSessions = new HashMap<String, WebSocketSession>();
	
	
	
	// 클라이언트와 연결 이후에 실행되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		logger.info("{} 연결됨", session.getId());

		String senderId = getId(session);
		String senderName = getName(session);
		userSessions.put(senderId, session);
		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(senderName + " 님이 접속했습니다."));
		}
	}

	// 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		
		logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		
		
//		String senderId = getId(session);
		String senderName = getName(session);
		
		
		for (WebSocketSession sess : sessionList) {
//			String senderName = getName(sess);
			sess.sendMessage(new TextMessage(senderName + " : " + message.getPayload()));
		}
	}

	// 클라이언트와 연결을 끊었을 때 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		logger.info("{} 연결 끊김", session.getId());
		String senderName = getName(session);
		for (WebSocketSession sess : sessionList) {

			sess.sendMessage(new TextMessage(senderName + " 님이 나갔습니다."));
		}
	}
	
	private String getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVO loginUser = (UserVO)httpSession.get("userVO");
		
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
	
	
}
