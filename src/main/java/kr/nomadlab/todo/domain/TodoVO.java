package kr.nomadlab.todo.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TodoVO {
	private Long tno;
	private String title;
	private LocalDate dueDate;
	private boolean finished;
}
