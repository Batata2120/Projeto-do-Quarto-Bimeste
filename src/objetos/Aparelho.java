package objetos;

public class Aparelho {
	private String qualidade;
	private double preco;
	private int id;
	private String marca;
	private String cnpj_academia;
	private Academia academiaResponsavel;
	//Construtor
	public Aparelho(String qualidade, double preco, int id, String cnpj_academia, String marca) {
		super();
		this.qualidade = qualidade;
		this.preco = preco;
		this.id = id;
		this.marca = marca;
		this.cnpj_academia = cnpj_academia;
	}
	public Aparelho() {
		super();
	}
	//Getters e Setters
	public String getQualidade() {
		return qualidade;
	}
	public void setQualidade(String qualidade) {
		this.qualidade = qualidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Academia getAcademiaResponsavel() {
		return academiaResponsavel;
	}
	public void setAcademiaResponsavel(Academia academiaResponsavel) {
		this.academiaResponsavel = academiaResponsavel;
	}
	public String getCnpj_academia() {
		return cnpj_academia;
	}
	public void setCnpj_academia(String cnpj_academia) {
		this.cnpj_academia = cnpj_academia;
	}
	@Override
	public String toString() {
		return "Aparelho [qualidade=" + qualidade + ", preco=" + preco + ", id=" + id + ", marca=" + marca
				+ ", cnpj_academia=" + cnpj_academia + ", academiaResponsavel=" + academiaResponsavel + "]";
	}
	
}
