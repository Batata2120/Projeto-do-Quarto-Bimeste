package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.LojaSuplemento;

public class ProdutoDAO {
	private Connection connection;

	public ProdutoDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(LojaSuplemento c, String produto) {
		int inseriu = 0;
		String sql = "INSERT INTO produtos(CNPJ_LOJA, produto) VALUES (?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCnpj());
			stmt.setString(2, produto);
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<String> getLista(LojaSuplemento c) {
		String sql = "SELECT * FROM produtos WHERE CNPJ_LOJA=?;";
		PreparedStatement stmt;
		String produto;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCnpj());
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> produtos = new ArrayList<>();
			while (rs.next()) {
				produto = new String();
				produto = rs.getString("produto");
				produtos.add(produto);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int remover(LojaSuplemento ls, String c) {
		int removeu = 0;
		String sql = "DELETE FROM produtos WHERE CNPJ_LOJA=? AND produto=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, ls.getCnpj());
			stmt.setString(2, c);
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}
	public int remover(LojaSuplemento ls) {
		int removeu = 0;
		String sql = "DELETE FROM produtos WHERE CNPJ_LOJA=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, ls.getCnpj());
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}
	public int alterar(LojaSuplemento lojaDeSuplementos, String produtoAntigo, String ProdutoNovo) {
		int alterou = 0;
		String sql = "UPDATE produtos SET produto=? WHERE CNPJ_LOJA=? AND produto=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, ProdutoNovo);
			stmt.setString(2, lojaDeSuplementos.getCnpj());
			stmt.setString(3, produtoAntigo);
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}
