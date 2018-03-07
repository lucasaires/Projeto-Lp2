package Larissa;

public enum Avaliacao {
	TOP("Top"), TUTOR("Tutor"), APRENDIZ("Aprendiz");
	
	private String avaliacao;
	
	
	private Avaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public String getAvaliacao() {
		return avaliacao;
	}
}
