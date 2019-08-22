package moim.moneyrequest.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import moim.moneyrequest.vo.MoneyRequestVO;
import moim.moneyrequest.vo.MoneyRequestVONoList;

@Repository
public class MoneyRequestDAOImpl implements MoneyRequestDAO {

	
	@Autowired
	private SqlSessionTemplate session;
	
	
	public void insertMoneyRequest(MoneyRequestVO newMrVO) {
		session.insert("moim.moneyrequest.dao.MoneyRequestDAO.insertMoneyRequest", newMrVO);
		// TODO Auto-generated method stub

	}


	
	public MoneyRequestVO selectNewData() {
		MoneyRequestVO result = session.selectOne("moim.moneyrequest.dao.MoneyRequestDAO.selectNewRequest");
		return result;
	}
	
	
	public void insertMoneyRequestList(Map<String, Object> newMrlist) {
		session.insert("moim.moneyrequest.dao.MoneyRequestDAO.insertMoneyRequestList", newMrlist);
	}
	
	public List<MoneyRequestVONoList> selectMrListByUser(int userNo) {
		List<MoneyRequestVONoList> result = session.selectList("moim.moneyrequest.dao.MoneyRequestDAO.selectMrListByUser",userNo);
		return result;
	}
	
	
	public int selectLinkedAccount(Map<String, Object> linkedAccount) {
		int result = session.selectOne("moim.moneyrequest.dao.MoneyRequestDAO.selectLinkedAccount",linkedAccount);
		return result;
	}
	
	
	// ∞Ë¡¬ø° µ∑ ª©±‚
	public void updateSenderAccount(Map<String, Object> subtractMoneyParam) {
		session.update("moim.moneyrequest.dao.MoneyRequestDAO.subtractMoney",subtractMoneyParam);
	}
	// ∞Ë¡¬ø° µ∑ ¥ı«œ±‚ 
	public void updateReceiverAccount(Map<String, Object> addMoneyParam) {
		
		session.update("moim.moneyrequest.dao.MoneyRequestDAO.addMoney",addMoneyParam);
	}
	
	public void updateMoneyRequestList(Map<String, Object> newMrList) {
		session.update("moim.moneyrequest.dao.MoneyRequestDAO.updateMrList", newMrList);
	}
	
}
