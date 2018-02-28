package Projeto;

public class AjudaOnline implements Ajuda  {
	private String disciplina;
	private int id;
	
	public AjudaOnline(String disciplina,int id) {
		this.disciplina = disciplina;
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public int getId() {
		return id;
	}
	
}
