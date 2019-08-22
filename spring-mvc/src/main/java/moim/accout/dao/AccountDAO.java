package moim.accout.dao;

import java.util.List;
import java.util.Map;

import moim.account.vo.AccountVO;
import moim.account.vo.MoimUserVO;
import moim.user.vo.UserVO;

public interface AccountDAO {
	
	
	/**
	 * 특정 유저가 가진 계좌 정보를 모두 가져오는 서비스
	 * @param userNo 조회할 유저 번호
	 * @return 계좌 객체 리스트
	 */
	public List<AccountVO> selectByUser(int userNo);
	
	
	/**
	 * 특정 유저가 참여하고 있는 모든 모임 계좌 리스트
	 * @param userNo
	 * @return	계좌 객체 리스트
	 */
	public List<AccountVO> selectMoimByUser(int userNo);
	
	
	/**
	 * 특정 계좌에 대한 정보를 가져오는 서비스
	 * @param accountNo 조회할 계좌 번호
	 * @return 계좌 객체
	 */
	public AccountVO selectByNo(int accountNo);
	
	
	/**
	 * 특정 모임계좌에 대해 구성원의 정보를 모두 가져오는 서비스
	 * @param accountNo
	 * @return 유저 객체 리스트
	 */
	public List<UserVO> selectMoimUserByNo(int accountNo);
	
	/**
	 * 모임계좌에 특정 구성원을 초대하는 서비스
	 * @param newMoimUser
	 */
	public void insertMoimUser(MoimUserVO newMoimUser);
	
	/**
	 * 나의 개인계좌를 가져오는 서비스(가장 위의)
	 * @param userNo
	 * @return accountNo
	 */
	public int selectMyAccountOne(int userNo);
	
	
	/**
	 * 그룹계좌와 연결된 계좌 가져오는 서비스
	 * 자기 번호와 계좌번호를 통해 조회
	 * @param linkedAccount
	 * @return
	 */
	public int selectLinkedAccount(Map<String, Object> linkedAccount);
	


}
