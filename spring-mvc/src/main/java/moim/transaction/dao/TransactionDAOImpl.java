package moim.transaction.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import moim.transaction.vo.TransactionVO;


@Repository
public class TransactionDAOImpl implements TransactionDAO{
	
	@Autowired
	private SqlSessionTemplate session;
	
	public void insertTransaction(TransactionVO newTransaction) {
		session.insert("moim.transaction.dao.TransactionDAO.insertTransaction",newTransaction);
		
	};
	
	public List<TransactionVO> selectTransactionList(int accountNo){
		List<TransactionVO> result = session.selectList("moim.transaction.dao.TransactionDAO.selectTransactionList", accountNo);
		return result;
	}
	public void updateFile(Map<String, Object> fileMap) {
		session.update("moim.transaction.dao.TransactionDAO.updateTrcFile",fileMap);
	}
}
