package projeto;

public class AvaliacaoTop implements Avaliacao{
	
	private final double TAXA_TOP = 0.9;
	private final double BONUS_TOP = 0.01;
	
	@Override
	public String toString(){
		return "Top";		
	}

	@Override
	public double calculaValor(int doacao, double notaAtual) {
		return (doacao * TAXA_TOP) + (notaAtual - 4.5) * BONUS_TOP;
	}
}
