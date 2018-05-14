package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;
@Repository
public class GuestbookDao extends ObjectDao{

	public GuestbookVo getGuestbook(long no){
		return sqlSession.selectOne("guestbook.getGuestbookByNo", no);
	}
	
	public List<GuestbookVo> getList(){
		return sqlSession.selectList("guestbook.getList", 0);
	}
	
	public List<GuestbookVo> getList(long lastNo){
		return sqlSession.selectList("guestbook.getList", lastNo);
	}

	public boolean insertGuestbook(GuestbookVo vo) {
		boolean result = false;
		int count= sqlSession.insert("guestbook.insert", vo);
		if(count == 0) {
			System.out.println("실패");
		}else {
			result = true;
			System.out.println("성공");
		}
		return result;
	}
	
	public GuestbookVo insertGuestbook2(GuestbookVo vo) {
		boolean result = false;
		GuestbookVo addedVo = null;
		int count= sqlSession.insert("guestbook.insert", vo);
		if(count == 0) {
			System.out.println("실패");
		}else {
			result = true;
			System.out.println("성공");
			addedVo = getGuestbook(vo.getNo());
		}
		return addedVo;
	}

	public boolean deleteGuestbook(long no, String password) {
		boolean result= false;
		Map<String, Object> param = new HashMap<>();
		param.put("no", no);
		param.put("password", password);
		System.out.println("맵"+param);
		int count = sqlSession.delete("guestbook.delete", param);

		if(count == 0) {
			System.out.println("실패");
		}else {
			result = true;
			System.out.println("성공");
		}
		return result;
	}	
}
