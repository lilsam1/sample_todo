package kr.nomadlab.todo.service;

import org.modelmapper.ModelMapper;

import kr.nomadlab.todo.dao.TodoDAO;
import kr.nomadlab.todo.domain.TodoVO;
import kr.nomadlab.todo.dto.TodoDTO;
import kr.nomadlab.todo.util.MapperUtil;

public enum TodoService {
	INSTANCE;
	
	private TodoDAO dao;
	private ModelMapper modelMapper;
	
	TodoService() {
		dao = new TodoDAO();
		modelMapper = MapperUtil.INSTANCE.get();
	}
	
	public void register(TodoDTO todoDTO) throws Exception {
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
		System.out.println("todoVO" + todoVO);
		dao.insert(todoVO);		// int를 반환하므로 이를 이용해서 예외처리도 가능
	}
}
