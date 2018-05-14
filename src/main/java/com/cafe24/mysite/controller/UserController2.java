package com.cafe24.mysite.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@SessionAttributes("authUser")
@RequestMapping("/user2")
public class UserController2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5574057311877233768L;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/login", method=RequestMethod.GET)
	public String login() {
		return "user/login2";
	}
	
	@RequestMapping(value= "/login", method=RequestMethod.POST)
	public String login(
			@ModelAttribute UserVo vo,
			Model model) {
		System.out.println("유저 2/로그인");
		UserVo authUser = userService.getUser(vo);
		if(authUser!=null) {
			model.addAttribute("authUser", authUser);
			return "redirect:/main";
		}
		return "user/login2";
	}
	
	@ResponseBody
	@RequestMapping("/modify")
	public String modify(
			@ModelAttribute("authUser") UserVo authUser) {
		System.out.println(authUser);
		return "UserController2:modify";
	}
	
	@RequestMapping("/logout")
	public String logout(
			SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/user2/login";
	}
	
}
