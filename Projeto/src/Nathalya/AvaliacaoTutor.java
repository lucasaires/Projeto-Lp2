package Nathalya;

public class AvaliacaoTutor implements Avaliacao{

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
