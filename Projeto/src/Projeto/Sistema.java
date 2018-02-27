package Projeto;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sistema {

	private Map<String, Aluno> mapaAlunos;
	private Map<String, Tutor> tutores;
	private List<Horario> horarios;
	private List<Local> locais;

	public Sistema() {
		this.mapaAlunos = new HashMap<>();
		this.tutores = new HashMap<>();
		this.horarios = new ArrayList<>();
		this.locais = new ArrayList<Local>();
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
		Collections.sort(alunosOrdenados);
		for (Aluno aluno : alunosOrdenados) {
			saida += aluno.toString() + ", ";
		}
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
		
		if (proficiencia < 0) {
			throw new IllegalArgumentException("Erro na definicao de papel: Proficiencia invalida");
		}
		if (mapaAlunos.containsKey(matricula)) {
			Aluno escolhido = mapaAlunos.get(matricula);
			if (!tutores.containsKey(matricula)) {
				Tutor novoTutor = new Tutor(escolhido.getNome(), escolhido.getMatricula(), escolhido.getCodigoCurso(),
						escolhido.getTelefone(), escolhido.getEmail(), disciplina, proficiencia);
				tutores.put(matricula, novoTutor);
			} else {
				if (tutores.get(matricula).verificaDisciplinas(disciplina) == false) {
					tutores.get(matricula).disciplinasTutor(disciplina);

				} else {
					throw new IllegalArgumentException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
				}
			}
		} else {
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

		if (email.trim().equals("")) {

			throw new IllegalArgumentException("Erro no cadastrar horario: email nao pode ser vazio ou em branco");

		} else if (horario.trim().equals("")) {

			throw new IllegalArgumentException("Erro no cadastrar horario: horario nao pode ser vazio ou em branco");

		} else if (dia.trim().equals("")) {

			throw new IllegalArgumentException("Erro no cadastrar horario: dia nao pode ser vazio ou em branco");
		}
		
		if(!consultaEmail(email)) {
			
			throw new IllegalArgumentException("Erro no cadastrar horario: tutor nao cadastrado");
			
		}

		Horario novo = new Horario(email, horario, dia);

		horarios.add(novo);

	}

	private boolean consultaEmail(String email) {
		
		boolean verifica = false;
		for(Tutor tutor : tutores.values()) {
			if (tutor.getEmail().equals(email)){
				
				verifica = true;
			}
			
		}
		return verifica;
	}

	public boolean consultaHorario(String email, String horario, String dia) {

		return verificahorario(email, horario, dia);
	}

	private boolean verificahorario(String email, String horario, String dia) {

		boolean verifica = false;

		Horario novo = new Horario(email, horario, dia);

		for (Horario HorarioDoTutor : horarios) {

			if (HorarioDoTutor.equals(novo)) {

				verifica = true;
			}

		}
		return verifica;
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		if (email.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");

		} else if (local.trim().equals("")) {

			throw new IllegalArgumentException(
					"Erro no cadastrar local de atendimento: local nao pode ser vazio ou em branco");
		}
		
		if(!consultaEmail(email)) {
			
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: tutor nao cadastrado");
			
		}

		Local novo = new Local(email, local);

		locais.add(novo);

	}

	public boolean consultaLocal(String email, String local) {

		return consultalocal(email, local);
	}

	private boolean consultalocal(String email, String local) {
		Local novo = new Local(email, local);
		boolean consulta = false;

		for (Local localDoTutor : locais) {

			if (localDoTutor.equals(novo)) {
				consulta = true;
			}

		}

		return consulta;
	}

	public int pedirAjudaPresencial(String disciplina, String horario, String dia, String localInteresse) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	// @Override
	// public int compareTo(Aluno o) {
	// TODO Auto-generated method stub
	// return 0;
	// }

}
