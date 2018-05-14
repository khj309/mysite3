package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.CommentDao;
import com.cafe24.mysite.vo.CommentVo;

@Service
public class CommentService {
	@Autowired
	private CommentDao commentDao;
	
	public CommentVo getCommentByNo(long commentNo) {
		return commentDao.getComment(commentNo);
	}
	
	public List<CommentVo> getListByBoardNo(long boardNo){
		return commentDao.getListByBoardNo(boardNo);
	}

	public boolean insertComment(CommentVo vo) {
		return commentDao.insertComment(vo);
	}
	
	public boolean deleteCommentByNo(long no) {
		return commentDao.deleteComment(no);
	}
	
	
}
