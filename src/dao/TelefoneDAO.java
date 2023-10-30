package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Cliente;

public class TelefoneDAO {
	private Connection connection;

	public TelefoneDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(Cliente cliente, String telefone) {
		int inseriu = 0;
		String sql = "INSERT INTO TELEFONE_CLIENTES(CPF_CLIENTE, telefone) VALUES (?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, telefone);
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<String> getLista(Cliente cliente) {
		String sql = "SELECT * FROM TELEFONE_CLIENTES WHERE CPF_CLIENTE=?;";
		PreparedStatement stmt;
		String telefone;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			ResultSet rs = stmt.executeQuery();
			ArrayList<String> telefones = new ArrayList<>();
			while (rs.next()) {
				telefone = new String();
				telefone = rs.getString("telefone");
				telefones.add(telefone);
			}
			rs.close();
			stmt.close();
			return telefones;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int remover(Cliente cliente, String telefone) {
		int removeu = 0;
		String sql = "DELETE FROM TELEFONE_CLIENTES WHERE CPF_CLIENTE=? AND telefone=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, telefone);
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}
	public int remover(Cliente cliente) {
		int removeu = 0;
		String sql = "DELETE FROM TELEFONE_CLIENTES WHERE CPF_CLIENTE=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cliente.getCpf());
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}
	public int alterar(Cliente cliente, String telefoneAntigo, String telefoneNovo) {
		int alterou = 0;
		String sql = "UPDATE TELEFONE_CLIENTES SET telefone=? WHERE CPF_CLIENTE=? AND telefone=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, telefoneNovo);
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, telefoneAntigo);
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}
