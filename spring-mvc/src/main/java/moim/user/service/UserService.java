package moim.user.service;


import java.util.List;

import moim.user.vo.UserVO;

public interface UserService {
	public UserVO detailUser(int userNo);
	
	public UserVO login(UserVO user) throws Exception;
	
	
	public List<Integer> selectFriendsByNo(int userNo);
	
	public List<UserVO> selectFriendsNames(List<Integer> friNoList);
	
	public void updateUserPayStatus(List<Integer> requestedList);
	
}
