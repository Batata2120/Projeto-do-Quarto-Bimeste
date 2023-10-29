package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Dependente;

public class DependenteDAO {
	private Connection connection;

	public DependenteDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(Dependente c) {
		int inseriu = 0;
		String sql = "INSERT INTO Dependente(nome, data_nascimento, cpf_dependente, cpf_funcionario, grau_parentesco) VALUES (?,?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getDataNascimento());
			stmt.setString(3, c.getCpf());
			stmt.setString(4, c.getFuncionarioResponsavel().getCpf());
			stmt.setString(5, c.getGrauParentesco());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<Dependente> getLista() {
		String sql = "SELECT * FROM Dependente;";
		PreparedStatement stmt;
		Dependente c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Dependente> Dependentes = new ArrayList<>();
			while (rs.next()) {
				c = new Dependente();
				c.setNome(rs.getString("nome"));
				c.setDataNascimento(rs.getString("data_nascimento"));
				c.setCpf(rs.getString("cpf_dependente"));
				c.setCpfFuncionario(rs.getString("cpf_funcionario"));
				c.setGrauParentesco(rs.getString("grau_parentesco"));
				Dependentes.add(c);
			}
			rs.close();
			stmt.close();
			return Dependentes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int remover(Dependente c) {
		int removeu = 0;
		String sql = "DELETE FROM Dependente WHERE cpf_dependente = ?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCpf());
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}

	public int alterar(Dependente c) {
		int alterou = 0;
		String sql = "UPDATE Dependente SET nome=?, data_nascimento=?, grau_parentesco=? WHERE cpf_dependente=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getDataNascimento());
			stmt.setString(3, c.getGrauParentesco());
			stmt.setString(4, c.getCpf());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}
