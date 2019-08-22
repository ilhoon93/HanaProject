package moim.user.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import moim.user.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SqlSessionTemplate session;
	
	public UserVO selectByNo(int userNo) {
		UserVO user = session.selectOne("moim.user.dao.UserDAO.selectByNo",userNo);
		return user;
	}
	
	
//	@Override
//	public List<Integer> selectMoimNoByUser(int userNo) {
//		List<Integer> result = session.selectList("moim.user.dao.UserDAO.selectMoimByUser", userNo);
//		return result;
//	}
	
	public UserVO login(UserVO user) throws Exception {
		UserVO result = session.selectOne("moim.user.dao.UserDAO.login", user);
		return result;
	}
	
	public List<Integer> selectFriendsByNo(int userNo){
		List<Integer> list = session.selectList("moim.user.dao.UserDAO.selectFriendsByNo",userNo);
		return list;
	}
	
	public List<UserVO> selectFriendsName(List<Integer> friNoList){
		List<UserVO> list = session.selectList("moim.user.dao.UserDAO.selectFriendsNames",friNoList);
		return list;
	}

	public void updateUserPayStatus(List<Integer> requestedList) {
		session.update("moim.user.dao.UserDAO.updateUserPayStatus", requestedList);
	}
	
}