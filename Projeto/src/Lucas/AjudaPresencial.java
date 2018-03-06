package Lucas;

public class AjudaPresencial extends AjudaOnline {

	private String horario;
	private String dia;
	private String localInteresse;

	public AjudaPresencial(String disciplina, String horario, String dia, String localInteresse, String matricula) {
		super(disciplina, matricula);
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

	public String toString() {
		return "Tutor - " + getMatricula() + ", horario - " + getHorario() + ", dia - " + getDia() + ", local - "
				+ getlocalInteresse() + ",disciplina - " + getDisciplina();

	}
}
