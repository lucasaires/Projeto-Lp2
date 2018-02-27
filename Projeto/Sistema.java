package Projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	public String recuperaAluno(String matricula) {
		if (!mapaAlunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por aluno: Aluno nao encontrado");
		}
		return mapaAlunos.get(matricula).toString();
	}

	public String listarAlunos() {
		String saida = "";
		ArrayList<Aluno> alunosOrdenados = new ArrayList<Aluno>();

		for (Aluno aluno : mapaAlunos.values()) {
			alunosOrdenados.add(aluno);
		}
		// comparar e ordenar
		return saida.substring(0, (saida.length() - 2));
	}

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

	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		if (proficiencia < 0 ) {
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
		}
		if (mapaAlunos.containsKey(matricula)) {
			Aluno escolhido = mapaAlunos.get(matricula);
			if (!tutores.containsKey(matricula)) {
				Tutor novoTutor = new Tutor(escolhido.getNome(), escolhido.getMatricula(), escolhido.getCodigoCurso(),
						escolhido.getTelefone(), escolhido.getEmail(), disciplina, proficiencia);
				tutores.put(matricula, novoTutor);
			} else {
				System.out.println(tutores.get(matricula).verificaDisciplinas(disciplina));
				if (tutores.get(matricula).verificaDisciplinas(disciplina) == false) {
					tutores.get(matricula).disciplinasTutor(disciplina);
					System.out.println(tutores.get(matricula).disciplinas);
				} else {
					throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
				}
			}
		}else {
		throw new IllegalArgumentException("Erro na definicao de papel: Tutor nao encontrado");
		}
	}

	public String recuperaTutor(String matricula) {
		if (!tutores.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		}

		return tutores.get(matricula).toString();
	}

	public String listarTutores() {
		return "lucas chato";
	}

	public void cadastrarHorario(String email, String horario, String dia) {

		
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		
		
	}

	//@Override
	//public int compareTo(Aluno o) {
		// TODO Auto-generated method stub
		//return 0;
	//}

}
