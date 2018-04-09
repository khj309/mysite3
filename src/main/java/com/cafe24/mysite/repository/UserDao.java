package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get( Long no ) {
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public UserVo get( String email ) {
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	public UserVo get(UserVo vo) throws UserDaoException {
		return sqlSession.
				selectOne( "user.getByEmailAndPassword", vo );
	}
	
	public int insert(UserVo vo) {
		return sqlSession.insert( "user.insert", vo );
	}
	
	public int update(UserVo vo) {
		return sqlSession.update( "user.update", vo );
	}
}
