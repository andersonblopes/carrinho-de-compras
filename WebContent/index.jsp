<%-- 
    Document   : index
    Created on : 06/06/2016, 15:40:01
    Author     : Anderson
--%>

<%@page import="exerc02.modelo.CarrinhoItens"%>
<%@page import="exerc02.util.Utils"%>
<%@page import="exerc02.controller.CarrinhoBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrnho de compras</title>
        <link type="text/css" rel="stylesheet" href="css/bootstrap-min.css"  />
    </head>
    <body>    
        <%
            CarrinhoBean cb = new CarrinhoBean();
            if (request.getParameter("id") != null) {
                cb.insereItem(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("index.jsp");
            }

            if (request.getParameter("acao") != null && request.getParameter("item") != null) {
                if (request.getParameter("acao").equals("x")) {
                    cb.removeItem(Integer.parseInt(request.getParameter("item")));
                    response.sendRedirect("index.jsp");
                }
            }


        %>
        <div class="container">            
            <div style="margin-top: 10px;">
                <div class="col-md-4 h1">                    
                    Carrinho de compras
                </div>                
                <div class="col-md-5" style="text-align: center;">
                    <br>
                    <a class="btn btn-toolbar btn-success" href="cadastra-produto.jsp">Adicionar produtos</a>   
                </div>
                <div class="col-md-3 btn-danger" style=" text-align: center; float: right;">   
                    <h2>
                        R$ <%= Utils.converterValor(cb.getCarrinho().getValorTotal(), 2)%>
                    </h2>
                </div>
            </div>                  
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>                        
                        <td style="width: 60%;">Produto</td>
                        <td style="width: 5%;">Quantidade</td>
                        <td style="width: 10%; text-align: center;">Valor</td>                        
                        <td style="width: 10%;"></td>
                    </tr>
                </thead>
                <tbody>                          
                    <%
                        for (CarrinhoItens c : cb.listaItens()) {
                            out.println("<tr><td class=\"text-uppercase\">" + c.getProduto().getProduto() + "</td>"
                                    + "<td style=\"text-align: center;\">" + c.getQuantidade() + "</td>"
                                    + "<td style=\"width: 10%; text-align: right;\"> R$ " + Utils.converterValor(c.getValortotal(), 2) + "</td>"
                                    + "<td style=\"text-align: center;\"><a href=\"index.jsp?acao=x&item=" + c.getPkitem() + "\">Remover</a></td></tr>");
                        }
                    %>                                           
                </tbody>
            </table>                                   
        </div>        
    </body>
</html>
