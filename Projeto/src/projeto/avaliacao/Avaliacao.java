package projeto.avaliacao;

import java.io.Serializable;

public interface Avaliacao extends Serializable {
	
	public double calculaValor(int doacao, double notaAtual);
	
	public String toString();
}
