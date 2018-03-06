package Lucas;

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
	private Map<Integer, AjudaOnline> ajudas;
	private List<Tutor> melhorTutores;

	public Sistema() {
		this.mapaAlunos = new HashMap<>();
		this.tutores = new HashMap<>();
		this.ajudas = new HashMap<>();
		this.melhorTutores = new ArrayList<>();
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
	 * Recupera informacoes de um aluno
	 * 
	 * @param matricula
	 *            matricula do aluno
	 * @param atributo
	 *            informacao que se quer do aluno
	 * @return informacoes do atributo
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
	 * Recupera informacoes sobre um tutor a partir da sua matricula
	 * 
	 * @param matricula
	 *            matricula do tutor a ser recuperado
	 * @return infomacoes sobre o tutor
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
	 * consulta se o local esta disponivel
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

	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia,
			String localInteresse) {
		// cadastrar tutor p ajuda

		int id = 0;
		
		

		for (Aluno aluno : mapaAlunos.values()) {

			if (aluno.matricula.equals(matrAluno)) {

				AjudaPresencial novaAjuda = new AjudaPresencial(disciplina, horario, dia, localInteresse, matrAluno);
				id = ajudas.size();
				escolheTutorPresencial(novaAjuda);
				
				ajudas.put(id, novaAjuda);

			}

		}

		return id + 1;

	}

	public int pedirAjudaOnline(String matrAluno, String disciplina) {
		// cadastrar tutor p ajuda

		int id = ajudas.size();
		AjudaOnline novaAjuda = new AjudaOnline(disciplina, matrAluno);

		escolheTutorOnline(novaAjuda);

		ajudas.put(id, novaAjuda);

		return id + 1;
	}

	private Tutor verificaProeficienciaParaOnline(AjudaOnline ajuda) {
		// analisa o tutor de maior proeficiencia

		int proeficiencia = 0;
		Tutor melhor = null ;

		for (Tutor tutor : tutores.values()) {

			if (tutor.getProficiencia() > proeficiencia && tutor.verificaDisciplinas(ajuda.getDisciplina())) {

				proeficiencia = tutor.getProficiencia();
				melhor = tutor;
			}
		}
		return melhor;
	}

	private Tutor escolheTutorOnline(AjudaOnline ajuda) {
		Tutor aux = null;
		
		for (Tutor tutor : tutores.values()) {
			
			if (tutor.verificaDisciplinas(ajuda.getDisciplina())) {
				
				aux = verificaProeficienciaParaOnline(ajuda);
			}
		}
		
		return aux;
	
	}

	private Tutor escolheTutorPresencial(AjudaPresencial ajuda) {
		// analisa o melhor tutor para a ajuda Presencial cadastrada
		Tutor aux = null;
		int proeficiencia = 0;

		for (Tutor tutor : tutores.values()) {
			if (tutor.verificaDisciplinas(ajuda.getDisciplina())) {
				if (tutor.consultaHorario(ajuda.getHorario(), ajuda.getDia())
						&& tutor.consultaLocal(ajuda.getlocalInteresse())) {
					if (tutor.getProficiencia() > proeficiencia) {
						aux = tutor;
						melhorTutores.add(aux);
						
					}
				}
			}
		}
		return aux;
	}

	public String pegarTutor(int idAjuda) {

		String melhorTutor = "";

		if (!ajudas.containsKey(idAjuda - 1)) {
			throw new IllegalArgumentException("Ajuda nao casdastrada");
		}
		for (Tutor tutor : melhorTutores) {

			if (tutor.matricula.equals(ajudas.get(idAjuda - 1).getMatricula())) {

				melhorTutor = tutor.toString();
			}

		}
		return melhorTutor;

	}

	public String getInfoAjuda(int idAjuda, String atributo) {
		if (!ajudas.containsKey(idAjuda)) {
			throw new IllegalArgumentException("Ajuda nao casdastrada");
		}
		if (atributo.trim().equals("")) {
			throw new IllegalArgumentException("Nao existe atributo");
		}

		if (ajudas.get(idAjuda - 1) instanceof AjudaPresencial) {

			AjudaPresencial ajuda = (AjudaPresencial) ajudas.get(idAjuda - 1);

			String atri = "";

			switch (atributo) {
			case "disciplina":
				atri = ajuda.getDisciplina();
				break;

			case "horario":
				atri = ajuda.getHorario();
				break;

			case "dia":
				atri = ajuda.getDia();
				break;

			case "localInteresse":
				atri = ajuda.getlocalInteresse();
				break;

			default:
				atri = "Atributo invalido";
				break;
			}

			return atri;

		} else {

			AjudaOnline ajuda = ajudas.get(idAjuda - 1);

			String atri = "";

			switch (atributo) {
			case "disciplina":
				atri = ajuda.getDisciplina();
				break;

			default:
				atri = "Atributo invalido";
				break;
			}

			return atri;
		}

	}

}
