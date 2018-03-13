package projeto;

public class AvaliacaoAprendiz implements Avaliacao {

	private final double TAXA_APRENDIZ = 0.4;
	private final double ONUS_APRENDIZ = 0.01;

	@Override
	public double calculaValor(int doacao, double notaAtual) {
		return doacao * TAXA_APRENDIZ - ((3 - notaAtual) * ONUS_APRENDIZ);
	}
	
	@Override
	public String toString(){
		return "Aprendiz";		
	}
	
	
	
}
