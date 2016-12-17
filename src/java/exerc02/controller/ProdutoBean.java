package exerc02.controller;

import java.io.Serializable;
import java.util.List;

import exerc02.dao.ProdutoDAO;
import exerc02.modelo.Produto;

/**
 *
 * @author Anderson
 */
public class ProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProdutoDAO produtoDAO;

    private String msg = "";

    public ProdutoBean() {
	produtoDAO = new ProdutoDAO();
    }

    public List<Produto> lista() {
	return produtoDAO.listar();
    }

    public void cadastra(String produto, double preco) {
	try {
	    produtoDAO.inserir(produto, preco);
	} catch (Exception e) {
	    msg = e.getCause().getMessage();
	}

    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

}
