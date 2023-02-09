package kr.nomadlab.todo.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.nomadlab.todo.dto.MemberDTO;
import kr.nomadlab.todo.service.MemberService;
import lombok.extern.log4j.Log4j2;

@WebServlet("/todo/login")
@Log4j2
public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("Login get...");
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("Login post...");
		
		String mid = req.getParameter("mid");
		String mpw = req.getParameter("mpw");
		
		String auto = req.getParameter("auto");
		
		boolean rememberMe = auto != null && auto.equals("on");
		
		try {
			MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);
			
			if (rememberMe) {
				String uuid = UUID.randomUUID().toString();
				
				MemberService.INSTANCE.updateUuid(mid, uuid);
				memberDTO.setUuid(uuid);
				
				Cookie rememberCookie = new Cookie("remember-me", uuid);
				rememberCookie.setMaxAge(60 * 60 * 24 * 7);	// 쿠키의 유효기간 1주일
				rememberCookie.setPath("/");
				
				resp.addCookie(rememberCookie);
			}
			HttpSession session = req.getSession();
			session.setAttribute("loginInfo", memberDTO);
			resp.sendRedirect("./list");
		} catch (Exception e) {
			resp.sendRedirect("./login?result=error");
		}	
		
	}
	
	
}
