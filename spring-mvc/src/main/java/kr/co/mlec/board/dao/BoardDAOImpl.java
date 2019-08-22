package kr.co.mlec.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.mlec.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSessionTemplate session;
	
	public List<BoardVO> select() {
		// TODO Auto-generated method stub
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectAll");
		return list;
	}

}
