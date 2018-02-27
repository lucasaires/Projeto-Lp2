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
	 * adiciona disciplinas do tutor 
	 * @param disciplina disciplina a ser adicionada
	 */
	
	public void disciplinasTutor(String disciplina) {
		if (this.verificaDisciplinas(disciplina) == false) {
			disciplinas.add(disciplina);
		}
	}

	/**
	 * Verifica se o tutor já é tutor de alguma disciplina 
	 * @param disciplina disciplina disciplina a procurar 
	 * @return true se o tutor já é tutor da disciplina dada e false caso não.  
	 */
	
	public boolean verificaDisciplinas(String disciplina) {
		if (disciplinas.contains(disciplina)) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna o quão hábil ele se sente na disciplina
	 * @returnMap retorna a proficiência do tutor 
	 */
	
	public int getProficiencia() {
		return proficiencia;
	}

	/**
	 * Muda a proficiência do tutor  
	 * @param proficiencia o quão hábil ele se sente na disciplina
	 */
	
	public void setProficiencia(int proficiencia) {
		this.proficiencia = proficiencia;
	}

	/**
	 * Retorna a nota
	 * @return nota
	 */
	
	public int getNota() {
		return nota;
	}

	/**
	 * Muda a nota 
	 * @param nota nota 
	 */
	
	public void setNota(int nota) {
		this.nota = nota;
	}

	/**
	 * Retorna o dinheiro  
	 * @return dinheiro 
	 */
	
	public int getDinheiro() {
		return dinheiro;
	}

	/**
	 * atualiza o dinheiro  
	 * @param dinheiro dinheiro 
	 */
	
	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	/**
	 * Recupera a matrícula do tutor 
	 * @return matricula 
	 */
	
	public String getMatricula() {
		return matricula;
	}
	
	/**
	 * Cadastra locais de atendimentos 
	 * @param local local a ser cadastrado 
	 */
	
	public void cadastrarLocal(String local) {
		locais.add(local);
	}
	
	/**
	 * Consulta se o local está cadastrado 
	 * @return true se estiver e false se não estiver cadastrado 
	 */
	
	public boolean consultaLocal(String local) {
		return locais.contains(local);
	}
	
	/**
	 * Cadastra horarios disponíveis 
	 * @param horario horário para ser cadastrado 
	 * @param dia dia a ser cadastrado  
	 */
	
	
	public void cadastraHorario(String horario, String dia) {
		ArrayList<String> horas = horarios.get(dia);
		if (horas == null) {
			horas = new ArrayList<String>();
		}
		horas.add(horario);
		horarios.put(dia, horas);
	}
	
	/**
	 * Consulta se o horário está disponível 
	 * @return true se estiver disponível e false se não estiver disponível
	 */
	
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
