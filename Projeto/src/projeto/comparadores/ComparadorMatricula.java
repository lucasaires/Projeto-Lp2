package projeto.comparadores;

import java.io.Serializable;
import java.util.Comparator;

import projeto.Entidades.Aluno;

public class ComparadorMatricula implements Comparator<Aluno>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 502383117773801865L;

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return o1.getPosicaoCadastro() - o2.getPosicaoCadastro();
	}

}
