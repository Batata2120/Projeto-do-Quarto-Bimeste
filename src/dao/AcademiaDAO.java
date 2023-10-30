package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Academia;
import objetos.Aparelho;
import objetos.Funcionario;

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

	public int remover(Academia c) {
		int removeu = 0;
		int removeuAcademia = 0;
		String sqlAcademia = "DELETE FROM Academia_tem_loja WHERE CNPJ_Academia=?";
		String sql = "DELETE FROM Academia WHERE CNPJ = ?;";
		String sqlAcademiaCliente = "DELETE FROM Clientes_integrado_Academia WHERE CNPJ_ACADEMIA=?";
		PreparedStatement stmt;
		PreparedStatement stmtAcademia;
		PreparedStatement stmtAcademiaCliente;
		AparelhoDAO aparelhoConnection = new AparelhoDAO();
		FuncionarioDAO funcionarioConnection = new FuncionarioDAO();
		try {
			ArrayList<Aparelho> listaAparelhos = aparelhoConnection.getLista(c);
			ArrayList<Funcionario> listaFuncionario = funcionarioConnection.getLista(c);
			for(int i = 0; i < listaAparelhos.size(); i++) {
				aparelhoConnection.remover(listaAparelhos.get(i));
			}
			for(int i = 0; i < listaFuncionario.size(); i++) {
				funcionarioConnection.remover(listaFuncionario.get(i));
			}
			stmtAcademia = (PreparedStatement) connection.prepareStatement(sqlAcademia);
			stmtAcademia.setString(1, c.getCnpj());
			removeuAcademia = stmtAcademia.executeUpdate();
			stmtAcademia.close();
			if (removeuAcademia == 1) {
				stmtAcademiaCliente = (PreparedStatement) connection.prepareStatement(sqlAcademiaCliente);
				stmtAcademiaCliente.setString(1, c.getCnpj());
				removeuAcademia = stmtAcademiaCliente.executeUpdate();
				stmtAcademiaCliente.close();
			}
			if (removeuAcademia == 1) {
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, c.getCnpj());
				removeu = stmt.executeUpdate();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}

}
