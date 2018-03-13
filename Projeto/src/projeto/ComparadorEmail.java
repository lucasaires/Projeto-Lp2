package projeto;

import java.util.Comparator;

public class ComparadorEmail implements Comparator<Aluno> {

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
