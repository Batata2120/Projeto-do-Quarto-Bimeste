package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Aparelho;

public class AparelhoDAO {
	private Connection connection;

	public AparelhoDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(Aparelho c) {
		int inseriu = 0;
		String sql = "INSERT INTO Aparelho(qualidade, preco, id, marca, CNPJ_Academia) VALUES (?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getQualidade());
			stmt.setDouble(2, c.getPreco());
			stmt.setInt(3, c.getId());
			stmt.setString(4, c.getMarca());
			stmt.setString(5, c.getAcademiaResponsavel().getCnpj());
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

	public int remover(Aparelho c) {
		int removeu = 0;
		String sql = "DELETE FROM Aparelho WHERE codigo = ?;";
		PreparedStatement stmt;
		try {
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
