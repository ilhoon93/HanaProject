package moim.accout.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import moim.account.vo.AccountVO;
import moim.account.vo.MoimUserVO;
import moim.user.vo.UserVO;

/**
 * mybatis를 통한 oracleDB 계좌 정보 CRUD 구현클래스
 * @author Ilhoon
 *
 */

@Repository
public class AccountDAOImpl implements AccountDAO{
	
	@Autowired
	private SqlSessionTemplate session;
	
	public List<AccountVO> selectByUser(int userNo){
		List<AccountVO> list = session.selectList("moim.account.dao.AccountDAO.selectByUser",userNo);
		return list;
	}
	
	public List<AccountVO> selectMoimByUser(int userNo){
		List<AccountVO> list = session.selectList("moim.account.dao.AccountDAO.selectMoimByUser",userNo);
		return list;
	}
	
	
	public AccountVO selectByNo(int accountNo) {
		AccountVO account = session.selectOne("moim.account.dao.AccountDAO.selectByNo",accountNo);
		return account;
	}
	
	public List<UserVO> selectMoimUserByNo(int accountNo){
		List<UserVO> list = session.selectList("moim.account.dao.AccountDAO.selectMoimUserByNo", accountNo);
		return list;
	}

	public void insertMoimUser(MoimUserVO newMoimUser) {
		session.insert("moim.account.dao.AccountDAO.insertMoimUser", newMoimUser);
	}
	
	public int selectMyAccountOne(int userNo) {
		int result = session.selectOne("moim.account.dao.AccountDAO.selectMyAccountOne", userNo);
		return result;
	}
	
	public int selectLinkedAccount(Map<String, Object> linkedAccount) {
		int result = session.selectOne("moim.account.dao.AccountDAO.selectLinkedAccount", linkedAccount);
		return result;
	}
	
	
}
