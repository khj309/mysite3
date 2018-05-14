package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public void join(UserVo vo) {
		boolean result = userDao.insertUser(vo);
	}

	@Override
	public UserVo getUser(UserVo vo) {
		return userDao.getUser(vo);
	}

	@Override
	public UserVo getUserByNo(long no) {
		return userDao.getUser(no);
	}

	@Override
	public UserVo getUserByEmail(String email) {
		return userDao.getUser(email);
	}
	
	@Override
	@Transactional
	public boolean modifyUser(UserVo vo) {
		return userDao.updateUser(vo);
	}
	
}
