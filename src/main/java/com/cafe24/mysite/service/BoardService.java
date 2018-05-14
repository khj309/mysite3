package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.web.util.Param;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private CommentDao commentDao;
	
	
	
	public List<BoardVo> getListByParam(Param param){
		return boardDao.getList(param);
	}

	public long countListbyParam(Param param) {
		return boardDao.countList(param);
	}

	public BoardVo getBoardByNo(Long no) {
		return boardDao.getBoard(no);
	}
	
	public BoardVo getBoardByNoWithComment(Long no) {
		BoardVo boardVo = boardDao.getBoard(no);
		boardVo.setComment(commentDao.getListByBoardNo(no));
		boardDao.updateViewCount(no);
		return boardVo;
	}

	public boolean insertBoard(BoardVo vo, long parentNo) {
		BoardVo parentBoard = boardDao.getBoard(parentNo);

		if(parentBoard != null) {
			vo.setGroupNo(parentBoard.getGroupNo());
			vo.setOrderNo(parentBoard.getOrderNo()+1);
			vo.setDepth(parentBoard.getDepth()+1);
			boardDao.updateOrderNo(vo.getGroupNo(), vo.getOrderNo());
		}
		System.out.println("새 보드"+vo);

		return boardDao.insertBoard(vo);
	}

	public boolean updateBoard(BoardVo vo) {
		return boardDao.updateBoard(vo);
	};

	public boolean deleteBoardByNo(long no) {
		return boardDao.updateStatus(no);
	}
}
