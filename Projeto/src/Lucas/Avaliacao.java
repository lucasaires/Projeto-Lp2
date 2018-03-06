package Lucas;

public enum Avaliacao {
	TOP("TOP"), TUTOR("TUTOR"), APRENDIZ("APRENDIZ");
	
	private String avaliacao;
	
	
	private Avaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public String getAvaliacao() {
		return avaliacao;
	}
}
