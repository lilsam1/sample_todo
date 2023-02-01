package sample_todo;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.nomadlab.todo.dao.TodoDAO;
import kr.nomadlab.todo.domain.TodoVO;

class TodoDAOTests {
	private TodoDAO todoDAO;
	
	@BeforeEach
	public void ready() {
		todoDAO = new TodoDAO();
	}
	
	@Test
	public void testTime() throws Exception {
		//System.out.println(todoDAO.getTime());
		System.out.println(todoDAO.getTime2());
	}
	

	@Test
	public void testInsert() throws Exception {
		TodoVO todoVO = TodoVO.builder()
				.title("sample title...")
				.dueDate(LocalDate.of(2021, 12, 31))
				.build();
		
		todoDAO.insert(todoVO);
	}
	
	
	@Test
	public void testList() throws Exception {
		List<TodoVO> list = todoDAO.selectAll();
		list.forEach(vo -> System.out.println(vo));
	}
	
	@Test
	public void testSelectOne() throws Exception {
		Long tno = 2L;	// 반드시 존재하는 번호를 이용
		TodoVO vo = todoDAO.selectOne(tno);
		System.out.println(vo);
	}
	
	@Test
	public void testUpdateOne() throws Exception {
		TodoVO todoVO = TodoVO.builder()
				.tno(2L)
				.title("sample title update...")
				.dueDate(LocalDate.of(2022, 01, 01))
				.finished(true)
				.build();
		
		todoDAO.updateOne(todoVO);
	}
	
	@Test
	public void testDeleteOne() throws Exception {
		Long tno = 3L;	// 반드시 존재하는 번호를 이용
		todoDAO.deleteOne(tno);
	}

}
