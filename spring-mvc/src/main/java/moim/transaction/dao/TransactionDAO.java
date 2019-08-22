package moim.transaction.dao;

import java.util.List;
import java.util.Map;

import moim.transaction.vo.TransactionVO;

public interface TransactionDAO {

	public void insertTransaction(TransactionVO newTransaction);
	
	public List<TransactionVO> selectTransactionList(int accountNo);
	
	
	/**
	 * 파일명을 데이터베이스에 업로드하는 코드
	 * @param fileMap
	 * @return
	 */
	public void updateFile(Map<String, Object> fileMap);
	
}
