package moim.moneyrequest.dao;

import java.util.List;
import java.util.Map;

import moim.moneyrequest.vo.MoneyRequestVO;
import moim.moneyrequest.vo.MoneyRequestVONoList;

public interface MoneyRequestDAO {
	public void insertMoneyRequest(MoneyRequestVO newMrVO);
	
//	public void insertMoneyRequestList(Map<String, Object> mrList);
	
	public MoneyRequestVO selectNewData();
	
	public void insertMoneyRequestList(Map<String, Object> newMrlist);
	
	
	public List<MoneyRequestVONoList> selectMrListByUser(int userNo);
	
	 
	public void updateSenderAccount(Map<String, Object> addMoneyParam);
	
	public void updateReceiverAccount(Map<String, Object> subtractMoneyParam);

	
	public void updateMoneyRequestList(Map<String, Object> newMrList);

}
