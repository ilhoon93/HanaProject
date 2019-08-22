package moim.account.service;

import java.util.List;
import java.util.Map;

import moim.account.vo.AccountVO;
import moim.account.vo.MoimUserVO;
import moim.user.vo.UserVO;


public interface AccountService {
	public List<AccountVO> selectAccount(int userNo);
	
	public List<AccountVO> selectMoimAccount(int userNo);
	
	
	public AccountVO selectOneAccount(int accountNo);
	
	public List<UserVO> selectMoimUserByNo(int accountNo);
	
	public void insertMoimUser(MoimUserVO newMoimUser);

	public int selectMyAccountOne(int userNo);
	
	public int selectLinkedAccount(Map<String, Object> linkedAccount);
}
