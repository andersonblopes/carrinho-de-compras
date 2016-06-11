/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerc02.controller;

import exerc02.dao.CarrinhoDAO;
import exerc02.dao.ProdutoDAO;
import exerc02.modelo.Carrinho;
import exerc02.modelo.CarrinhoItens;
import exerc02.modelo.Produto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Anderson
 */
public class CarrinhoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Carrinho carrinho;

    private ProdutoDAO produtoDAO;
    private CarrinhoDAO carrinhoDAO;

    public CarrinhoBean() {
        carrinhoDAO = new CarrinhoDAO();
        this.carrinho = carrinhoDAO.obtemCarrinho();
    }

    public void insereItem(int pkproduto) {
        ArrayList<CarrinhoItens> carrinhoatual = carrinhoDAO.listarItens(carrinho);
        boolean existe = false;
        for (CarrinhoItens item : carrinhoatual) {
            if (item.getProduto().getId() == pkproduto) {
                existe = true;
                item.setQuantidade(item.getQuantidade() + 1);
                item.setValortotal(item.getProduto().getPreco() * item.getQuantidade());
                carrinhoDAO.atualizaItem(carrinho, item);
            }
        }

        if (!existe) {
            produtoDAO = new ProdutoDAO();
            Produto p = produtoDAO.obterPorID(pkproduto);
            CarrinhoItens item = new CarrinhoItens();
            item.setProduto(p);
            item.setQuantidade(1);
            item.setFkcarrinho(getCarrinho().getPkcarrinho());
            item.setValortotal(item.getQuantidade() * p.getPreco());
            carrinhoDAO.inserirItem(carrinho, item);
        }

    }

    public double mostraValor() {
        carrinhoDAO.atualizaValor(carrinho);
        return getCarrinho().getValorTotal();
    }

    public ArrayList<CarrinhoItens> listaItens() {
        return carrinhoDAO.listarItens(carrinho);
    }

    public void removeItem(int pkitem) {
        carrinhoDAO.removerItem(pkitem, getCarrinho());
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

}
