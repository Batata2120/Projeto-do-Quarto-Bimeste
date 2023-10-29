package objetos;

public class Academia {
	private String nome;
	private String proprietario;
	private String cnpj;
	private String localizacao;
	//Construtor
	public Academia(String nome, String proprietario, String cnpj, String localizacao) {
		super();
		this.nome = nome;
		this.proprietario = proprietario;
		this.cnpj = cnpj;
		this.localizacao = localizacao;
	}
	public Academia() {
		super();
	}
	//Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
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
}
