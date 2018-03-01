package Larissa;

public class AjudaOnline implements Ajuda  {
	private String disciplina;
	private int id;
	private String tutor;
	
	public AjudaOnline(String disciplina,int id,String tutor) {
		this.disciplina = disciplina;
		this.id = id;
		this.tutor = tutor;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public int getId() {
		return id;
	}

	public String getTutor() {
		return tutor;
	}
	
	
}
