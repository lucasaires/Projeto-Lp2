package Yally;


public class AjudaOnline  {
	private String disciplina;
	private int id;
	private String tutor;
	private String matrAluno;
	
	
	public AjudaOnline(String matrAluno,String disciplina,int id,String tutor) {
		this.matrAluno = matrAluno;
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

	public String getMatrAluno() {
		return matrAluno;
	}

	
}
