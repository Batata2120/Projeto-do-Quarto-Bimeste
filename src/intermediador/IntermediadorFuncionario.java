package intermediador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.FuncionarioDAO;
import objetos.Funcionario;

public class IntermediadorFuncionario {
	private FuncionarioDAO funcionarioConnection = new FuncionarioDAO();
	private ArrayList<Funcionario> arrayFuncionario = new ArrayList<>();

	public IntermediadorFuncionario() {
		
	}

	public ArrayList<Funcionario> listarFuncionarios() {
		arrayFuncionario = new ArrayList<>();
		arrayFuncionario = funcionarioConnection.getLista();
		if (arrayFuncionario == null) {
			return (null);
		}
		return (arrayFuncionario);
	}

	public int remover(Funcionario c) {
		return (funcionarioConnection.remover(c));
	}

	public int editar(Funcionario c) {
		return (funcionarioConnection.alterar(c));
	}

	public int inserir(Funcionario c) {
		return (funcionarioConnection.inserir(c));
	}

}
