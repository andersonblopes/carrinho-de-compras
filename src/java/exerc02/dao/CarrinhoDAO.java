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
import java.util.logging.Level;
import java.util.logging.Logger;

import exerc02.controller.CarrinhoBean;
import exerc02.controller.ProdutoBean;
import exerc02.modelo.Carrinho;
import exerc02.modelo.CarrinhoItens;
import exerc02.util.Conecta;

/**
 *
 * @author Anderson
 */
public class CarrinhoDAO {

    private Conecta conecta;
    String sql = null;
    private ResultSet rs;
    private ProdutoDAO produtoDAO;

    public CarrinhoDAO() {
	conecta = new Conecta();
	produtoDAO = new ProdutoDAO();
    }

    public void novo() {
	sql = "insert into public.carrinho (\"valortotal\",\"ativo\")values(0.00, true );";
	try {
	    conecta.executarSQL(sql);
	    obtemCarrinho();
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(CarrinhoBean.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public Carrinho obtemCarrinho() {
	sql = "select * from public.carrinho where public.carrinho.ativo is true;";
	try {
	    rs = conecta.getDados(sql);
	    Carrinho carrinho = new Carrinho();
	    if (rs.next()) {
		carrinho = new Carrinho();
		carrinho.setPkcarrinho(rs.getInt("pkcarrinho"));
		carrinho.setAtivo(rs.getBoolean("ativo"));
		carrinho.setValorTotal(rs.getDouble("valortotal"));
	    } else {
		novo();
	    }
	    return carrinho;
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(CarrinhoBean.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    public void encerraCarrinho(Carrinho carrinho) {
	try {
	    sql = "update public.carrinho set ativo = false where public.carrinho.pkcarrinho = "
		    + carrinho.getPkcarrinho() + ";";
	    conecta.executarSQL(sql);
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(CarrinhoBean.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public Carrinho obterPorID(int pkcarrinho) {
	sql = "select * from public.carrinho where public.carrinho.pkcarrinho = " + pkcarrinho;
	try {
	    rs = conecta.getDados(sql);
	    if (rs.next()) {
		Carrinho carrinho = new Carrinho();
		carrinho.setPkcarrinho(rs.getInt("pkcarrinho"));
		carrinho.setAtivo(rs.getBoolean("ativo"));
		carrinho.setValorTotal(rs.getDouble("valortotal"));
		return carrinho;
	    }
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
	}

	return null;
    }

    public ArrayList<CarrinhoItens> listarItens(Carrinho carrinho) {

	ArrayList<CarrinhoItens> itens = new ArrayList<>();

	try {
	    sql = "select * from public.carrinhoitens where public.carrinhoitens.fkcarrinho = "
		    + carrinho.getPkcarrinho() + ";";
	    rs = conecta.getDados(sql);

	    while (rs.next()) {
		CarrinhoItens item = new CarrinhoItens();
		item.setFkcarrinho(rs.getInt("fkcarrinho"));
		item.setProduto(produtoDAO.obterPorID(rs.getInt("produto")));
		item.setPkitem(rs.getInt("pkitem"));
		item.setQuantidade(rs.getInt("quantidade"));
		item.setValortotal(rs.getDouble("valortotal"));

		itens.add(item);
	    }
	    rs.close();

	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
	}
	return itens;
    }

    public void inserirItem(Carrinho carrinho, CarrinhoItens item) {
	try {
	    sql = "INSERT INTO public.carrinhoitens (\"fkcarrinho\",\"produto\",\"quantidade\", \"valortotal\")VALUES ("
		    + carrinho.getPkcarrinho() + "," + item.getProduto().getId() + "," + item.getQuantidade() + ","
		    + item.getValortotal() + ")";
	    conecta.executarSQL(sql);
	    atualizaValor(carrinho);
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void removerItem(int pkitem, Carrinho carrinho) {
	try {
	    sql = "delete from public.carrinhoitens where public.carrinhoitens.pkitem = " + pkitem;
	    conecta.executarSQL(sql);
	    atualizaValor(carrinho);
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void atualizaItem(Carrinho carrinho, CarrinhoItens item) {
	try {
	    sql = "update public.carrinhoitens set quantidade = " + item.getQuantidade() + ", valortotal="
		    + item.getValortotal() + " where public.carrinhoitens.pkitem = " + item.getPkitem() + ";";
	    conecta.executarSQL(sql);
	    atualizaValor(carrinho);
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(CarrinhoDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void atualizaValor(Carrinho carrinho) {
	try {
	    double valor = 0;
	    for (CarrinhoItens itenscarrinho : listarItens(carrinho)) {
		valor += itenscarrinho.getValortotal();
	    }
	    sql = "update public.carrinho set valortotal = " + valor + " where public.carrinho.pkcarrinho = "
		    + carrinho.getPkcarrinho() + ";";
	    conecta.executarSQL(sql);
	} catch (ClassNotFoundException | IOException | SQLException ex) {
	    Logger.getLogger(CarrinhoDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
