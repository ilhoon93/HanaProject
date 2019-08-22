package kr.co.mlec.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mlec.board.dao.BoardDAO;
import kr.co.mlec.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	private BoardDAO boardDAO;
	
	public List<BoardVO> selectBoard() {
		List<BoardVO> boardList = boardDAO.select();
		// TODO Auto-generated method stub
		return boardList;
	}

}
