package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.web.util.Param;

@Repository
public class BoardDao extends ObjectDao{
	public BoardVo getBoard(long no){
		return sqlSession.selectOne("board.getBoard", no);
	}

	public List<BoardVo> getList(Param param){
		param = param.clone();
		String searchValue = param.getSearchValue();
		if(searchValue != null) {
			param.setSearchValue("%"+searchValue+"%");
		}
		return sqlSession.selectList("board.getBoardByParam", param);
	}

	public long countList(Param param) {
		param = param.clone();
		String searchValue = param.getSearchValue();
		if(searchValue != null) {
			param.setSearchValue("%"+searchValue+"%");
		}
		return sqlSession.selectOne("board.countListByParam", param);
	}

	public boolean insertBoard(BoardVo vo) {
		boolean result= false;
		int count = sqlSession.insert("board.insert", vo);

		if(count != 0) {
			result = true;
		}

		return result;
	}

	public boolean updateBoard(BoardVo vo) {
		boolean result= false;
		int count = sqlSession.update("board.updateBoard", vo);

		if(count != 0) {
			result = true;
		}

		return result;
	}

	public boolean updateOrderNo(int groupNo, int orderNo) {
		boolean result= false;
		Map<String, Object> map = new HashMap<>();

		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);

		int count = sqlSession.update("board.updateOrderNo", map);

		if(count != 0) {
			result = true;
		}

		return result;
	}


	public boolean updateViewCount(long no) {
		boolean result= false;

		int count = sqlSession.update("board.updateViewCount", no);

		if(count != 0) {
			result = true;
		}

		return result;
	}

	//글 상태를 삭제 상태로 변경
	public boolean updateStatus(long no) {
		boolean result= false;

		int count = sqlSession.update("board.updateStatus", no);

		if(count != 0) {
			result = true;
		}

		return result;
	}	

}
