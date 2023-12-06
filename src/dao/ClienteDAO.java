package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Cliente;
import objetos.Endereco;

public class ClienteDAO {
	protected Connection connection;

	public ClienteDAO() {
		connection = new FabricaConexoes().getConnection();
	}

	public int inserir(Cliente c) {
		int inseriu = 0;
		String sql = "INSERT INTO Clientes(cpf, ficha_corporal, nome, cep, estado, cidade, bairro, rua, numero) VALUES (?,?,?,?,?,?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCpf());
			stmt.setString(2, c.getFichaCorporal());
			stmt.setString(3, c.getNome());
			stmt.setString(4, c.getEndereco().getCep());
			stmt.setString(5, c.getEndereco().getEstado());
			stmt.setString(6, c.getEndereco().getCidade());
			stmt.setString(7, c.getEndereco().getBairro());
			stmt.setString(8, c.getEndereco().getRua());
			stmt.setString(9, c.getEndereco().getNumero());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}

	public ArrayList<Cliente> getLista() {
		String sql = "SELECT * FROM Clientes WHERE cpf NOT IN (SELECT cpf FROM Cliente_premium);";
		PreparedStatement stmt;
		Cliente c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Cliente> Clientes = new ArrayList<>();
			while (rs.next()) {
				c = new Cliente();
				c.setCpf(rs.getString("cpf"));
				c.setFichaCorporal(rs.getString("ficha_corporal"));
				c.setNome(rs.getString("nome"));
				Endereco enderecoCliente = new Endereco();
				enderecoCliente.setCep(rs.getString("cep"));
				enderecoCliente.setEstado(rs.getString("estado"));
				enderecoCliente.setCidade(rs.getString("cidade"));
				enderecoCliente.setBairro(rs.getString("bairro"));
				enderecoCliente.setRua(rs.getString("rua"));
				enderecoCliente.setNumero(rs.getString("numero"));
				c.setEndereco(enderecoCliente);
				Clientes.add(c);
			}
			rs.close();
			stmt.close();
			return Clientes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int remover(Cliente c) {
		int removeu = 0;
		int removeuCliente = 0;
		String sqlCliente = "DELETE FROM Clientes_integrado_Academia WHERE CPF_CLIENTES=?";
		String sqlAparelhoCliente = "DELETE FROM Clientes_usam_aparelho WHERE CPF_CLIENTES=?";
		String sql = "DELETE FROM Clientes WHERE cpf = ?;";
		PreparedStatement stmtCliente;
		PreparedStatement stmt;
		PreparedStatement stmtAparelhoCliente;
		ClientePremiumDAO clientePremiumConnection = new ClientePremiumDAO();
		TelefoneDAO telefoneConnection = new TelefoneDAO();
		try {
			telefoneConnection.remover(c);
			clientePremiumConnection.remover(c.getCpf());
			stmtCliente = (PreparedStatement) connection.prepareStatement(sqlCliente);
			stmtCliente.setString(1, c.getCpf());
			removeuCliente = stmtCliente.executeUpdate();
			stmtCliente.close();

			stmtAparelhoCliente = (PreparedStatement) connection.prepareStatement(sqlAparelhoCliente);
			stmtAparelhoCliente.setString(1, c.getCpf());
			removeuCliente = stmtAparelhoCliente.executeUpdate();
			stmtAparelhoCliente.close();

			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCpf());
			removeu = stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}

	public int alterar(Cliente c) {
		int alterou = 0;
		String sql = "UPDATE Clientes SET ficha_corporal=?, nome=?, cep=?, estado=?, cidade=?, bairro=?, rua=?, numero=? WHERE cpf=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getFichaCorporal());
			stmt.setString(2, c.getNome());
			stmt.setString(3, c.getEndereco().getCep());
			stmt.setString(4, c.getEndereco().getEstado());
			stmt.setString(5, c.getEndereco().getCidade());
			stmt.setString(6, c.getEndereco().getBairro());
			stmt.setString(7, c.getEndereco().getRua());
			stmt.setString(8, c.getEndereco().getNumero());
			stmt.setString(9, c.getCpf());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}
