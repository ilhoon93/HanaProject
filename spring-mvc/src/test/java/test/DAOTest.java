package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import moim.user.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/**/*.xml"})
public class DAOTest {

	@Autowired
	private SqlSessionTemplate session;
	
	@Test
	public void 로그인테스트() throws Exception {
		
		UserVO user = new UserVO();
		user.setUserEmail("kohkhj902@naver.com");
		user.setUserPassword("123456");
		UserVO vo = session.selectOne("moim.user.dao.UserDAO.login", user);
		System.out.println(vo);
	}
}
