package Lucas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Larissa Gabriela Amorim da Costa, Lucas Gomes Aires , Nathalya Raissa
 *         Guedes Alves, Yally de Lima Galdino
 *
 */

public class Tutor extends Aluno {

	private String disciplina;
	private int proficiencia;
	private double nota;
	private int dinheiro;
	private String avaliacao;
	private List<String> disciplinas;
	private Set<String> locais;
	private Map<String, ArrayList<String>> horarios;

	public Tutor(String nome, String matricula, int codigoCurso, String telefone, String email, String disciplina,
			int proficiencia) {

		super(nome, matricula, codigoCurso, telefone, email);

		if (disciplina.trim().equals("")) {
			throw new NullPointerException("Erro no cadastro de aluno: Disciplina nao pode ser vazio ou nulo");
		}

		if (proficiencia <= 0) {
			throw new NullPointerException("Erro no cadastro de aluno: Proficiencia nao pode ser menor que 0");
		}

		this.disciplina = disciplina;
		this.proficiencia = proficiencia;
		this.nota = 4.00;
		this.dinheiro = 0;
		this.disciplinas = new ArrayList<String>();
		this.locais = new HashSet<String>();
		this.horarios = new HashMap<String, ArrayList<String>>();
		this.avaliacao = Avaliacao.TUTOR.getAvaliacao();

	}

	/**
	 * adiciona disciplinas do tutor
	 * 
	 * @param disciplina
	 *            disciplina a ser adicionada
	 */

	public void disciplinasTutor(String disciplina) {
		if (this.verificaDisciplinas(disciplina) == false) {
			disciplinas.add(disciplina);
		}
	}

	public String getDisciplina() {
		return disciplina;
	}

	/**
	 * Verifica se o tutor ja eh tutor de alguma disciplina
	 * 
	 * @param disciplina
	 *            disciplina disciplina a procurar
	 * @return true se o tutor ja eh tutor da disciplina dada e false caso nao.
	 */

	public boolean verificaDisciplinas(String disciplina) {
		if (disciplinas.contains(disciplina)) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna o quao habil ele se sente na disciplina
	 * 
	 * @returnMap retorna a proficiencia do tutor
	 */

	public int getProficiencia() {
		return proficiencia;
	}

	/**
	 * Muda a proficiencia do tutor
	 * 
	 * @param proficiencia
	 *            o quao habil ele se sente na disciplina
	 */

	public void setProficiencia(int proficiencia) {
		this.proficiencia = proficiencia;
	}

	/**
	 * Retorna a nota
	 * 
	 * @return nota
	 */

	public double getNota() {
		return nota;
	}

	/**
	 * Muda a nota
	 * 
	 * @param nota
	 *            nota
	 */

	public double calculaNota(int nota) {

		return this.nota = ((this.nota * 5.00) + nota) / 6.00;

	}

	/**
	 * Retorna o dinheiro
	 * 
	 * @return dinheiro
	 */

	public int getDinheiro() {
		return dinheiro;
	}

	/**
	 * atualiza o dinheiro
	 * 
	 * @param dinheiro
	 *            dinheiro
	 */

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	/**
	 * Recupera a matricula do tutor
	 * 
	 * @return matricula
	 */

	public String getMatricula() {
		return matricula;
	}

	/**
	 * Cadastra locais de atendimentos
	 * 
	 * @param local
	 *            local a ser cadastrado
	 */

	public void cadastrarLocal(String local) {
		locais.add(local);
	}

	/**
	 * Consulta se o local esta� cadastrado
	 * 
	 * @return true se estiver e false se nao estiver cadastrado
	 */

	public boolean consultaLocal(String local) {
		return locais.contains(local);

	}

	/**
	 * Cadastra horarios disponiveis
	 * 
	 * @param horario
	 *            horario para ser cadastrado
	 * @param dia
	 *            dia a ser cadastrado
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
	 * Consulta se o horario esta disponivel
	 * 
	 * @return true se estiver disponivel e false se nao estiver disponivel
	 */

	public boolean consultaHorario(String horario, String dia) {

		boolean monitor = false;
		if (horarios.containsKey(dia) || horarios.get(dia).equals(horario)) {
			monitor = true;
		}

		return monitor;
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

	public String modificaAvaliacao(double valor) {

		if (valor > 4.5) {

			this.avaliacao = Avaliacao.TOP.getAvaliacao();

		} else if (valor > 3 && valor <= 4.5) {

			this.avaliacao = Avaliacao.TUTOR.getAvaliacao();

		} else {

			this.avaliacao = Avaliacao.APRENDIZ.getAvaliacao();
		}

		return this.avaliacao;
	}

	public String getAvalicao() {
		return avaliacao;
	}

}
