package com.cafe24.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@RequestMapping(value = {"/main"})
	public String main() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "헬로!";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public Map<String, Object> hello2() {
		Map<String, Object> map= new HashMap<>();
		map.put("name", "두울리");
		map.put("no", 10L);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/hello3")
	public UserVo hello3() {
		UserVo vo = new UserVo();
		vo.setEmail("khj309@naver.com");
		vo.setName("네임");
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/hello4")
	public JSONResult hello4() {
		UserVo vo = new UserVo();
		vo.setEmail("khj309@naver.com");
		vo.setName("네임");
		JSONResult result = JSONResult.success(vo);
		return result;
	}
}
