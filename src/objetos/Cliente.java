package objetos;

import java.util.ArrayList;

public class Cliente {
	protected String nome;
	protected ArrayList<String> arrayTelefones = new ArrayList<>();
	protected Endereco endereco;
	protected String cpf;
	protected String fichaCorporal;
	//Contrutor
	public Cliente(String nome, Endereco endereco, String cpf, String fichaCorporal) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.fichaCorporal = fichaCorporal;
	}
	public Cliente() {
		super();
	}
	//M�todos
	public void adicionarTelefone(String telefone) {
		for(int i = 0; i < arrayTelefones.size(); i++) {
			if(telefone.equals(arrayTelefones.get(i))) {
				System.out.println("O telefone j� existe.");
				return;
			}
		}
		arrayTelefones.add(telefone);
	}
	public void removerTelefone(String telefone) {
		for(int i = 0; i < arrayTelefones.size(); i++) {
			if(telefone.equals(arrayTelefones.get(i))) {
				arrayTelefones.remove(telefone);
				return;
			}
		}
		System.out.println("O telefone n�o existe.");
	}
	//Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<String> getArrayTelefones() {
		return arrayTelefones;
	}
	public void setArrayTelefones(ArrayList<String> arrayTelefones) {
		this.arrayTelefones = arrayTelefones;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getFichaCorporal() {
		return fichaCorporal;
	}
	public void setFichaCorporal(String fichaCorporal) {
		this.fichaCorporal = fichaCorporal;
	}
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", arrayTelefones=" + arrayTelefones + ", endereco=" + endereco + ", cpf="
				+ cpf + ", fichaCorporal=" + fichaCorporal + "]";
	}
	
}
