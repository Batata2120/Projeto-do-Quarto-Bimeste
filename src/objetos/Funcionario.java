package objetos;

public class Funcionario {
	private String nome;
	private String cpf;
	private String funcao;
	private double salario;
	private String dataNascimento;
	private String cnpjAcademiaResponsavel;
	private Academia academiaResponsavel;
	//Construtor
	public Funcionario(String nome, String cpf, String funcao, double salario, String dataNascimento,
			String cnpjAcademiaResponsavel, Academia academiaResponsavel) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.funcao = funcao;
		this.salario = salario;
		this.dataNascimento = dataNascimento;
		this.cnpjAcademiaResponsavel = cnpjAcademiaResponsavel;
		this.academiaResponsavel = academiaResponsavel;
	}
	public Funcionario() {
		super();
	}
	//Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCnpjAcademiaResponsavel() {
		return cnpjAcademiaResponsavel;
	}
	public void setCnpjAcademiaResponsavel(String cnpjAcademiaResponsavel) {
		this.cnpjAcademiaResponsavel = cnpjAcademiaResponsavel;
	}
	public Academia getAcademiaResponsavel() {
		return academiaResponsavel;
	}
	public void setAcademiaResponsavel(Academia academiaResponsavel) {
		this.academiaResponsavel = academiaResponsavel;
	}
	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", cpf=" + cpf + ", funcao=" + funcao + ", salario=" + salario
				+ ", dataNascimento=" + dataNascimento + ", cnpjAcademiaResponsavel=" + cnpjAcademiaResponsavel
				+ ", academiaResponsavel=" + academiaResponsavel + "]";
	}
	
	
}
