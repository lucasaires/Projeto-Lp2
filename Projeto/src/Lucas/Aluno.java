package Lucas;

/**
 * 
 * @author Larissa Gabriela Amorim da Costa, Lucas Gomes Aires , Nathalya Raissa Guedes Alves ,
 *         Yally de Lima Galdino
 *
 */
public class Aluno implements Comparable<Aluno> {
	protected String matricula;
	private String nome;
	private int codigoCurso;
	private String email;
	private int nota = 5;
	private String telefone;

	public Aluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		if (nome.trim().equals("")) {
			throw new NullPointerException("Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		}
		if (email.indexOf("@") == 0 || email.indexOf("@") == email.length() - 1 || !email.contains("@")) {
			throw new IllegalArgumentException("Erro no cadastro de aluno: Email invalido");
		}
		this.matricula = matricula;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.telefone = telefone;
		this.email = email;
	}

	/**
	 * Retorna a matricula do aluno 
	 * @return a matricula do aluno 
	 */
	
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna o nome do aluno 
	 * @return o nome do aluno 
	 */
	
	public String getNome() {
		return nome;
	}


	/**
	 * Retorna a nota do aluno 
	 * @return nota do aluno 
	 */
	
	public int getNota() {
		return nota;
	}

	/**
	 * Atualiza a nota do aluno 
	 * @param nota uma nova nota para o aluno 
	 */
	
	public void setNota(int nota) {
		this.nota = nota;
	}

	/**
	 * 
	 * @return o codigo do curso 
	 */
	
	public int getCodigoCurso() {
		return codigoCurso;
	}

	/**
	 * 
	 * @return o email do aluno 
	 */
	
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @return o telefone do aluno 
	 */
	
	public String getTelefone() {
		return telefone;
	}

	@Override
	public String toString() {
		if (telefone.trim().equals("")) {
			return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.email;
		}
		return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.telefone + " - "
				+ this.email;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public int compareTo(Aluno o) {
		return this.nome.compareTo(o.getNome());
	}

}
