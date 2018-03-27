package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping( "/guestbook" )
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	@ResponseBody
	@RequestMapping( {"", "/list"} )
	public String test() {
		
//		List<GuestbookVo> list = guestbookDao.getList();
//		for( GuestbookVo vo : list ) {
//			System.out.println( vo );
//		}
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName( "둘리" );
		vo.setPassword( "1234");
		vo.setContent( "안녕하세요." );
		int count = guestbookDao.insert( vo );
		System.out.println( count );
			
		return "ok";
	}
}
