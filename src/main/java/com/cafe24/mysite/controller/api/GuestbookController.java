package com.cafe24.mysite.controller.api;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller(value="guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private GuestbookService guestbookServ;

	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public JSONResult list(@RequestParam("lastNo") Long lastNo) {
		return JSONResult.success(guestbookServ.getListByLastNo(lastNo));
	}
	/* 쿼리 스트링
	@ResponseBody
	@RequestMapping(value = "", method=RequestMethod.POST)
	public JSonResult insert(GuestbookVo vo) {
		GuestbookVo added = guestbookServ.insertGuestbook2(vo);
		System.out.println(added);
		return JSonResult.success(added);
	}*/

	/* JSon 스트링 */
	@ResponseBody
	@RequestMapping(value = "", method=RequestMethod.POST)
	public JSONResult insert(@RequestBody GuestbookVo vo) {
		GuestbookVo added = guestbookServ.insertGuestbook2(vo);
		System.out.println(added);
		return JSONResult.success(added);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/{no}", method=RequestMethod.DELETE)
	public JSONResult delete(
			@PathVariable("no") Long no,
			@RequestParam(value = "passwd", required=false, defaultValue="0") String passwd) {
		System.out.println("방명록 삭제" + passwd);
		JSONResult jResult = null;
		boolean result = guestbookServ.deleteByNoAndPassword(no, passwd);
		if(result == true) {
			jResult=JSONResult.success("success");
		}
		else if(result == false) {
			jResult=JSONResult.success("failed");
		}
		
		return jResult;
	}
}
	