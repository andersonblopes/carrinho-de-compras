<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Carrinho de compras - Erro</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div>
		<h2>Valor inválido informado!</h2>
		<%
		out.print(request.getParameter("excecao"));
		%>
		<a href="cadastra-produto.jsp">Cadastro de produtos</a><br> <a
			href="index.jsp">Carrinho de compras</a>
	</div>

</body>
</html>
