package projeto.ajuda;

public class AjudaPresencial extends AjudaOnline {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6767538481180344873L;
	private String horario;
	private String dia;
	private String localInteresse;


	public AjudaPresencial(String disciplina, String horario, String dia, String localInteresse, String matriculaAluno,String matriculaTutor) {
		super(disciplina, matriculaAluno,matriculaTutor);
		this.horario = horario;
		this.dia = dia;
		this.localInteresse = localInteresse;
		this.ajuda = "presencial";

	}

	public String getHorario() {
		return horario;
	}

	public String getDia() {
		return dia;
	}

	public String getlocalInteresse() {
		return localInteresse;
	}
	
	@Override
	public String toString() {
		return "Tutor - " + getMatriculaTutor() + ", horario - "+ getHorario() + ", dia - " + getDia() + ", local - "
				+ getlocalInteresse() + ", disciplina - " + getDisciplina();

	}
}
