package sample_todo;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.nomadlab.todo.dto.TodoDTO;
import kr.nomadlab.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

@Log4j2
class TodoServiceTests {
	private TodoService todoService;
	
	@BeforeEach
	public void ready() {
		todoService = TodoService.INSTANCE;
	}
	
	@Test
	public void testRegister() throws Exception {
		TodoDTO todoDTO = TodoDTO.builder()
				.title("JDBC Test Title")
				.dueDate(LocalDate.now())
				.build();
		log.info("--------------------------");	// �׽�Ʈ �ڵ��� Log4j2 ���� Ȯ��
		log.info(todoDTO);
		todoService.register(todoDTO);
	}

}
