package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public boolean existUser( String email ) {
		UserVo userVo = userDao.get(email);
		return userVo != null;
	}
	
	public boolean join( UserVo userVo ) {
		return userDao.insert( userVo ) == 1;
	}
	
	public UserVo getUser( UserVo vo ) {
		UserVo userVo = userDao.get( vo );
		return userVo;
	}
	
	public UserVo getUser(Long no) {
		return userDao.get(no);
	}
	
	@Transactional
	public boolean modifyUser( UserVo userVo ) {
		return userDao.update(userVo) == 1;
	}
}
