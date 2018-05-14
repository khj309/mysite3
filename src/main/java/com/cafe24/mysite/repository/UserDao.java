package com.cafe24.mysite.repository;

import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao extends ObjectDao{
	public UserVo getUser(UserVo vo) throws RuntimeException{
		return sqlSession.selectOne("user.getUserByEmailAndPassword", vo);
	}
	
	public UserVo getUser(long no) {
		return sqlSession.selectOne("user.getUserByNo", no);
	}
	
	public UserVo getUser(String email) {
		return sqlSession.selectOne("user.getUserByEmail", email);
	}
	
	public boolean insertUser(UserVo vo) {
		boolean result= false; 
		int count = sqlSession.insert("user.insert", vo);
		if(count == 0) {
			System.out.println("실패");
		}else {
			result = true;
			System.out.println("성공");
		}

		return result;
	}
	
	public boolean updateUser(UserVo vo) {
		boolean result= false; 
		int count = sqlSession.insert("user.update", vo);
		if(count == 0) {
			System.out.println("실패");
		}else {
			result = true;
			System.out.println("성공");
		}

		return result;
	}
	
}
