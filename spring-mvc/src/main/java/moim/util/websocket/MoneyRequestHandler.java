package moim.util.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import moim.account.service.AccountService;
import moim.account.vo.AccountVO;
import moim.moneyrequest.service.MoneyRequestService;
import moim.moneyrequest.vo.MoneyRequestVONoList;
import moim.transaction.service.TransactionService;
import moim.transaction.vo.TransactionVO;
import moim.user.vo.UserVO;

public class MoneyRequestHandler extends TextWebSocketHandler{
	
	
	@Autowired
	private MoneyRequestService mrService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	// 접속 했을 때 세션에 있는 userVO 받아와서 user_pay_status가 R이면 알림 창 띄우고 납부하기
	
	List<WebSocketSession > sessions = new ArrayList<WebSocketSession>();
	Map<String, WebSocketSession> userSessions = new HashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("그룹 웹소켓 연결:" + session);
		sessions.add(session);
		UserVO loginUser = getUser(session);
//		int userNo = loginUser.getUserNo();
		
		
		String senderId = getId(session);
		userSessions.put(senderId, session); // 사용자의 아이디와 그에 해당하는 세션매핑

		System.out.println(loginUser); // 접속한 유저 
		
		// 접속한 사용자가 요청받은 출금 목록
		List<MoneyRequestVONoList> result = mrService.selectMrListByUser(loginUser.getUserNo());
		
		
		System.out.println(result);
		// 접속한 사용자에게 출금 요청 메시지 전송
		for(MoneyRequestVONoList mr : result) {
			TextMessage msg = new TextMessage(mr.getTrcAccountNo()+ "모임계좌에서 \n'" + 
					mr.getTrcTitle() + "'목적으로"+ mr.getTrcDate()+ "까지" +	
					mr.getTrcAmount() + "원을 출금요청하였습니다.\n\n 확인을 누르면 연결계좌에서 바로 출금 됩니다,"+
					mr.getTrcAccountNo()+","+mr.getTrcAmount()+","+mr.getTrcTitle());
			System.out.println(msg.getPayload());
			session.sendMessage(msg);
		}
		
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
		UserVO loginUser = getUser(session);
//		String senderName = getName(session);
		
		
		String msg = message.getPayload();
		
		System.out.println("보낸사람 id : " + senderId + " 보낸 메시지 : " + msg);
		
		
		String [] strs = msg.split(",");
		String cmd = strs[0].trim();
		String receiveAccount = strs[1].trim();
		String sendUser = strs[2].trim();
		String money = strs[3].trim();
		String sendAccount = strs[4].trim();
		String title = strs[5].trim();
		if(cmd.equals("agree")) {
			
			
			// 나의 연결 계좌 정보 조회
			// 계좌 이체를 위한 준비
			Map<String, Object> addMoneyParam = new HashMap<String, Object>();
			Map<String, Object> subtractMoneyParam = new HashMap<String, Object>();
			
			
			addMoneyParam.put("receiveAccount",receiveAccount);
			subtractMoneyParam.put("sendAccount",sendAccount);
			int sendAccountN = Integer.parseInt(sendAccount);
			int receiveAccountN = Integer.parseInt(receiveAccount);
			AccountVO subVO = accountService.selectOneAccount(sendAccountN);
			AccountVO addVO = accountService.selectOneAccount(receiveAccountN);
			
			int subVOAmount = subVO.getAccountTotalAmount();
			int addVOAmount = addVO.getAccountTotalAmount();
			
			int subVOAmountN = subVOAmount - Integer.parseInt(money);
			int addVOAmountN = addVOAmount + Integer.parseInt(money);
			
			
			String newReceiveAccountMoney = String.valueOf(addVOAmountN);
			String newSendAccountMoney = String.valueOf(subVOAmountN);
			
			addMoneyParam.put("money", newReceiveAccountMoney);
			subtractMoneyParam.put("money", newSendAccountMoney);

			
			// 계좌 이체 실행
			System.out.println(subtractMoneyParam);
			System.out.println(addMoneyParam);
			mrService.transferMoney(subtractMoneyParam, addMoneyParam);
			
			// 출금 요청 정보 업데이트
			List<MoneyRequestVONoList> mr = mrService.selectMrListByUser(loginUser.getUserNo());
			for(MoneyRequestVONoList data : mr) {
				if(Integer.parseInt(receiveAccount)==data.getTrcAccountNo() 
						&& data.getTrcAmount() == Integer.parseInt(money)) {
					Map<String, Object> updateMrList = new HashMap<String, Object>();
					updateMrList.put("trcNo",data.getTrcNo());
					updateMrList.put("userNo",loginUser.getUserNo());
					mrService.updateMrList(updateMrList);
				}
			}
			// 트랜잭션 기록
			TransactionVO transaction  = new TransactionVO();
			
			transaction.setTrcAmount(Integer.parseInt(money));
//			System.out.println(money);
			transaction.setTrcSendAccount(sendAccountN);
			transaction.setTrcReceiveAccount(receiveAccountN);
			transaction.setTrcStatus("1");
			transaction.setTrcDate("dummy");
			transaction.setTrcNo(1);
			
			System.out.println(loginUser.getUserName());
			transaction.setTrcSenderName(loginUser.getUserName());
			transaction.setTrcTitle(title);
			
			
			System.out.println(transaction);
			transactionService.insertTransaction(transaction);
			
			
			System.out.println("트랜잭션 실행 후");
//			transactionService.
			
			

		}
	}
	// 로그인한 유저의 객체를 리턴
	private UserVO getUser(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVO loginUser = (UserVO)httpSession.get("userVO");
		return loginUser;
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


	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		System.out.println("afterConnectionClosed" + session + " : " + status);
	}

}
