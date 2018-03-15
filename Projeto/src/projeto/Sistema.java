package projeto;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import projeto.Entidades.Aluno;
import projeto.Entidades.Tutor;
import projeto.ajuda.AjudaOnline;
import projeto.ajuda.AjudaPresencial;
import projeto.comparadores.ComparadorEmail;
import projeto.comparadores.ComparadorMatricula;

/**
 * 
 * @author Larissa Gabriela Amorim da Costa, Lucas Gomes Aires , Nathalya Raissa
 *         Guedes Alves , Yally de Lima Galdino
 *
 */

public class Sistema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 398040044492698556L;
	private double caixaSistema;
	private Map<String, Aluno> mapaAlunos;
	private String ultimaOrdem;
	private Map<String, Tutor> tutores;
	private Map<Integer, AjudaOnline> ajudas;
	private int indexAluno;
	private int indexTutor;

	public Sistema() {
		this.mapaAlunos = new HashMap<>();
		this.tutores = new HashMap<>();
		this.ajudas = new HashMap<>();
		this.ultimaOrdem = "NOME";
		this.caixaSistema = 0;
		this.indexAluno = 0;
		this.indexTutor = 0;

	}

	/**
	 * Cadastra um novo aluno
	 */

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email, indexAluno++);
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
		ArrayList<Aluno> alunosOrdenados = getListagemOrdenadaAlunos();

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
						escolhido.getTelefone(), escolhido.getEmail(), disciplina, proficiencia, indexTutor++);
				novoTutor.disciplinasTutor(disciplina);

				tutores.put(matricula, novoTutor);
				mapaAlunos.remove(matricula);
				mapaAlunos.put(matricula, novoTutor);

			}

			else {
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

	private Tutor pegaPorMatricula(String matricula) {
		return this.tutores.get(matricula);
	}

	/**
	 * Lista tutores cadastrados
	 * 
	 * @return lista de tutores
	 */

	public String listarTutores() {

		String saida = "";
		for (Tutor tutor : getListagemOrdenadaTutores()) {
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

	/**
	 * 
	 * @param matrAluno
	 *            aluno que quer ajuda
	 * @param disciplina
	 *            disciplina da ajuda
	 * @param horario
	 *            horário da ajuda
	 * @param dia
	 *            dia da ajuda
	 * @param localInteresse
	 *            local da ajuda
	 * @return id da ajuda
	 */

	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia,
			String localInteresse) {

		if (matrAluno.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");

		} else if (disciplina.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");

		} else if (horario.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");

		} else if (dia.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");

		} else if (localInteresse.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");
		}

		int id = ajudas.size();

		Tutor tutor = verificaAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse);
		AjudaPresencial novaAjuda = new AjudaPresencial(disciplina, horario, dia, localInteresse, matrAluno,
				tutor.getMatricula());

		ajudas.put(id, novaAjuda);

		return id + 1;

	}

	/**
	 * 
	 * @param matrAluno
	 * @param disciplina
	 *            disciplina da ajuda
	 * @param horario
	 *            horario da ajuda
	 * @param dia
	 *            dia da ajuda
	 * @param localInteresse
	 *            local da ajuda
	 * @return tutor escolhido
	 */

	private Tutor verificaAjudaPresencial(String matrAluno, String disciplina, String horario, String dia,
			String localInteresse) {

		int proficiencia = 0;
		Tutor melhorTutor = null;

		for (Tutor tutor : tutores.values()) {

			if (tutor.getDisciplina().equals(disciplina) && tutor.getProficiencia() > 0
					&& tutor.consultaHorario(horario, dia)) {

				if (tutor.getProficiencia() > proficiencia) {
					proficiencia = tutor.getProficiencia();
					melhorTutor = tutor;
				}
			}
		}
		return melhorTutor;
	}

	/**
	 * 
	 * @param matrAluno
	 *            aluno que quer a ajuda
	 * @param disciplina
	 *            disciplina da ajuda
	 * @return id da ajuda
	 */

	public int pedirAjudaOnline(String matrAluno, String disciplina) {

		if (matrAluno.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no pedido de ajuda online: matricula de aluno nao pode ser vazio ou em branco");

		} else if (disciplina.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro no pedido de ajuda online: disciplina nao pode ser vazio ou em branco");

		}

		int id = ajudas.size();
		Tutor tutor = verificaAjudaOnline(matrAluno, disciplina);
		AjudaOnline novaAjuda = new AjudaOnline(disciplina, matrAluno, tutor.getMatricula());

		ajudas.put(id, novaAjuda);

		return id + 1;
	}

	/**
	 * 
	 * @param matrAluno
	 * @param disciplina
	 *            disciplina da ajuda
	 * @return melhor tutor
	 */

	private Tutor verificaAjudaOnline(String matrAluno, String disciplina) {

		int proficiencia = 0;
		Tutor melhorTutor = null;

		for (Tutor tutor : tutores.values()) {

			if (tutor.getMatricula().equals(matrAluno) || (tutor.verificaDisciplinas(disciplina))) {

				if (tutor.getProficiencia() > proficiencia) {
					melhorTutor = tutor;
				}
			}
		}
		return melhorTutor;

	}

	/**
	 * 
	 * @param idAjuda
	 *            id da ajuda
	 * @return tutor da ajuda
	 */

	public String pegarTutor(int idAjuda) {

		if (idAjuda < 0) {
			throw new IllegalArgumentException("Erro ao tentar recuperar tutor : id nao pode menor que zero ");
		}

		if (!ajudas.containsKey(idAjuda - 1)) {
			throw new IllegalArgumentException("Erro ao tentar recuperar tutor : id nao encontrado ");
		}

		Tutor t = null;

		String a = "";

		if (ajudas.get(idAjuda - 1).getTipoAjuda().equals("online")) {

			AjudaOnline ajuda = ajudas.get(idAjuda - 1);

			t = verificaAjudaOnline(ajuda.getMatricula(), ajuda.getDisciplina());

			a = ajuda.toString(t.getMatricula());

		} else if (ajudas.get(idAjuda - 1).getTipoAjuda().equals("presencial")) {

			AjudaPresencial ajuda = (AjudaPresencial) ajudas.get(idAjuda - 1);

			t = verificaAjudaPresencial(ajuda.getMatricula(), ajuda.getDisciplina(), ajuda.getHorario(), ajuda.getDia(),
					ajuda.getlocalInteresse());

			a = ajuda.toString();
		}

		return a;

	}

	/**
	 * 
	 * @param idAjuda
	 *            ajuda que quer a informção
	 * @param atributo
	 *            qual informação sobre a ajuda
	 * @return atributo
	 */

	public String getInfoAjuda(int idAjuda, String atributo) {

		if (idAjuda < 0) {
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : id nao pode menor que zero ");
		}

		if (!ajudas.containsKey(idAjuda - 1)) {
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : id nao encontrado ");
		}
		if (atributo.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro ao tentar recuperar info da ajuda : atributo nao pode ser vazio ou em branco");
		}

		if (!atributo.equals("disciplina") && !atributo.equals("horario") && !atributo.equals("dia")
				&& !atributo.equals("localInteresse")) {

			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao encontrado");
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

	/**
	 * 
	 * @param idAjuda
	 *            id da ajuda
	 * @param nota
	 *            nota do tutor
	 */

	public void avaliarTutor(int idAjuda, int nota) {

		AjudaOnline ajuda;
		String matriculaAluno;

		if (nota < 0) {
			throw new IllegalArgumentException("Erro na avaliacao de tutor: nota nao pode ser menor que 0");

		} else if (nota > 5) {
			throw new IllegalArgumentException("Erro na avaliacao de tutor: nota nao pode ser maior que 5");
		}
		if (!ajudas.containsKey(idAjuda - 1)) {
			throw new IllegalArgumentException("Erro na avaliacao de tutor: id nao encontrado ");
		}

		else {
			ajuda = ajudas.get(idAjuda - 1);
			matriculaAluno = ajuda.getMatricula();
		}

		if (!ajudas.containsKey(idAjuda - 1)) {

			throw new IllegalArgumentException("Erro na avaliacao de tutor: id nao encontrado ");

		} else if (!ajuda.avaliarAjuda(matriculaAluno)) {
			throw new IllegalArgumentException("Erro na avaliacao de tutor: Ajuda ja avaliada");
		}

		Tutor tutor = tutores.get(ajudas.get(idAjuda - 1).getMatriculaTutor());

		tutor.modificaAvaliacao(nota);
	}

	/**
	 * 
	 * @param matriculaTutor
	 *            metrícula do tutor
	 * @return nota do tutor
	 */

	public String pegarNota(String matriculaTutor) {

		Locale.setDefault(new Locale("pt", "BR", "WIN"));

		DecimalFormat df = new java.text.DecimalFormat("#,##0.00", new DecimalFormatSymbols());

		return df.format(tutores.get(matriculaTutor).getNota());

	}

	/**
	 * 
	 * @param matriculaTutor
	 *            matricula do tutor
	 * @return nível do tutor
	 */

	public String pegarNivel(String matriculaTutor) {
		Tutor novo = tutores.get(matriculaTutor);
		return novo.getAvalicao();
	}

	/**
	 * 
	 * @param matriculaTutor
	 *            matrícula do tutor
	 * @param totalCentavos
	 *            dinheiro doado ao tutor
	 */

	public void doar(String matriculaTutor, int totalCentavos) {

		if (matriculaTutor.trim().isEmpty()) {
			throw new IllegalArgumentException("Matricula vazia");
		} else if (totalCentavos < 0) {
			throw new IllegalArgumentException("Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		} else if (!tutores.containsKey(matriculaTutor)) {
			throw new IllegalArgumentException("Erro na doacao para tutor: Tutor nao encontrado");
		}

		Tutor tutor = this.pegaPorMatricula(matriculaTutor);

		double taxaTutor = tutor.getTaxaTutor(totalCentavos);

		double total_sistema = Math.ceil((totalCentavos - taxaTutor));

		tutor.receberDinheiro(totalCentavos - total_sistema);
		this.caixaSistema += total_sistema;

	}

	/**
	 * 
	 * @param emailTutor
	 *            tutor
	 * @return dinheiro do tutor
	 */

	public int totalDinheiroTutor(String emailTutor) {

		if (emailTutor.trim().equals("") || emailTutor == null) {
			throw new IllegalArgumentException(
					"Erro na consulta de total de dinheiro do tutor: emailTutor nao pode ser vazio ou nulo");
		}

		boolean verifica = false;

		Tutor t = null;

		for (Tutor tutor : tutores.values()) {

			if (tutor.getEmail().equals(emailTutor)) {
				verifica = true;
				t = tutor;
			}
		}

		if (!verifica) {
			throw new IllegalArgumentException("Erro na consulta de total de dinheiro do tutor: Tutor nao encontrado");
		}

		return t.getDinheiro();
	}

	/**
	 * 
	 * @return dinheiro do sistema
	 */

	public int totalDinheiroSistema() {
		return (int) caixaSistema;
	}

	/**
	 * 
	 * @param atributo
	 *            nome da ordem da listagem
	 */

	public void configuraOrdem(String atributo) {
		switch (atributo) {

		case "NOME":
			ultimaOrdem = "NOME";
			break;
		case "EMAIL":
			ultimaOrdem = "EMAIL";
			break;
		case "MATRICULA":
			ultimaOrdem = "MATRICULA";
			break;

		default:
			throw new IllegalArgumentException("Ordem inválida");
		}

	}

	/**
	 * 
	 * @return lista ordenada de alunos
	 */

	private ArrayList<Aluno> getListagemOrdenadaAlunos() {
		ArrayList<Aluno> lista = new ArrayList<>();

		for (Aluno aluno : mapaAlunos.values())
			lista.add(aluno);

		ordenaLista(lista);

		return lista;

	}

	/**
	 * 
	 * @return lista ordenado de tutores
	 */

	private ArrayList<Tutor> getListagemOrdenadaTutores() {
		ArrayList<Tutor> lista = new ArrayList<>();

		for (Tutor tutor : tutores.values())
			lista.add(tutor);

		ordenaListaTutor(lista);

		return lista;

	}

	/**
	 * ordena alunos
	 * 
	 * @param lista
	 *            lista de alunos
	 */
	private void ordenaLista(List<Aluno> lista) {
		switch (ultimaOrdem) {
		case "NOME":
			Collections.sort(lista);
			break;
		case "EMAIL":
			Collections.sort(lista, new ComparadorEmail());
			break;
		case "MATRICULA":
			Collections.sort(lista, new ComparadorMatricula());
			break;
		}
	}

	/**
	 * ordena tutores
	 * 
	 * @param lista
	 *            lista de tutores
	 * 
	 */
	private void ordenaListaTutor(List<Tutor> lista) {
		switch (ultimaOrdem) {
		case "NOME":
			Collections.sort(lista);
			break;
		case "EMAIL":
			Collections.sort(lista, new ComparadorEmail());
			break;
		case "MATRICULA":
			Collections.sort(lista, new ComparadorMatricula());
			break;
		}
	}

}
