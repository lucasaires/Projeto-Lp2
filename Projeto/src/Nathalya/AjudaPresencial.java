package Nathalya;

public class AjudaPresencial extends AjudaOnline {

	private String horario;
	private String dia;
	private String localInteresse;
	
	public AjudaPresencial(String disciplina,String horario, String dia, String localInteresse,int id) {
		super(disciplina, id);
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
