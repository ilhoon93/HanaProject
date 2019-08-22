package moim.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moim.user.dao.UserDAO;
import moim.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	

	public UserVO detailUser(int userNo) {
		// TODO Auto-generated method stub
		return userDAO.selectByNo(userNo);
	}

	public UserVO login(UserVO user) throws Exception{
		return userDAO.login(user);
	}
	
	public List<Integer> selectFriendsByNo(int userNo){
		List<Integer> list = userDAO.selectFriendsByNo(userNo);
		return list;
	}
	
	
	public List<UserVO> selectFriendsNames(List<Integer> friNoList){
		List<UserVO> list = userDAO.selectFriendsName(friNoList);
		return list;
	}
	
	public void updateUserPayStatus(List<Integer> requestedList) {
		userDAO.updateUserPayStatus(requestedList);
	}
}
