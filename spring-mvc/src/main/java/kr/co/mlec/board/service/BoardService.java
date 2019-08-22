package kr.co.mlec.board.service;

import java.util.List;

import kr.co.mlec.board.vo.BoardVO;

public interface BoardService {
	List<BoardVO> selectBoard();
}
