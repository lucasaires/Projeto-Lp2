package Yally;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Larissa Gabriela Amorim da Costa, Lucas Gomes Aires , Nathalya Raissa
 *         Guedes Alves , Yally de Lima Galdino
 *
 */

public class Sistema {

	private Map<String, Aluno> mapaAlunos;
	private Map<String, Tutor> tutores;
	private Map<Integer, Ajuda> ajudas;

	public Sistema() {
		this.mapaAlunos = new HashMap<>();
		this.tutores = new HashMap<>();
		this.ajudas = new HashMap<>();
	}

	/**
	 * Cadastra um novo aluno
	 */

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);
		if (mapaAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		}
		mapaAlunos.put(matricula, aluno);
	}

	/**
	 * Recupera um aluno a partir da sua matricula
	 * 
	 * @param matricula
	 *            matricula do aluno que vai ser recuperado
	 * @return aluno recuperado
	 */

	public String recuperaAluno(String matricula) {
		if (!mapaAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por aluno: Aluno nao encontrado");
		}
		return mapaAlunos.get(matricula).toString();
	}

	/**
	 * Lista alunos cadastrados
	 * 
	 * @return lista de alunos ordenados a partir do nome
	 */

	public String listarAlunos() {
		String saida = "";
		ArrayList<Aluno> alunosOrdenados = new ArrayList<Aluno>();
		for (Aluno aluno : mapaAlunos.values()) {
			alunosOrdenados.add(aluno);

		}
		Collections.sort(alunosOrdenados);
		for (Aluno aluno : alunosOrdenados) {
			saida += aluno.toString() + ", ";
		}
		return saida.substring(0, (saida.length() - 2));
	}

	/**
	 * Recupera informa�oes de um aluno
	 * 
	 * @param matricula
	 *            matricula do aluno
	 * @param atributo
	 *            informa�ao que se quer do aluno
	 * @return informa�oes do atributo
	 */

	public String getInfoAluno(String matricula, String atributo) {
		if (!mapaAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		}
		Aluno aluno = mapaAlunos.get(matricula);
		String atri = "";

		switch (atributo) {
		case "Nome":
			atri = aluno.getNome();
			break;

		case "Telefone":
			atri = aluno.getTelefone();
			break;

		case "Email":
			atri = aluno.getEmail();
			break;

		default:
			atri = "Atributo invalido";
			break;
		}

		return atri;
	}

	/**
	 * Transforma um aluno em um tutor
	 * 
	 * @param matricula
	 *            matricula do aluno
	 * @param disciplina
	 *            disciplina que o aluno vai ser tornar tutor
	 * @param proficiencia
	 *            o quao habil ele se sente na disciplina
	 */

	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if (proficiencia < 0) {
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
		}
		if (mapaAlunos.containsKey(matricula)) {
			Aluno escolhido = mapaAlunos.get(matricula);
			if (!tutores.containsKey(matricula)) {
				Tutor novoTutor = new Tutor(escolhido.getNome(), escolhido.getMatricula(), escolhido.getCodigoCurso(),
						escolhido.getTelefone(), escolhido.getEmail(), disciplina, proficiencia);
				novoTutor.disciplinasTutor(disciplina);
				tutores.put(matricula, novoTutor);
			} else {
				if (tutores.get(matricula).verificaDisciplinas(disciplina))
					throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");

				else
					tutores.get(matricula).disciplinasTutor(disciplina);
			}
		} else {
			throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		}
	}

	/**
	 * Recupera informa�oes sobre um tutor a partit da sua matricula
	 * 
	 * @param matricula
	 *            matricula do tutor a ser recuperado
	 * @return infoma�oes sobre o tutor
	 */

	public String recuperaTutor(String matricula) {
		if (!tutores.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		}

		return tutores.get(matricula).toString();
	}

	/**
	 * Lista tutores cadastrados
	 * 
	 * @return lista de tutores
	 */

	public String listarTutores() {
		String saida = "";
		for (Tutor tutor : tutores.values()) {
			saida += tutor.toString() + ", ";
		}
		return saida.substring(0, (saida.length() - 2));
	}

	/**
	 * Cadastra Horario
	 * 
	 * @param email
	 *            email do tutor
	 * @param horario
	 *            horario disponivel
	 * @param dia
	 *            dia disponivel
	 * 
	 */

	public void cadastrarHorario(String email, String horario, String dia) {
		if (email.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		} else if (horario.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: horario nao pode ser vazio ou em branco");
		} else if (dia.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: dia nao pode ser vazio ou em branco");
		}
		Tutor tutor = buscaTutor(email);
		if (tutor == null) {
			throw new IllegalArgumentException("Erro no cadastrar horario: tutor nao cadastrado");
		}
		tutor.cadastraHorario(horario, dia);
	}

	/**
	 * Consulta se o horario esta disponivel
	 * 
	 * @param email
	 *            do tutor
	 * @param horario
	 *            horario a consultar
	 * @param dia
	 *            dia a consultar
	 * @return true se o horario esta disponivel
	 */

	public boolean consultaHorario(String email, String horario, String dia) {

		if (email.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		} else if (horario.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: horario nao pode ser vazio ou em branco");
		} else if (dia.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastrar horario: dia nao pode ser vazio ou em branco");
		}
		Tutor tutor = buscaTutor(email);
		if (tutor == null) {
			return false;
		}
		return tutor.consultaHorario(horario, dia);
	}

	/**
	 * Busca se o tutor esta cadastrado
	 * 
	 * @param email
	 *            email do tutor
	 * @return tutor
	 */

	private Tutor buscaTutor(String email) {
		for (Tutor tutor : tutores.values()) {
			if (tutor.getEmail().equals(email)) {
				return tutor;
			}
		}
		return null;
	}

	/**
	 * Cadastra local de atendimento
	 * 
	 * @param email
	 *            email do tutor
	 * @param local
	 *            local a ser cadastrado
	 */

	public void cadastrarLocalDeAtendimento(String email, String local) {
		if (email.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		} else if (local.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no cadastrar local de atendimento: local nao pode ser vazio ou em branco");
		}
		Tutor tutorBusca = buscaTutor(email);
		if (tutorBusca == null) {
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: tutor nao cadastrado");
		}
		tutorBusca.cadastrarLocal(local);
	}

	/**
	 * consulta se o local esta� disponivel
	 * 
	 * @param email
	 *            do tutor
	 * @param local
	 *            local a procurar
	 * @return true se o local esta disponivel e false se o local nao esta
	 *         disponivel
	 */

	public boolean consultaLocal(String email, String local) {
		if (email.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		} else if (local.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no cadastrar local de atendimento: local nao pode ser vazio ou em branco");
		}
		Tutor tutor = buscaTutor(email);
		if (tutor == null) {
			return false;
		}
		return tutor.consultaLocal(local);
	}

	public int pedirAjudaPresencial(String disciplina, String horario, String dia, String localInteresse) {
		// cadastrar tutor p ajuda
		int id = ajudas.size();
		AjudaPresencial novaAjuda = new AjudaPresencial(disciplina, horario, dia, localInteresse, id, null);
		ajudas.put(id, novaAjuda);
		return id;
	}

	public int pedirAjudaOnline(String disciplina) {
		int id = ajudas.size();
		// cadastrar tutor p ajuda
		AjudaOnline novaAjuda = new AjudaOnline(disciplina, id, null);
		ajudas.put(id, novaAjuda);

	

		return id;
	}

	public String pegarTutor(Integer idAjuda) {

		if (!ajudas.containsKey(idAjuda)) {
			throw new IllegalArgumentException("Ajuda não casdastrada");
		}
		return ajudas.get(idAjuda).pegarTutor();

	}

	public String getInfoAjuda(Integer idAjuda, String atributo) {
		if (!ajudas.containsKey(idAjuda)) {
			throw new IllegalArgumentException("Ajuda não casdastrada");
		}
		if (atributo.trim().equals("")) {
			throw new IllegalArgumentException("Não existe atributo");
		}
		if(atributo == null) {
			throw new NullPointerException("Atributo nulo");
		}
		
		Ajuda ajuda = ajudas.get(idAjuda);
		String atri = "";
		
		switch (atributo) {
		case "Diplina":
			atri = ajuda.getdisDiplina();
			break;

		case "Horario":
			atri = ajuda.getHorario();
			break;

		case "Dia":
			atri = ajuda.getDia();
			break;

		case "Local Interesse":
			atri = ajuda.getLocalInteresse();
			break;

		case "Id":
			atri = ajuda.getId();
			break;

		case "Tutor":
			atri = ajuda.pegarTutor();
			break;
	
		default:
			atri = "Atributo invalido";
			break;
		}

		return atri;

	}

}
