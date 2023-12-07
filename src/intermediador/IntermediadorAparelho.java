package intermediador;

import java.util.ArrayList;

import dao.AparelhoDAO;
import objetos.Aparelho;

public class IntermediadorAparelho {
	private AparelhoDAO aparelhoConnection = new AparelhoDAO();
	private ArrayList<Aparelho> arrayAparelho = new ArrayList<>();
	
	public IntermediadorAparelho() {
		
	}
	
	public ArrayList<Aparelho> listarAparelhos(){
		arrayAparelho = new ArrayList<>();
		arrayAparelho = aparelhoConnection.getLista();
		if(arrayAparelho == null) {
			return(null);
		}
		return(arrayAparelho);
	}
	
	public int remover(Aparelho c) {
		return(aparelhoConnection.remover(c));
	}
	
	public int editar(Aparelho c) {
		return(aparelhoConnection.alterar(c));
	}

	public int inserir(Aparelho c) {
		return(aparelhoConnection.inserir(c));
	}

}
