package objetos;

public class Dependente {
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String grauParentesco;
	private Funcionario funcionarioResponsavel;
	private String cpfFuncionario;
	//Construtor
	public Dependente(String nome, String dataNascimento, String cpf, String grauParentesco,
			Funcionario funcionarioResponsavel, String cpfFuncionario) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.cpfFuncionario = cpfFuncionario;
		this.grauParentesco = grauParentesco;
		this.funcionarioResponsavel = funcionarioResponsavel;
	}
	public Dependente() {
		super();
	}

	//Getters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getGrauParentesco() {
		return grauParentesco;
	}
	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}
	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}
	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}
	public String getCpfFuncionario() {
		return cpfFuncionario;
	}
	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}
	@Override
	public String toString() {
		return "Dependente [nome=" + nome + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", grauParentesco="
				+ grauParentesco + ", funcionarioResponsavel=" + funcionarioResponsavel + ", cpfFuncionario="
				+ cpfFuncionario + "]";
	}
	
}
