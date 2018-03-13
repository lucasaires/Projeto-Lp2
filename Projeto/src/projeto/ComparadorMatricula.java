package projeto;

import java.util.Comparator;

public class ComparadorMatricula implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return o1.getPosicaoCadastro() - o2.getPosicaoCadastro();
	}

}
