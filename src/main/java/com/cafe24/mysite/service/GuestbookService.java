package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class GuestbookService{
	@Autowired
	private GuestbookDao gbDao;

	public List<GuestbookVo> getList() {
		return gbDao.getList();
	}
	
	public List<GuestbookVo> getListByLastNo(long lastNo) {
		return gbDao.getList(lastNo);
	}

	public boolean insertGuestbook(GuestbookVo vo) {
		return gbDao.insertGuestbook(vo);
	}
	
	public GuestbookVo insertGuestbook2(GuestbookVo vo) {
		return gbDao.insertGuestbook2(vo);
	}

	
	public boolean deleteByNoAndPassword(Long no, String password) {
		return gbDao.deleteGuestbook(no, password);
	}

}
