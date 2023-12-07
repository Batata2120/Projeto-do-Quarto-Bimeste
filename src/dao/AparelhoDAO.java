package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Academia;
import objetos.Aparelho;

public class AparelhoDAO {
	private Connection connection;

	public AparelhoDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(Aparelho c) {
		int inseriu = 0;
		String sql = "INSERT INTO Aparelho(qualidade, preco, marca, CNPJ_Academia) VALUES (?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getQualidade());
			stmt.setDouble(2, c.getPreco());
			stmt.setString(3, c.getMarca());
			stmt.setString(4, c.getCnpj_academia());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<Aparelho> getLista() {
		String sql = "SELECT * FROM Aparelho;";
		PreparedStatement stmt;
		Aparelho c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Aparelho> Aparelhos = new ArrayList<>();
			while (rs.next()) {
				c = new Aparelho();
				c.setQualidade(rs.getString("qualidade"));
				c.setPreco(rs.getDouble("preco"));
				c.setId(rs.getInt("id"));
				c.setMarca(rs.getString("marca"));
				c.setCnpj_academia(rs.getString("CNPJ_Academia"));
				Aparelhos.add(c);
			}
			rs.close();
			stmt.close();
			return Aparelhos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Aparelho> getLista(Academia ac) {
		String sql = "SELECT * FROM Aparelho WHERE CNPJ_Academia=?;";
		PreparedStatement stmt;
		Aparelho c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, ac.getCnpj());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Aparelho> Aparelhos = new ArrayList<>();
			while (rs.next()) {
				c = new Aparelho();
				c.setQualidade(rs.getString("qualidade"));
				c.setPreco(rs.getDouble("preco"));
				c.setId(rs.getInt("id"));
				c.setMarca(rs.getString("marca"));
				c.setCnpj_academia(rs.getString("CNPJ_Academia"));
				Aparelhos.add(c);
			}
			rs.close();
			stmt.close();
			return Aparelhos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int remover(Aparelho c) {
		int removeu = 0;
		int removeuAparelho = 0;
		String sqlAparelhoCliente = "DELETE FROM Clientes_usam_aparelho WHERE ID_APARELHOS=?";
		String sql = "DELETE FROM Aparelho WHERE id = ?;";
		PreparedStatement stmt;
		PreparedStatement stmtAparelhoCliente;
		try {
			stmtAparelhoCliente = (PreparedStatement) connection.prepareStatement(sqlAparelhoCliente);
			stmtAparelhoCliente.setInt(1, c.getId());
			removeuAparelho = stmtAparelhoCliente.executeUpdate();
			stmtAparelhoCliente.close();
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, c.getId());
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}

	public int alterar(Aparelho c) {
		int alterou = 0;
		String sql = "UPDATE Aparelho SET qualidade=?, preco=?, marca=? WHERE id=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getQualidade());
			stmt.setDouble(2, c.getPreco());
			stmt.setString(3, c.getMarca());
			stmt.setInt(4, c.getId());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}
