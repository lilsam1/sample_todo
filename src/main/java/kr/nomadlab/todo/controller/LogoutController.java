package kr.nomadlab.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;

@WebServlet("/todo/logout")
@Log4j2
public class LogoutController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("logout...");
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("loginInfo");
		session.invalidate();
		
		resp.sendRedirect("./login");
	}
}
