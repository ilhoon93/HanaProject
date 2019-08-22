package moim.transaction.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moim.transaction.dao.TransactionDAO;
import moim.transaction.vo.TransactionVO;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionDAO transactionDAO;
	
	public void insertTransaction (TransactionVO newTransaction) {
		transactionDAO.insertTransaction(newTransaction);
	}
	
	public List<TransactionVO> selectTransactionList(int accountNo){
		List<TransactionVO> result = transactionDAO.selectTransactionList(accountNo);
		return result;
	}
	
	
	public void receiptFileUpload(Map<String, Object> fileUploadMap) {
		transactionDAO.updateFile(fileUploadMap);
	}
}
