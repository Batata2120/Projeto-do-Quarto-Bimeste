package objetos;

import java.util.ArrayList;

public class LojaSuplemento {
	private String cnpj;
	private String localizacao;
	private String proprietario;
	private ArrayList<String> arrayProdutos = new ArrayList<>();
	//Construtor
	public LojaSuplemento(String cnpj, String localizacao, String proprietario) {
		super();
		this.cnpj = cnpj;
		this.localizacao = localizacao;
		this.proprietario = proprietario;
	}
	public LojaSuplemento() {
		super();
	}
	//Métodos
	public void adicionarProduto(String produto) {
		for(int i = 0; i < arrayProdutos.size(); i++) {
			if(produto.equals(arrayProdutos.get(i))) {
				System.out.println("O produto j� existe.");
				return;
			}
		}
		arrayProdutos.add(produto);
	}
	public void removerTelefone(String telefone) {
		for(int i = 0; i < arrayProdutos.size(); i++) {
			if(telefone.equals(arrayProdutos.get(i))) {
				arrayProdutos.remove(telefone);
				return;
			}
		}
		System.out.println("O produto n�o existe.");
	}
	//Getters e Setters
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	public ArrayList<String> getArrayProdutos() {
		return arrayProdutos;
	}
	public void setArrayProdutos(ArrayList<String> arrayProdutos) {
		this.arrayProdutos = arrayProdutos;
	}
	@Override
	public String toString() {
		return "LojaSuplemento [cnpj=" + cnpj + ", localizacao=" + localizacao + ", proprietario=" + proprietario
				+ ", arrayProdutos=" + arrayProdutos + "]";
	}
	
}
