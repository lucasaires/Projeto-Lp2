package Projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Larissa Gabriela Amorim da Costa, Lucas Gomes Aires , Nathalya Raíssa Guedes Alves,
 *         Yally de Lima Galdino
 *
 */
public class Tutor extends Aluno {

	private String disciplina;
	private int proficiencia;
	private int nota;
	private int dinheiro;
	private List<String> disciplinas;
	private Set<String> locais;
	private Map<String, ArrayList<String>> horarios;

	public Tutor(String nome, String matricula, int codigoCurso, String telefone, String email, String disciplina,
			int proficiencia) {

		super(nome, matricula, codigoCurso, telefone, email);

		if (disciplina.trim().equals("")) {
			throw new NullPointerException("Erro no cadastro de aluno: Disciplina nao pode ser vazio ou nulo");
		}

		this.disciplina = disciplina;
		this.proficiencia = proficiencia;
		this.nota = 4;
		this.dinheiro = 0;
		this.disciplinas = new ArrayList<String>();
		this.locais = new HashSet<String>();
		this.horarios = new HashMap<String, ArrayList<String>>();
	}

	/**
	 * 
	 * @param disciplina
	 */
	public void disciplinasTutor(String disciplina) {
		if (this.verificaDisciplinas(disciplina) == false) {
			disciplinas.add(disciplina);
		}
	}

	/**
	 * 
	 * @param disciplina
	 * @return
	 */
	public boolean verificaDisciplinas(String disciplina) {
		if (disciplinas.contains(disciplina)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @returnMap
	 */
	public int getProficiencia() {
		return proficiencia;
	}

	/**
	 * 
	 * @param proficiencia
	 */
	public void setProficiencia(int proficiencia) {
		this.proficiencia = proficiencia;
	}

	/**
	 * 
	 */
	public int getNota() {
		return nota;
	}

	/**
	 * 
	 */
	public void setNota(int nota) {
		this.nota = nota;
	}

	/**
	 * 
	 * @return
	 */
	public int getDinheiro() {
		return dinheiro;
	}

	/**
	 * 
	 * @param dinheiro
	 */
	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	/**
	 * 
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * 
	 * @return
	 */
	public String getDisciplina() {
		return disciplina;
	}
	
	public void cadastrarLocal(String local) {
		locais.add(local);
	}
	
	public boolean consultaLocal(String local) {
		return locais.contains(local);
	}
	
	public void cadastraHorario(String horario, String dia) {
		ArrayList<String> horas = horarios.get(dia);
		if (horas == null) {
			horas = new ArrayList<String>();
		}
		horas.add(horario);
		horarios.put(dia, horas);
	}
	
	public boolean consultaHorario(String horario, String dia) {
		if (horarios.get(dia) == null) {
			return false;
		}
		return horarios.get(dia).contains(horario);
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
