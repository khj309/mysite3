package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;


@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping( value="/join", method=RequestMethod.GET )
	public String join() {
		return "user/join";
	}

	@RequestMapping( value="/join", method=RequestMethod.POST )
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping( "/joinsuccess" )
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}

	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.GET )
	public String modify(@AuthUser UserVo authUser, Model model) {
		System.out.println( authUser );
		
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute( "vo", vo );
		
		return "user/modify";
	}
	
	/*
	@ExceptionHandler( UserDaoException.class )
	public String handleUserDaoException() {
		// 로그 남기기
		return "error/error";
	}
	*/
	
	
}
