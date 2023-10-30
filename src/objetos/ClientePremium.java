package objetos;

public class ClientePremium extends Cliente{
	private String dietaPlanejada;
	private double desconto;
	//Construtor
	public ClientePremium(String nome, Endereco endereco, String cpf, String fichaCorporal, String dietaPlanejada,
			double desconto) {
		super(nome, endereco, cpf, fichaCorporal);
		this.dietaPlanejada = dietaPlanejada;
		this.desconto = desconto;
	}
	public ClientePremium() {
		super();
	}
	//Getters and Setters
	public String getDietaPlanejada() {
		return dietaPlanejada;
	}
	public void setDietaPlanejada(String dietaPlanejada) {
		this.dietaPlanejada = dietaPlanejada;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	@Override
	public String toString() {
		return "ClientePremium [dietaPlanejada=" + dietaPlanejada + ", desconto=" + desconto + ", nome=" + nome
				+ ", arrayTelefones=" + arrayTelefones + ", endereco=" + endereco + ", cpf=" + cpf + ", fichaCorporal="
				+ fichaCorporal + "]";
	}
}
