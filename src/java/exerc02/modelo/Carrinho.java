package exerc02.modelo;

import java.io.Serializable;

/**
 *
 * @author Anderson
 */
public class Carrinho implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pkcarrinho;
    private boolean ativo;
    private double valorTotal;

    public int getPkcarrinho() {
	return pkcarrinho;
    }

    public void setPkcarrinho(int pkcarrinho) {
	this.pkcarrinho = pkcarrinho;
    }

    public double getValorTotal() {
	return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
	this.valorTotal = valorTotal;
    }

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(boolean ativo) {
	this.ativo = ativo;
    }

}
