package kr.nomadlab.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("todo read GET ...");
		
		try {
			Long tno = Long.parseLong(req.getParameter("tno"));
			
			TodoDTO todoDTO = todoService.get(tno); 
			
			// ������ ���
			req.setAttribute("dto", todoDTO);
			req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServletException("read error");
		}
	
	}
	
}
