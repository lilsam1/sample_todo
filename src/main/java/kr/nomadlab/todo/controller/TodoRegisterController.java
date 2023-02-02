package kr.nomadlab.todo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.nomadlab.todo.dto.TodoDTO;
import kr.nomadlab.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {
	private TodoService todoService = TodoService.INSTANCE;
	private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("/todo/register GET ...");
		req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TodoDTO todoDTO = TodoDTO.builder()
				.title(req.getParameter("title"))
				.dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
				.build();
		log.info("/todo/register POST ...");
		log.info(todoDTO);
		try {
			todoService.register(todoDTO);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServletException("read error");
		}
		resp.sendRedirect("./list");
	}
	
}
