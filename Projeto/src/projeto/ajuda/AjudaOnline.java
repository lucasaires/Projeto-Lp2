package projeto.ajuda;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AjudaOnline implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2943938449572846846L;
	private String disciplina;
	private String matriculaAluno;
	private String matriculaTutor;
	protected String ajuda;
	private Set<String> avaliadores;
	
	
	
	public AjudaOnline(String disciplina, String matriculaAluno,String matriculaTutor) {
		this.disciplina = disciplina;
		this.matriculaAluno = matriculaAluno;
		this.matriculaTutor = matriculaTutor;
		this.ajuda = "online";
		this.avaliadores = new HashSet<>();
	}
	
	public String getMatriculaTutor() {
		return this.matriculaTutor;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public String getMatricula() {
		return matriculaAluno;
	}

	public String toString(String matricula) {
		return "Tutor - " + matricula + ", disciplina - " + disciplina;
	}

	public String getTipoAjuda() {
		return this.ajuda;
	}
	
	public boolean avaliarAjuda(String matriculaAluno) {
		return this.avaliadores.add(matriculaAluno);
	}
	
}
