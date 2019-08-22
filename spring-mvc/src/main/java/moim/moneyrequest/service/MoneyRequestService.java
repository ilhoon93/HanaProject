package moim.moneyrequest.service;

import java.util.List;
import java.util.Map;

import moim.moneyrequest.vo.MoneyRequestVO;
import moim.moneyrequest.vo.MoneyRequestVONoList;

public interface MoneyRequestService {
	public void insertMoneyRequest(MoneyRequestVO newMrVO);
	
	public MoneyRequestVO selectNewRequest();
	
	public void insertMoneyRequestList(Map<String, Object> newMrList);
	
	public List<MoneyRequestVONoList> selectMrListByUser(int userNo);
	
	public void transferMoney(Map<String, Object> substractMoneyParam, Map<String, Object> addMoneyParam);

	public void updateMrList(Map<String, Object> newMrList);
}
