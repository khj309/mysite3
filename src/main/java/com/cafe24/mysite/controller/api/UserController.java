package com.cafe24.mysite.controller.api;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller(value="userApiController")
@RequestMapping("/api/user")
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value="/checkemail", method=RequestMethod.GET)
	public JSONResult checkEmail(
			@RequestParam(value = "email", required = true, defaultValue="") String email ) {
		UserVo vo = userService.getUserByEmail(email);
		System.out.println(email);
		return JSONResult.success(vo == null?"not exist":"exist");
	}
}
