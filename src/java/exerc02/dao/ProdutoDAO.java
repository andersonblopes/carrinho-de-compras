/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerc02.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import exerc02.controller.ProdutoBean;
import exerc02.modelo.Produto;
import exerc02.util.Conecta;

/**
 *
 * @author Anderson
 */
public class ProdutoDAO {

    private Conecta conecta;
    String sql = null;
    private ResultSet rs;

    public ProdutoDAO() {
	conecta = new Conecta();
    }

    public Produto obterPorID(int pkproduto) {
	sql = "select * from public.produto where public.produto.pkproduto = " + pkproduto;
	try {
	    rs = conecta.getDados(sql);
	    if (rs.next()) {
		Produto produto = new Produto();
		produto.setId(rs.getInt("pkproduto"));
		produto.setProduto(rs.getString("produto"));
		produto.setPreco(rs.getDouble("preco"));
		return produto;
	    }
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
	}

	return null;
    }

    public List<Produto> listar() {

	ArrayList<Produto> produtos = new ArrayList<>();

	try {
	    sql = "select * from public.produto;";
	    rs = conecta.getDados(sql);

	    while (rs.next()) {
		Produto p = new Produto();
		p.setId(rs.getInt("pkproduto"));
		p.setProduto(rs.getString("produto"));
		p.setPreco(rs.getDouble("preco"));
		produtos.add(p);
	    }
	    rs.close();

	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
	}
	return produtos;
    }

    public void inserir(String produto, double preco) {
	try {
	    sql = "INSERT INTO public.produto (\"produto\", \"preco\")VALUES (E'" + produto + "', '" + preco + "')";
	    conecta.executarSQL(sql);
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void remover(int pkproduto) {
	try {
	    sql = "delete from public.produto where public.produto.pkproduto = " + pkproduto;
	    conecta.executarSQL(sql);
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
