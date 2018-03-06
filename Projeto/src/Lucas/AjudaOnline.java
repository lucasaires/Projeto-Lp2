package Lucas;

public class AjudaOnline {
	private String disciplina;
	private String matricula;
	protected String ajuda;
	
	
	public AjudaOnline(String disciplina, String matricula) {
		this.disciplina = disciplina;
		this.matricula = matricula;
		this.ajuda = "online";
	}

	public String getDisciplina() {
		return disciplina;
	}

	public String getMatricula() {
		return matricula;
	}

	public String toString() {
		return "Tutor - " + getMatricula() + ", disciplina - " + disciplina;
	}

	public String getTipoAjuda() {
		return this.ajuda;
	}
	
}
