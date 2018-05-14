package com.cafe24.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOG = LogFactory.getLog(GlobalExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	public void handlerException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception {
		
		//1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOG.error(errors.toString());
		
		//e.printStackTrace();
		
		
		request.setAttribute("errors", errors.toString());
		//2. 사과 응답
		String acceptHeader=request.getHeader("accept");
		if(acceptHeader.matches(".*application/json.*")) {
			//실패 json 응답
			LOG.debug("실패 json 응답");
			JSONResult result = JSONResult.fail(errors.toString());
			String json =new ObjectMapper().writeValueAsString(result);
			response.setContentType("application/json; charset=utf-8;");
			response.getWriter().print(json);
			return;
		}else {
			//실패 사과 페이지 응답
			request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);
		}
	}
}
