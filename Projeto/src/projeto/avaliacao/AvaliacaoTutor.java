package projeto.avaliacao;

public class AvaliacaoTutor implements Avaliacao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9048575129147758425L;
	private final double TAXA_TUTOR = 0.8;
	
	@Override
	public double calculaValor(int doacao, double notaAtual) {
		return doacao * TAXA_TUTOR;
	}
	
	@Override
	public String toString(){
		return "Tutor";		
	}
	

}
