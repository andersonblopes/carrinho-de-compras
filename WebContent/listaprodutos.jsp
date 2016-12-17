<%-- 
    Document   : listaprodutos
    Created on : 06/06/2016, 15:40:01
    Author     : Anderson
--%>

<%@page import="exerc02.controller.CadastrarProduto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de produtos</title>
        <link type="text/css" rel="stylesheet" href="css/bootstrap-min.css"  />
    </head>
    <body>        
        <%
            CadastrarProduto c = new CadastrarProduto();
            if (request.getParameter("preco") != null) {
                c.cadastra(request.getParameter("produto"), Double.parseDouble(request.getParameter("preco")));
            }
        %>
        <div class="container">
            <h1>Lista de produtos</h1>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>           
                        <td>Código</td>
                        <td>Produto</td>
                        <td>Preço unitário</td>
                        <td>Quantidade</td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (c.getProdutos().size() > 0) {
                            for (int i = 0; i < c.getProdutos().size(); i++) {
                                out.println("<tr><td>" + c.getProdutos().get(i).getId() + "</td>"
                                        + "<td>" + c.getProdutos().get(i).getProduto() + "</td>"
                                        + "<td>R$ " + c.getProdutos().get(i).getPreco() + "</td>"
                                        + "<td>1</td>"
                                        + "<td><a href=\"#\">Comprar</a></td>"
                                        + "</tr>");
                            }
                        } else {
                            out.println("<tr><td colspan=\"5\">Nenhum produto cadastrado!</td></tr>");
                        }
                    %>
                </tbody>
            </table>
            <a href="index.jsp">Carrinho</a>
            <a href="cadastra-produto.jsp">Cadastrar produtos</a>
        </div>        
    </body>
</html>
