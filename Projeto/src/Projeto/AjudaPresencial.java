package Projeto;

public class AjudaPresencial implements Ajuda {

	private String disciplina;
	private String horario;
	private String dia;
	private String localInteresse;
	private int id;
	
	public AjudaPresencial(String disciplina,String horario, String dia, String localInteresse,int id) {
		this.disciplina = disciplina;
		this.horario = horario;
		this.dia = dia;
		this.localInteresse = localInteresse;
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
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

	public int getId() {
		return id;
	}
	
	
}
