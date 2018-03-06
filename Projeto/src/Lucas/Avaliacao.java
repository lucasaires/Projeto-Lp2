package Lucas;

public enum Avaliacao {
	TOP("TOP"), TUTOR("TUTOR"), APRENDIZ("APRENDIZ");
	
	private Avaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	private String avaliacao;
	
	public String getAvaliacao() {
		return avaliacao;
	}
}
