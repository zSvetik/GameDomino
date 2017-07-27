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
	<p>
		<b>Текущий набор: <c:out value="${current_set}" /></b>
	</p>
	<form action="/showResult" method="get">
		<p>
			<input type="radio" name="type" value="max" checked>Только
			самая длинная цепочка
		</p>
		<p>
			<input type="radio" name="type" value="all">Все цепочки
		</p>

		<button type="submit">Показать</button>
		<p>
			<c:out value="${message}" />
		</p>
	</form>

	<form action="/showResult" method="post">
		<button type="submit">История</button>
		<br>
		
		<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>История</h2></caption>
            <tr>
                <th>Начальная цепочка</th>
                <th>Правильный порядок</th>
            </tr>
            <c:forEach var="chain" items="${listChain}">
                <tr>
                    <td><c:out value="${chain.stringChainDomino}" /></td>
                    <td><c:out value="${chain.listChainDominoHistory}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    
	</form>

	<a href="/Create.jsp"><button type="button">Новый набор</button></a>
</body>
</html>
