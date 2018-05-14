package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5574057311877233768L;
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(
			Model model,
			@RequestParam(value="result", required=false, defaultValue="") String result) {
		model.addAttribute("result", result);
		return "user/login";
	}

	@RequestMapping(value = "join", method=RequestMethod.GET)
	public String join( UserVo userVo) {
		return "user/joinform";
	}

	@RequestMapping(value = "join", method=RequestMethod.POST)
	public String join( @Valid UserVo userVo, BindingResult result) {
		if(result.hasErrors() == true) {
			System.out.println("UserController:POST join() -> 해즈 에러");
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError objectError : errors) {
				System.out.println(objectError.toString());
			}
			return "user/joinform";
		}
		userService.join(userVo);
		return "redirect:joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	
	@Auth
	@RequestMapping(value = "modify", method=RequestMethod.GET)
	public String modify(
			@AuthUser UserVo authUser, 
			Model model) {
		
		UserVo userInfo = userService.getUserByNo(authUser.getNo());
		model.addAttribute("userInfo", userInfo);
		return "user/modify";
	}

	@Auth
	@RequestMapping(value = "modify", method=RequestMethod.POST)
	public String modify(
			@AuthUser UserVo authUser,
			UserVo userVo) {
		 
		if(authUser == null) {
			return "redirect:/main";
		}
		
		boolean result = userService.modifyUser(userVo);
		return "redirect:/user/modify?result=success";
	}
 
	
	/*
	@ExceptionHandler( UserDaoException.class )
	public String handleUserDaoException() {
		return "error/error";
	}
	*/
}
