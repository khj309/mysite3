package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.CommentService;
import com.cafe24.mysite.vo.CommentVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;
import com.cafe24.web.util.Param;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {
	private static final int BOARD_COUNT_PER_PAGE = 10;  //한 페이지에 보여줄 게시글 수
	private static final int PAGI_COUNT_PER_PAGE = 5;    //한 페이지에 보여줄 페이지 번호 수

	@Autowired
	private CommentService commentServ;

	@Auth
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(
			Model model,
			@AuthUser UserVo authUser,
			@ModelAttribute Param param,
			@ModelAttribute CommentVo vo) {

		vo.setUserNo(authUser.getNo());

		boolean result = commentServ.insertComment(vo);

		param.build();
		model.addAttribute("boardParam", param);
		return "redirect:/board/view/"+vo.getBoardNo()+"?"+param.getQueryString();
	}

	@Auth
	@RequestMapping("/delete/{no}")
	public String delete(
			Model model,
			@AuthUser UserVo authUser,
			@ModelAttribute Param param,
			@PathVariable("no") Long no) {

		CommentVo vo = commentServ.getCommentByNo(no);
		
		if((vo != null)&&(authUser.getNo() != vo.getUserNo())) {
			return "redirect:/main";
		}
		
		long boardNo = vo.getBoardNo();
		commentServ.deleteCommentByNo(no);
		return "redirect:/board/view/"+boardNo+"?"+param.getQueryString();
	}
}
