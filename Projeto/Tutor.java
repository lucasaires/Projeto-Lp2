package Projeto;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Aluno {

	private String disciplina;
	private int proficiencia;
	private int nota;
	private int dinheiro;
	public List<String> disciplinas;

	public Tutor(String nome, String matricula, int codigoCurso, String telefone, String email, String disciplina,
			int proficiencia) {

		super(nome, matricula, codigoCurso, telefone, email);

		this.disciplina = disciplina;
		this.proficiencia = proficiencia;
		this.nota = 4;
		this.dinheiro = 0;
		this.disciplinas = new ArrayList<>();
	}

	public void disciplinasTutor(String disciplina) {
		disciplinas.add(disciplina);
	}

	public boolean verificaDisciplinas(String disciplina) {
		boolean aux = false;
		for (String disc : disciplinas) {
			if (disc.equals(disciplina)) {
				aux = true;
			}
		}
		return aux;
	}

	public int getProficiencia() {
		return proficiencia;
	}

	public void setProficiencia(int proficiencia) {
		this.proficiencia = proficiencia;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getDisciplina() {
		return disciplina;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutor other = (Tutor) obj;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		return true;
	}

}
