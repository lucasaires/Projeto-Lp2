package Yally;

public class AjudaPresencial extends AjudaOnline {

	private String horario;
	private String dia;
	private String localInteresse;
	
	public AjudaPresencial(String matrAluno,String disciplina,String horario, String dia, String localInteresse,int id,String tutor) {
		super(matrAluno,disciplina,  id, tutor);
		this.horario = horario;
		this.dia = dia;
		this.localInteresse = localInteresse;
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
	
}
