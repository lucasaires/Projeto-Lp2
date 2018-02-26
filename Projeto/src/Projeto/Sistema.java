package Projeto;


import java.util.HashMap;
import java.util.Map;

public class Sistema {

	private Map<String, Aluno> alunos;
	private Map<String, Tutor> tutores;

	public Sistema() {
		this.alunos = new HashMap<>();
		this.tutores = new HashMap<>();
	}

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(nome, matricula, codigoCurso, telefone, email);

		alunos.put(matricula, aluno);
	}

	public String recuperaAluno(String matricula) {
		return alunos.get(matricula).toString();
	}

	public String listarAlunos() {
		String saida = "";

		for (Aluno aluno : alunos.values()) {
			saida += aluno.toString() + ", ";
		}
		return saida.substring(0, (saida.length()-2));
	}

	public String getInfoAluno(String matricula, String atributo) {
		
		//fazer um método
		
		if (!alunos.containsKey(matricula)) {
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		}
		Aluno aluno = alunos.get(matricula);
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
		Aluno escolhido = alunos.get(matricula);
		Tutor novoTutor = new Tutor(escolhido.nome,escolhido.matricula,escolhido.codigoCurso,escolhido.telefone,escolhido.email,disciplina,proficiencia);
	
	}

	public String recuperaTutor(String matricula) {
		return "d";
		
	}

	public String listarTutores() {
		return "l";		
	}

}