package exerc02.controller;

import exerc02.dao.ProdutoDAO;
import exerc02.modelo.Produto;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Anderson
 */
public class ProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProdutoDAO produtoDAO;

    public ProdutoBean() {
        produtoDAO = new ProdutoDAO();
    }

    public List<Produto> lista() {
        return produtoDAO.listar();
    }

    public void cadastra(String produto, double preco) {        
        produtoDAO.inserir(produto, preco);
    }

}
