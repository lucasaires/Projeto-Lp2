package projeto.comparadores;

import java.io.Serializable;
import java.util.Comparator;

import projeto.Entidades.Aluno;

public class ComparadorEmail implements Comparator<Aluno>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4141169725365747319L;

	@Override
	public int compare(Aluno t1, Aluno t2) {

		int compare;

		if (t1.getEmail().compareTo(t2.getEmail()) == 0)
			compare = t1.getPosicaoCadastro() - t2.getPosicaoCadastro();

		else
			compare = t1.getEmail().compareTo(t2.getEmail());

		return compare;

	}

}
