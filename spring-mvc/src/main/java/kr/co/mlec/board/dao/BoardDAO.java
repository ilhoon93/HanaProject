package kr.co.mlec.board.dao;

import java.util.List;

import kr.co.mlec.board.vo.BoardVO;

public interface BoardDAO {
	List<BoardVO> select();
}
