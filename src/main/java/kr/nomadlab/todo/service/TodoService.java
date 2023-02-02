package kr.nomadlab.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import kr.nomadlab.todo.dao.TodoDAO;
import kr.nomadlab.todo.domain.TodoVO;
import kr.nomadlab.todo.dto.TodoDTO;
import kr.nomadlab.todo.util.MapperUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
//		System.out.println("todoVO" + todoVO);
		log.info(todoVO);
		dao.insert(todoVO);		// int�� ��ȯ�ϹǷ� �̸� �̿��ؼ� ����ó���� ����
	}
	
	public List<TodoDTO> listAll() throws Exception {
		List<TodoVO> voList = dao.selectAll();
		log.info("voList ...");
		log.info(voList);
		List<TodoDTO> dtoList = voList.stream()
				.map(vo -> modelMapper.map(vo, TodoDTO.class))
				.collect(Collectors.toList());
		return dtoList;
	}
	
	public TodoDTO get(Long tno) throws Exception {
		log.info("tno: " + tno);
		TodoVO todoVO = dao.selectOne(tno);
		TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
		return todoDTO;
	}
}
