package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private GuestbookService gbService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<GuestbookVo> list = gbService.getList();
		model.addAttribute("list", list);
		model.addAttribute("length", list.size());
		return "guestbook/list";
	}
	
	@RequestMapping(value ="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		gbService.insertGuestbook(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value = "/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no")Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value = "/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no")Long no, @RequestParam("password") String password) {
		gbService.deleteByNoAndPassword(no, password);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value = "/ajax", method=RequestMethod.GET)
	public String listAjax(Model model) {
		List<GuestbookVo> list = gbService.getList();
		model.addAttribute("list", list);
		model.addAttribute("length", list.size());
		return "guestbook/index-ajax";
	}
	
}
