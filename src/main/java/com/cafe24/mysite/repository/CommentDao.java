package com.cafe24.mysite.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.CommentVo;
@Repository
public class CommentDao extends ObjectDao{

	public CommentVo getComment(long no){
		return sqlSession.selectOne("comment.getComment", no);
	}

	public List<CommentVo> getListByBoardNo(long boardNo){
		return sqlSession.selectList("comment.getList", boardNo);
	}
	
	public boolean insertComment(CommentVo vo) {
		boolean result = false;
		int count = sqlSession.insert("comment.insert", vo);
			if(count == 0) {
				System.out.println("deleteComment() 실패");
			}else {
				result = true;
				System.out.println("deleteComment() 성공");
			}
		return result;
	}

	public boolean deleteComment(long no) {
		boolean result = false;
		int count = sqlSession.delete("comment.delete", no);
			if(count == 0) {
				System.out.println("deleteComment() 실패");
			}else {
				result = true;
				System.out.println("deleteComment() 성공");
			}
		return result;
	}	

}
