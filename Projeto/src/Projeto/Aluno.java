package Projeto;

public class Aluno {
	private String matricula;
	private String nome;
	private int codigoCurso;
	private String email;
	private int nota = 5;
	private String telefone;	
	
	public Aluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		if (nome.trim().equals("")) {
			throw new NullPointerException("Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		}
		if (email.indexOf("@") == 0 || email.indexOf("@") == email.length()-1) {
			throw new IllegalArgumentException("Erro no cadastro de aluno: Email invalido");
		}
		this.matricula = matricula;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.telefone = telefone;
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		if (telefone.trim().equals("")) {
		return this.matricula + " - " + this.nome + " - " +  this.codigoCurso + " - " + this.email;
	}
		return this.matricula + " - " +  this.nome + " - " + this.codigoCurso + " - " + this.telefone + " - " + this.email;
		
	}
}
