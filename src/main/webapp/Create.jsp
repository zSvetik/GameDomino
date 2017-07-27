<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Домино</title>
</head>
<body>
	<a href="/index.html">На главную</a>
	<form action="/setDominoList" method="get">
		<p>
			<b>Со скольки элементов строить цепочки?</b>
		</p>

		<p>
			<input type="radio" name="type" value="random">Случайное
			количество
		</p>
		<p>
			<input type="radio" name="type" value="set" checked>Указать
			вручную: <input type="number" name="count" style="width: 40pt"
				value="4">
		</p>

		<button type="submit">Формировать</button>
		<p>
			<c:out value="${message}" />
		</p>
	</form>
	<form action="/showResult" method="post">
		<button type="submit">Смотреть результат</button>
	</form>

</body>
</html>
