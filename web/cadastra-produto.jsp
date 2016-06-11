<%-- 
    Document   : cadastra-produto
    Created on : 06/06/2016, 15:39:39
    Author     : Anderson
--%>

<%@page import="exerc02.util.Utils"%>
<%@page import="java.util.List"%>
<%@page import="exerc02.modelo.Produto"%>
<%@page import="exerc02.controller.ProdutoBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de produtos</title>            
        <link type="text/css" rel="stylesheet" href="css/bootstrap-min.css"  />
    </head>
    <body>  
        <%
            ProdutoBean p = new ProdutoBean();
            if (request.getParameter("preco") != null) {
                p.cadastra(request.getParameter("produto"), Double.parseDouble(request.getParameter("preco").replace(",", ".")));
            }
        %>
        <div class="container bg-primary">
            <div style="float: right; margin-top: 10px;">
                <a title="Ir para o carrinho de compras" class="btn btn-toolbar btn-success" href="index.jsp">Carrinho</a>   
            </div>
            <form class="form-group form-group" role="form" action="cadastra-produto.jsp" method="POST">
                <h3>Cadastro de produtos</h3>                
                <hr>
                <div class="form-group col-md-10">
                    <label for="produto">Produto</label>
                    <input id="produto" autofocus="true" name="produto" class="form-control input-sm" required="true" type="text" placeholder="Descrição do produto"/>
                </div>
                <div class="form-group col-md-2">
                    <label for="preco">Preço</label>
                    <input id="preco" name="preco" class="form-control input-sm" type="text" required="true" placeholder="R$ 0.00"/>
                </div>
                <div class="form-group col-md-1">
                    <button type="submit" class="btn btn-default btn-sm">Cadastrar</button>                
                </div>
            </form>   
        </div>
        <div class="container">
            <form class="form-group form-group" role="form">
                <hr>
                <h4>Relação de produtos disponíveis</h4>
                <div class="table-responsive">
                    <table class="table table-bordered table-condensed table-hover">
                        <thead>
                            <tr>           
                                <td style="width: 5%;">Código</td>
                                <td style="width: 75%;">Produto</td>
                                <td style="width: 10%;">Preço unitário</td>
                                <td style="width: 10%;"></td>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Produto> produtos = p.lista();
                                if (produtos.size() > 0) {
                                    for (int i = 0; i < produtos.size(); i++) {
                                        out.println("<tr><td>" + produtos.get(i).getId() + "</td>"
                                                + "<td class=\"text-uppercase\">" + produtos.get(i).getProduto() + "</td>"
                                                + "<td style=\"width: 10%; text-align: right;\">R$ " + Utils.converterValor(produtos.get(i).getPreco(), 2) + "</td>"
                                                + "<td style=\"text-align: center;\"><a title = \"Incluir no carrinho de compras.\" href= \"index.jsp?id=" + produtos.get(i).getId() + "\" type=\"submit\" class=\"btn btn-success btn-sm\">Comprar</button></a></td>"
                                                + "</tr>");
                                    }
                                } else {
                                    out.println("<tr><td colspan=\"5\">Nenhum produto cadastrado!</td></tr>");
                                }
                            %>
                        </tbody>
                    </table>   
                </div>
            </form>
        </div>
    </body>
</html>
