package com.cafe24.mysite.service;

import com.cafe24.mysite.vo.UserVo;

public interface UserService {

	public void join(UserVo vo);

	public UserVo getUser(UserVo vo);

	public UserVo getUserByNo(long no);
	
	public UserVo getUserByEmail(String email);
	
	public boolean modifyUser(UserVo vo);
}
