package moim.account.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moim.account.vo.AccountVO;
import moim.account.vo.MoimUserVO;
import moim.accout.dao.AccountDAO;
import moim.user.vo.UserVO;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	public List<AccountVO> selectAccount(int userNo) {
		// TODO Auto-generated method stub
		List<AccountVO> list = accountDAO.selectByUser(userNo);
		return list;
	}
	
	public List<AccountVO> selectMoimAccount(int userNo){
		List<AccountVO> list = accountDAO.selectMoimByUser(userNo);
		return list;
	}
	
	public AccountVO selectOneAccount(int accountNo) {
		AccountVO account = accountDAO.selectByNo(accountNo);
		return account;
	}
	
	public List<UserVO> selectMoimUserByNo(int accountNo){
		List<UserVO> list = accountDAO.selectMoimUserByNo(accountNo);
		return list;
	}
	
	public void insertMoimUser(MoimUserVO newMoimUser) {
		accountDAO.insertMoimUser(newMoimUser);
	}
	
	public int selectMyAccountOne(int userNo) {
		int result = accountDAO.selectMyAccountOne(userNo);
		return result;
	}
	
	public int selectLinkedAccount(Map<String, Object> linkedAccount) {
		int result = accountDAO.selectLinkedAccount(linkedAccount);
		return result;
	}
}