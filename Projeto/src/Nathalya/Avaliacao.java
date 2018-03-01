package Nathalya;

public enum Avaliacao {
	NOTA_INICIAL(5), NOTA_4(4), NOTA_3(3), NOTA_2(2), NOTA_1(1), NOTA_0(0);
	
	private Avaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	private int avaliacao;
	
	public int getAvaliacao() {
		return avaliacao;
	}
}
