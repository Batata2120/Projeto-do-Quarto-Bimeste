package intermediador;

import java.util.ArrayList;

import dao.ClienteDAO;
import dao.ClientePremiumDAO;
import objetos.Cliente;
import objetos.ClientePremium;

public class IntermediadorCliente {
	private ClienteDAO clienteConnection = new ClienteDAO();
	private ClientePremiumDAO clientePremiumConnection = new ClientePremiumDAO();
	private ArrayList<Cliente> arrayCliente = new ArrayList<>();
	
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
	
	public int editar(Cliente c) {
		return(clienteConnection.alterar(c));
	}
	
}
