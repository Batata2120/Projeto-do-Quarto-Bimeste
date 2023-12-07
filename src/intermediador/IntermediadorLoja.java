package intermediador;

import java.util.ArrayList;

import dao.LojaSuplementoDAO;
import objetos.LojaSuplemento;

public class IntermediadorLoja {
	private LojaSuplementoDAO LojaSuplementoConnection = new LojaSuplementoDAO();
	private ArrayList<LojaSuplemento> arrayLojaSuplemento = new ArrayList<>();

	public IntermediadorLoja() {
		
	}

	public ArrayList<LojaSuplemento> listarLojaSuplementos() {
		arrayLojaSuplemento = new ArrayList<>();
		arrayLojaSuplemento = LojaSuplementoConnection.getLista();
		if (arrayLojaSuplemento == null) {
			return (null);
		}
		return (arrayLojaSuplemento);
	}

	public int remover(LojaSuplemento c) {
		return (LojaSuplementoConnection.remover(c));
	}

	public int editar(LojaSuplemento c) {
		return (LojaSuplementoConnection.alterar(c));
	}

	public int inserir(LojaSuplemento c) {
		return (LojaSuplementoConnection.inserir(c));
	}

}
