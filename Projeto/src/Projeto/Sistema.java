package Projeto;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Larissa Gabriela Amorim da Costa, Lucas Gomes Aires , Nathalya Raíssa Guedes Alves ,
 *         Yally de Lima Galdino
 *
 */
public class Sistema {

	private Map<String, Aluno> mapaAlunos;
	private Map<String, Tutor> tutores;

	public Sistema() {
		this.mapaAlunos = new HashMap<>();
		this.tutores = new HashMap<>();
	}

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);
		if (mapaAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
		}
		mapaAlunos.put(matricula, aluno);
	}

	/**
	 * 
	 * @param matricula
	 * @return
	 */
	public String recuperaAluno(String matricula) {
		if (!mapaAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por aluno: Aluno nao encontrado");
		}
		return mapaAlunos.get(matricula).toString();
	}

	/**
	 * 
	 * @return
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
	 * 
	 * @param matricula
	 * @param atributo
	 * @return
	 */
	public String getInfoAluno(String matricula, String atributo) {
		if (!mapaAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		}
		Aluno aluno = mapaAlunos.get(matricula);
		switch (atributo) {
		case "Nome":
			return aluno.getNome();

		case "Telefone":
			return aluno.getTelefone();

		case "Email":
			return aluno.getEmail();

		default:
			return "Atributo invalido";
		}
	}

	/**
	 * 
	 * @param matricula
	 * @param disciplina
	 * @param proficiencia
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
	 * 
	 * @param matricula
	 * @return
	 */
	public String recuperaTutor(String matricula) {
		if (!tutores.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		}

		return tutores.get(matricula).toString();
	}

	/**
	 * 
	 * @return
	 */
	public String listarTutores() {
		String saida = "";
		for (Tutor tutor : tutores.values()) {
			saida += tutor.toString() + ", ";
		}
		return saida.substring(0, (saida.length() - 2));
	}

	/**
	 * 
	 * @param email
	 * @param horario
	 * @param dia
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
	 * 
	 * @param email
	 * @param horario
	 * @param dia
	 * @return
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		Tutor tutor = buscaTutor(email);
		if (tutor == null) {
			return false;
		}
		return tutor.consultaHorario(horario, dia);
	}
	
	private Tutor buscaTutor(String email) {		
		for (Tutor tutor : tutores.values()) {
			if (tutor.getEmail().equals(email)) {
				return tutor;
			}
		} 
		return null;
	}
	
	/**
	 * 
	 * @param email
	 * @param local
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
	 * 
	 * @param email
	 * @param local
	 * @return
	 */
	public boolean consultaLocal(String email, String local) {
		Tutor tutor = buscaTutor(email);
		if (tutor == null) {
			return false;
		}
		return tutor.consultaLocal(local);
	}

}
