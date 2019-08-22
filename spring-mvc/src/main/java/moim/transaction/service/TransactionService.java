package moim.transaction.service;

import java.util.List;
import java.util.Map;

import moim.transaction.vo.TransactionVO;

public interface TransactionService {

	public void insertTransaction (TransactionVO newTransaction);

	
	public List<TransactionVO> selectTransactionList(int account);
	
	
	public void receiptFileUpload(Map<String, Object> fileUploadMap);
}
