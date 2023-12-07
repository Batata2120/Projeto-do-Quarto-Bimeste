package intermediador;

import java.util.ArrayList;

import dao.ClienteDAO;
import dao.ClientePremiumDAO;
import dao.TelefoneDAO;
import objetos.Cliente;
import objetos.ClientePremium;

public class IntermediadorCliente {
	private ClienteDAO clienteConnection = new ClienteDAO();
	private ClientePremiumDAO clientePremiumConnection = new ClientePremiumDAO();
	private ArrayList<Cliente> arrayCliente = new ArrayList<>();
	private TelefoneDAO telefoneConnection = new TelefoneDAO();
	
	public IntermediadorCliente() {
		
	}
	
	public ArrayList<Cliente> listarClientes(){
		arrayCliente = new ArrayList<>();
		ArrayList<Cliente> arrayLista = clienteConnection.getLista();
		ArrayList<ClientePremium> arrayListaPremium = clientePremiumConnection.getListaClientePremium();
		if(arrayLista == null || arrayListaPremium == null) {
			return(null);
		}
		for(Cliente cliente : arrayLista) {
			arrayCliente.add(cliente);
		}
		for(ClientePremium cliente : arrayListaPremium) {
			arrayCliente.add(cliente);
		}
		return(arrayCliente);
	}
	
	public int remover(Cliente c) {
		return(clienteConnection.remover(c));
	}
	public int removerPremium(ClientePremium c) {
		return(clientePremiumConnection.remover(c));
	}
	
	public int editar(Cliente c) {
		return(clienteConnection.alterar(c));
	}
	public int editarPremium(ClientePremium c) {
		return(clientePremiumConnection.alterar(c));
	}
	public int inserir(Cliente c) {
		return(clienteConnection.inserir(c));
	}
	public int inserirPremium(ClientePremium c) {
		return(clientePremiumConnection.inserir(c));
	}
	public int inserirTelefone(String t, Cliente c) {
		return(telefoneConnection.inserir(c, t));
	}
	public int removerTelefone(String t, Cliente c) {
		return(telefoneConnection.remover(c, t));
	}
	public ArrayList<String> listarTelefones(Cliente c) {
		return(telefoneConnection.getLista(c));
	}
	
}
