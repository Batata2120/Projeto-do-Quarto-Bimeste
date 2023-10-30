package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.ClientePremium;
import objetos.Endereco;

public class ClientePremiumDAO extends ClienteDAO {
	public ClientePremiumDAO() {
		connection = new FabricaConexoes().getConnection();
	}
	public int inserir(ClientePremium c) {
		int inseriu = 0;
		String sql = "INSERT INTO Cliente_premium(cpf, Dieta_planejada, Desconto) VALUES (?,?,?);";
		PreparedStatement stmt;
		try {
			ClienteDAO clienteConnection = new ClienteDAO();
			clienteConnection.inserir(c);
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getCpf());
			stmt.setString(2, c.getDietaPlanejada());
			stmt.setDouble(3, c.getDesconto());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}
	public ArrayList<ClientePremium> getListaClientePremium() {
		String sql = "SELECT c.* , cp.Dieta_planejada, cp.Desconto FROM Cliente_premium AS cp, Clientes AS c WHERE c.cpf=cp.cpf;";
		PreparedStatement stmt;
		ClientePremium c;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<ClientePremium> Clientes = new ArrayList<>();
			while (rs.next()) {
				c = new ClientePremium();
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
				c.setDietaPlanejada(rs.getString("Dieta_planejada"));
				c.setDesconto(rs.getDouble("Desconto"));
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
	public int remover(ClientePremium c) {
		int removeu = 0;
		String sql = "DELETE FROM Cliente_premium WHERE cpf = ?;";
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
	public int remover(String cpf) {
		int removeu = 0;
		String sql = "DELETE FROM Cliente_premium WHERE cpf = ?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, cpf);
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}
	public int alterar(ClientePremium c) {
		int alterou = 0;
		String sql = "UPDATE Cliente_premium SET Dieta_planejada=?, Desconto=? WHERE cpf=?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, c.getDietaPlanejada());
			stmt.setDouble(2, c.getDesconto());
			stmt.setString(3, c.getCpf());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
}


