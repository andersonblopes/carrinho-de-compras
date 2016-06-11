/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerc02.modelo;

import java.io.Serializable;

/**
 *
 * @author Anderson
 */
public class CarrinhoItens implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pkitem;
    private Produto produto;
    private int quantidade;
    private double valortotal;
    private int fkcarrinho;

    public int getPkitem() {
        return pkitem;
    }

    public void setPkitem(int pkitem) {
        this.pkitem = pkitem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public int getFkcarrinho() {
        return fkcarrinho;
    }

    public void setFkcarrinho(int fkcarrinho) {
        this.fkcarrinho = fkcarrinho;
    }

}
