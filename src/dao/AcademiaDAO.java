package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Academia;

public class AcademiaDAO {
	private Connection connection;

	public AcademiaDAO() {
	connection = new FabricaConexoes().getConnection();
	}

	public int inserir(Academia c) {
		int inseriu = 0;
		String sql = "INSERT INTO Academia(nome, proprietario, CNPJ, localizacao) VALUES (?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getProprietario());
			stmt.setString(3, c.getCnpj());
			stmt.setString(4, c.getLocalizacao());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<Academia> getLista() {
		String sql = "SELECT * FROM Academia;";
		PreparedStatement stmt;
		Academia c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Academia> Academias = new ArrayList<>();
			while (rs.next()) {
				c = new Academia();
				c.setNome(rs.getString("nome"));
				c.setCnpj(rs.getString("CNPJ"));
				c.setProprietario(rs.getString("proprietario"));
				c.setLocalizacao(rs.getString("localizacao"));
				Academias.add(c);
			}
			rs.close();
			stmt.close();
			return Academias;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
