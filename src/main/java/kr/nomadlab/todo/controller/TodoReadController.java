package kr.nomadlab.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.nomadlab.todo.dto.TodoDTO;
import kr.nomadlab.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {
	private TodoService todoService = TodoService.INSTANCE;

	private Cookie findCookie(Cookie[] cookies, String cookieName) {
		Cookie targetCookie = null;
		
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals(cookieName)) {
					targetCookie = cookie;
					break;
				}
			}
		}
		
		if (targetCookie == null) {
			targetCookie = new Cookie(cookieName, "");
			targetCookie.setPath("/");
			targetCookie.setMaxAge(60 * 60 * 24);
		}
		
		return targetCookie;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("todo read GET ...");
		
		try {
			Long tno = Long.parseLong(req.getParameter("tno"));
			
			TodoDTO todoDTO = todoService.get(tno); 
			
			// 데이터 담기
			req.setAttribute("dto", todoDTO);
			
			// 쿠키 찾기
			Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
			String todoListStr = viewTodoCookie.getValue();
			boolean exist = false;
			
			if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {
				exist = true;
			}
			log.info("exist : " + exist);
			
			if (!exist) {
				todoListStr += tno + "-";
				viewTodoCookie.setValue(todoListStr);
				viewTodoCookie.setMaxAge(60 * 60 * 24);
				viewTodoCookie.setPath("/");
				resp.addCookie(viewTodoCookie);
			}
			
			req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServletException("read error");
		}
	
	}
	
	
}
