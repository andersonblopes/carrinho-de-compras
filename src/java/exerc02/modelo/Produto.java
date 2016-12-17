package exerc02.modelo;

/**
 *
 * @author Anderson
 */
public class Produto {

    private int pkproduto;
    private String produto;
    private double preco;

    public int getId() {
	return pkproduto;
    }

    public void setId(int id) {
	this.pkproduto = id;
    }

    public String getProduto() {
	return produto;
    }

    public void setProduto(String produto) {
	this.produto = produto;
    }

    public double getPreco() {
	return preco;
    }

    public void setPreco(double preco) {
	this.preco = preco;
    }

}
