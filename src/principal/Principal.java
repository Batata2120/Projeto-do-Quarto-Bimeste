package principal;

import java.util.ArrayList;

import dao.*;
import objetos.*;

public class Principal {
	public static void main(String[] args) {
		AcademiaDAO academiaConnection = new AcademiaDAO();
		AparelhoDAO aparelhoConnection = new AparelhoDAO();
		ClienteDAO clienteConnection = new ClienteDAO();
		ClientePremiumDAO clientePremiumConnection = new ClientePremiumDAO();
		DependenteDAO dependenteConnection = new DependenteDAO();
		FuncionarioDAO funcionarioConnection = new FuncionarioDAO();
		LojaSuplementoDAO lojaSuplementoConnection = new LojaSuplementoDAO();
		ProdutoDAO produtoConnection = new ProdutoDAO();
		TelefoneDAO telefoneConnection = new TelefoneDAO();
		ArrayList<Academia> academias = academiaConnection.getLista();
		ArrayList<Aparelho> aparelhos = aparelhoConnection.getLista();
		ArrayList<Cliente> clientes = clienteConnection.getLista();
		ArrayList<ClientePremium> clientesPremium = clientePremiumConnection.getListaClientePremium();
		ArrayList<Dependente> dependentes = dependenteConnection.getLista();
		ArrayList<Funcionario> funcionarios = funcionarioConnection.getLista();
		ArrayList<LojaSuplemento> lojasDeSuplemento = lojaSuplementoConnection.getLista();
		for(int i = 0; i < academias.size(); i++) {
			System.out.println(academias.get(i).toString());
		}
		for(int i = 0; i < aparelhos.size(); i++) {
			System.out.println(aparelhos.get(i).toString());
		}
		for(int i = 0; i < clientes.size(); i++) {
			clientes.get(i).setArrayTelefones(telefoneConnection.getLista(clientes.get(i)));
			System.out.println(clientes.get(i).toString());
		}
		for(int i = 0; i < clientesPremium.size(); i++) {
			System.out.println(clientesPremium.get(i).toString());
		}
		for(int i = 0; i < dependentes.size(); i++) {
			System.out.println(dependentes.get(i).toString());
		}
		for(int i = 0; i < funcionarios.size(); i++) {
			System.out.println(funcionarios.get(i).toString());
		}
		for(int i = 0; i < lojasDeSuplemento.size(); i++) {
			lojasDeSuplemento.get(i).setArrayProdutos(produtoConnection.getLista(lojasDeSuplemento.get(i)));
			System.out.println(lojasDeSuplemento.get(i).toString());
		}
		Endereco endereco1 = new Endereco("12345567", "São Paulo", "São Carlos", "Aracy", "Bicão", "990");
		academiaConnection.inserir(new Academia("Smart winner", "Papai Noel", "12345678901239", "Rua Alvarado Nogueira, centro SP"));
		aparelhoConnection.inserir(new Aparelho("Média qualidade", 1000, 6, "12345678901239", "Nike"));
		clienteConnection.inserir(new Cliente("Mateus", endereco1, "12345678949", "34cm-Braço,60cm-panturrilha,175cm-toráx" ));
		clientePremiumConnection.inserir(new ClientePremium("Marcos", endereco1, "12345678950", "34cm-Braço,60cm-panturrilha,175cm-toráx", "ovo", 10));
		dependenteConnection.inserir(new Dependente("Pions Marcos", "2017-03-22", "78901234589", "Padrinho", null, "98765432110"));
		funcionarioConnection.inserir(new Funcionario("Markin", "987654321", "Faxineiro", 1500, "2000-05-09", "12345678901234", null));
		lojaSuplementoConnection.inserir(new LojaSuplemento("56789012345680", "Rua pamonha", "Mascoa"));
		produtoConnection.inserir(new LojaSuplemento("56789012345680", "Rua pamonha", "Mascoa"), "Banana e canela");
		telefoneConnection.inserir(new Cliente("Mateus", endereco1, "12345678949", "34cm-Braço,60cm-panturrilha,175cm-toráx"), "555-9289");
		academiaConnection.remover(new Academia("Smart winner", "Papai Noel", "12345678901239", "Rua Alvarado Nogueira, centro SP"));
		aparelhoConnection.remover(new Aparelho("Média qualidade", 1000, 6, "12345678901239", "Nike"));
		clienteConnection.remover(new Cliente("Mateus", endereco1, "12345678949", "34cm-Braço,60cm-panturrilha,175cm-toráx" ));
		clientePremiumConnection.remover(new ClientePremium("Marcos", endereco1, "12345678950", "34cm-Braço,60cm-panturrilha,175cm-toráx", "ovo", 10));
		dependenteConnection.remover(new Dependente("Pions Marcos", "2017-03-22", "78901234589", "Padrinho", null, "98765432110"));
		funcionarioConnection.remover(new Funcionario("Markin", "987654321", "Faxineiro", 1500, "2000-05-09", "12345678901234", null));
		lojaSuplementoConnection.remover(new LojaSuplemento("56789012345680", "Rua pamonha", "Mascoa"));
		produtoConnection.remover(new LojaSuplemento("56789012345680", "Rua pamonha", "Mascoa"), "Banana e canela");
		telefoneConnection.remover(new Cliente("Mateus", endereco1, "12345678949", "34cm-Braço,60cm-panturrilha,175cm-toráx"), "555-9289");
	
	}
}
