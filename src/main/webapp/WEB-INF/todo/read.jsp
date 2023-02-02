<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo Read</title>
</head>
<body>
	<h1>Todo Read</h1>
		<div>
			<div>번호 : ${dto.tno} </div>
			<div>제목 : ${dto.title} </div>
			<div>날짜 : ${dto.dueDate} </div>
			<div>완료 : ${dto.finished ? "DONE" : "NOT YET"} </div>
		</div>
</body>
</html>