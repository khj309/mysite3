package com.cafe24.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.repository.GuestbookDao;

@Controller( "guestbookAPIController" )
@RequestMapping( "/api/guestbook" )
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping( "/list" )
	public JSONResult list() {
		return null;
	}
}
