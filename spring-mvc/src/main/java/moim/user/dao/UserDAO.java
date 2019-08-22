package moim.user.dao;

import java.util.List;

import moim.user.vo.UserVO;

/**
 * 유저 정보 CRUD와 관련된 기능클래스
 * @author Ilhoon
 *
 */

public interface UserDAO {
	/**
	 * 유저 상세 정보 조회 서비스
	 * @param userNO 조회할 유저 번호
	 * @return 조회된 사용자
	 */
	public UserVO selectByNo(int userNo);
	
	/**
	 * 유저 로그인 서비스
	 * @param dto 로그인을 위한 사용자 정보
	 * @return 로그인 된 유저 정보 - 없다면 null
	 */
	public UserVO login(UserVO user) throws Exception;
	
	/**
	 * 내가 가진 친구목록
	 * @param userNo
	 * @return 친구 유저 번호 목록
	 */
	public List<Integer>selectFriendsByNo(int userNo);
	
	/**
	 * 친구 번호목록을 통해 가져오는 이름 목록
	 * @param friNoList
	 * @return 친구 이름 목록
	 */
	public List<UserVO> selectFriendsName(List<Integer> friNoList);
	
	/**
	 * 결제 요청 받은 리스트를 통해 유저의 결제 정보 상태를 갱신
	 * @param requestedList
	 */
	public void updateUserPayStatus(List<Integer> requestedList);
}