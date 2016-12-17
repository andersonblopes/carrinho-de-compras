package exerc02.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anderson
 */
public class Conecta {

    private static Conecta conecta;
    private static Connection conexao;
    private static Statement stis4;

    public Connection getConnection() throws ClassNotFoundException, IOException {
	try {
	    String url = "jdbc:postgresql://127.0.0.1:5432/carrinhodecompras";
	    String senha = "postgres";
	    String usr = "postgres";

	    Class.forName("org.postgresql.Driver");
	    return DriverManager.getConnection(url, usr, senha);
	} catch (SQLException erro) {
	    throw new RuntimeException(erro);
	}
    }

    public void executarSQL(String sql) throws ClassNotFoundException, IOException, SQLException {
	try {
	    conecta = new Conecta();
	    conexao = conecta.getConnection();
	    stis4 = conexao.createStatement();
	    stis4.executeUpdate(sql);
	    stis4.close();
	} catch (SQLException erro) {
	    throw new RuntimeException(erro);
	} finally {
	    conexao.close();
	}
    }

    public ResultSet getDados(String sql) throws ClassNotFoundException, IOException, SQLException {
	try {
	    conecta = new Conecta();
	    conexao = conecta.getConnection();
	    stis4 = conexao.createStatement();
	    ResultSet rs = stis4.executeQuery(sql);
	    return rs;
	} catch (SQLException erro) {
	    throw new RuntimeException(erro);
	} finally {
	    conexao.close();
	}
    }

}
