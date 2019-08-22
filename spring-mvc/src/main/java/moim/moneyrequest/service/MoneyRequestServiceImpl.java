package moim.moneyrequest.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import moim.account.service.AccountService;
import moim.account.vo.AccountVO;
import moim.moneyrequest.dao.MoneyRequestDAO;
import moim.moneyrequest.vo.MoneyRequestVO;
import moim.moneyrequest.vo.MoneyRequestVONoList;

@Transactional
@Service
public class MoneyRequestServiceImpl implements MoneyRequestService {

	@Autowired
	private MoneyRequestDAO moneyRequestDAO;
	
	
	public void insertMoneyRequest(MoneyRequestVO newMrVO) {
		// TODO Auto-generated method stub
		moneyRequestDAO.insertMoneyRequest(newMrVO);
	}
	
	public MoneyRequestVO selectNewRequest() {
		MoneyRequestVO result = moneyRequestDAO.selectNewData();
		return result;
	}
	
	public void insertMoneyRequestList(Map<String, Object> newMrList) {
		moneyRequestDAO.insertMoneyRequestList(newMrList);
	}
	
	public List<MoneyRequestVONoList> selectMrListByUser(int userNo) {
		List<MoneyRequestVONoList> result = moneyRequestDAO.selectMrListByUser(userNo);
		return result;
	}
	
	// 계좌 이체 송신, 수신
	/*
	 * 돈 있나 확인 -> 없으면 끝
	 * 돈 빼기
	 * 돈 넣기
	 * 출금요청 리스트에서 해당 유저와 row 삭제
	 * money_request --> 트랜잭션
	 * 트랜잭션에 기록
	 * 끝
	 */
	@Transactional
	public void transferMoney(Map<String, Object> substractMoneyParam, Map<String, Object> addMoneyParam) {
		moneyRequestDAO.updateSenderAccount(substractMoneyParam);
		moneyRequestDAO.updateReceiverAccount(addMoneyParam);
	}
	
	
	public void updateMrList(Map<String, Object> newMrList) {
		moneyRequestDAO.updateMoneyRequestList(newMrList);
	}
}
