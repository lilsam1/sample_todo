package kr.nomadlab.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String str = mid + mpw;
		
		HttpSession session = req.getSession();
		
		session.setAttribute("loginInfo", str);
		
		resp.sendRedirect("./list");
	}
	
	
}
