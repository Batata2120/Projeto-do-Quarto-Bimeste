package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Academia;
import objetos.Funcionario;

public class FuncionarioDAO {
	private Connection connection;

	public FuncionarioDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(Funcionario c) {
		int inseriu = 0;
		String sql = "INSERT INTO Funcionario(nome, cpf, funcao, salario, data_nascimento , CNPJ_Academia) VALUES (?,?,?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getCpf());
			stmt.setString(3, c.getFuncao());
			stmt.setDouble(4, c.getSalario());
			stmt.setString(5, c.getDataNascimento());
			stmt.setString(6, c.getCnpjAcademiaResponsavel());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<Funcionario> getLista() {
		String sql = "SELECT * FROM Funcionario;";
		PreparedStatement stmt;
		Funcionario c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Funcionario> Funcionarios = new ArrayList<>();
			while (rs.next()) {
				c = new Funcionario();
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setFuncao(rs.getString("funcao"));
				c.setSalario(rs.getDouble("salario"));
				c.setDataNascimento(rs.getString("data_nascimento"));
				c.setCnpjAcademiaResponsavel(rs.getString("CNPJ_Academia"));
				Funcionarios.add(c);
			}
			rs.close();
			stmt.close();
			return Funcionarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Funcionario> getLista(Academia ac) {
		String sql = "SELECT * FROM Funcionario WHERE CNPJ_Academia=?;";
		PreparedStatement stmt;
		Funcionario c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, ac.getCnpj());
			ResultSet rs = stmt.executeQuery();
			ArrayList<Funcionario> Funcionarios = new ArrayList<>();
			while (rs.next()) {
				c = new Funcionario();
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setFuncao(rs.getString("funcao"));
				c.setSalario(rs.getDouble("salario"));
				c.setCnpjAcademiaResponsavel(rs.getString("CNPJ_Academia"));
				Funcionarios.add(c);
			}
			rs.close();
			stmt.close();
			return Funcionarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int remover(Funcionario c) {
		int removeu = 0;
		int removeuDependentes = 0;
		String sqlDependentes = "DELETE FROM Dependente_funcionario WHERE cpf_funcionario = ?;";
		String sql = "DELETE FROM Funcionario WHERE cpf = ?;";
		PreparedStatement stmt;
		PreparedStatement stmtDependentes;
		try {
			stmtDependentes = (PreparedStatement) connection.prepareStatement(sqlDependentes);
			stmtDependentes.setString(1, c.getCpf());
			removeuDependentes = stmtDependentes.executeUpdate();
			stmtDependentes.close();
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, c.getCpf());
				removeu = stmt.executeUpdate();
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}

	public int alterar(Funcionario c) {
		int alterou = 0;
		String sql = "UPDATE Funcionario SET nome=?, funcao=?, salario=?, data_nascimento=? WHERE cpf=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getFuncao());
			stmt.setDouble(3, c.getSalario());
			stmt.setString(4, c.getDataNascimento());
			stmt.setString(5, c.getCpf());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}
