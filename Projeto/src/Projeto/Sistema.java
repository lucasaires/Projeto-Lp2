package Projeto;

import java.util.HashMap;
import java.util.Map;

public class Sistema {

	private Map<String, Aluno> mapaAlunos;

	public Sistema() {
		this.mapaAlunos = new HashMap<>();
	}

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);

		mapaAlunos.put(matricula, aluno);
	}

	public String recuperaAluno(String matricula) {
		return mapaAlunos.get(matricula).toString();
	}

	public String listarAlunos() {
		String saida = "";

		for (Aluno aluno : mapaAlunos.values()) {
			saida += aluno.toString() + ", ";
		}
		return saida.substring(0, (saida.length()-2));
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

}
