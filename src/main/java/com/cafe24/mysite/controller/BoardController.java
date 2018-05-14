package com.cafe24.mysite.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;
import com.cafe24.web.util.PaginationBuilder;
import com.cafe24.web.util.Param;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	private static final int BOARD_COUNT_PER_PAGE = 10;  //한 페이지에 보여줄 게시글 수
	private static final int PAGI_COUNT_PER_PAGE = 5;    //한 페이지에 보여줄 페이지 번호 수

	@Autowired
	BoardService boardServ;
	
	@RequestMapping("/list")
	public String list(
			@ModelAttribute Param param,
			Model model) {

		param.setCountPerPage(BOARD_COUNT_PER_PAGE);
		param.build();
		List<BoardVo> list = boardServ.getListByParam(param);
		long totalBoardCount  = boardServ.countListbyParam(param);

		PaginationBuilder pb = new PaginationBuilder(param, totalBoardCount, PAGI_COUNT_PER_PAGE);
		pb.build();

		model.addAttribute("list", list);
		model.addAttribute("pb", pb);
		model.addAttribute("boardParam", param);
		return "board/list";
	}

	@RequestMapping("/view/{no}")
	public String view(
			@ModelAttribute Param param,
			@PathVariable("no") Long no,
			Model model) {
		BoardVo vo = boardServ.getBoardByNoWithComment(no);
		model.addAttribute("vo", vo);
		param.build();
		model.addAttribute("boardParam", param);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(
			Model model,
			@AuthUser UserVo authUser,
			@ModelAttribute Param param,
			@RequestParam(value="parentno", required=false, defaultValue="0") Long parentNo) {

		BoardVo parentBoard = boardServ.getBoardByNo(parentNo);
		if(parentBoard != null) {
			parentBoard.setTitle("'"+parentBoard.getTitle()+"'에 대한 답글입니다.");
			parentBoard.setContent("\n\n\n-----------------------------------\n"+parentBoard.getContent());
			model.addAttribute("parentBoard", parentBoard);
		}
		param.build();
		model.addAttribute("boardParam", param);
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
			Model model,
			@AuthUser UserVo authUser,
			@ModelAttribute BoardVo boardVo,
			@RequestParam(value="parentno", required=false, defaultValue="0")Long parentNo) {

		boardVo.setUserNo(authUser.getNo());
		boolean result  = boardServ.insertBoard(boardVo, parentNo);
		return "redirect:/board/list";
	}

	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modify(
			Model model,
			@AuthUser UserVo authUser,
			@ModelAttribute Param param,
			@PathVariable("no") Long no) {

		BoardVo board = boardServ.getBoardByNo(no);
		//게시글이 있고, 인증유저가 작성자가 맞는지 확인)
		if(board == null || board.getUserNo() != authUser.getNo()) {
			return "redirect:/board/list";
		}


		model.addAttribute("board", board);
		param.build();
		model.addAttribute("boardParam", param);
		return "board/modify";
	}

	@Auth
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modify(
			Model model,
			@ModelAttribute Param param,
			@ModelAttribute BoardVo vo) throws UnsupportedEncodingException {

		//원본 있는지 확인
		long boardNo=vo.getNo();

		BoardVo board = boardServ.getBoardByNo(boardNo);

		if(board == null) {
			return "redirect:/board/list";
		}

		board.setTitle(vo.getTitle());
		board.setContent(vo.getContent());

		boardServ.updateBoard(board);
		if(param.getSearchValue() != null) {
			param.setSearchValue(URLEncoder.encode(param.getSearchValue(), "utf-8"));
		}
		param.build();
		return "redirect:/board/view/"+board.getNo()+"?"+param.getQueryString();
	}

	@Auth
	@RequestMapping("/delete/{no}")
	public String delete(
			Model model,
			@AuthUser UserVo authUser,
			@ModelAttribute Param param,
			@PathVariable("no") Long no) throws UnsupportedEncodingException {


		BoardVo targetBoard = boardServ.getBoardByNo(no);

		if(targetBoard != null && targetBoard.getUserNo() == authUser.getNo()) {
			boardServ.deleteBoardByNo(no);
		}


		if(param.getSearchValue() != null) {
			param.setSearchValue(URLEncoder.encode(param.getSearchValue(), "utf-8"));
		}

		param.build();
		return "redirect:/board/list?"+param.getQueryString();
	}
}
