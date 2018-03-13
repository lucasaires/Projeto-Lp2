package projeto;

import easyaccept.EasyAccept;

public class Facade {
	Sistema sistema;

	public Facade() {
		sistema = new Sistema();
	}

	public static void main(String[] args) {
		args = new String[] { "projeto.Facade", "testes/us1_test.txt", "testes/us2_test.txt", "testes/us3_test.txt",
				"testes/us4_test.txt", "testes/us5_test.txt", "testes/us6_test.txt" };
		EasyAccept.main(args);

	}

	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		sistema.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}

	public String recuperaAluno(String matricula) {
		return sistema.recuperaAluno(matricula);
	}

	public String listarAlunos() {
		return sistema.listarAlunos();
	}

	public String getInfoAluno(String matricula, String atributo) {
		return sistema.getInfoAluno(matricula, atributo);
	}

	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		sistema.tornarTutor(matricula, disciplina, proficiencia);
	}

	public String recuperaTutor(String matricula) {
		return sistema.recuperaTutor(matricula);
	}

	public String listarTutores() {
		return sistema.listarTutores();
	}

	public void cadastrarHorario(String email, String horario, String dia) {
		sistema.cadastrarHorario(email, horario, dia);
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		sistema.cadastrarLocalDeAtendimento(email, local);
	}

	public boolean consultaHorario(String email, String horario, String dia) {
		return sistema.consultaHorario(email, horario, dia);

	}

	public boolean consultaLocal(String email, String local) {
		return sistema.consultaLocal(email, local);

	}

	public int pedirAjudaPresencial(String matrAluno, String disciplina, String horario, String dia,
			String localInteresse) {
		return sistema.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse);
	}

	public int pedirAjudaOnline(String matrAluno, String disciplina) {
		return sistema.pedirAjudaOnline(matrAluno, disciplina);
	}

	public String pegarTutor(int idAjuda) {
		return sistema.pegarTutor(idAjuda);
	}

	public String getInfoAjuda(int idAjuda, String atributo) {
		return sistema.getInfoAjuda(idAjuda, atributo);
	}

	public String avaliarTutor(int idAjuda, int nota) {
		return sistema.avaliarTutor(idAjuda, nota);
	}

	public double pegarNota(String matriculaTutor) {
		return sistema.pegarNota(matriculaTutor);
	}

	public String pegarNivel(String matriculaTutor) {
		return sistema.pegarNivel(matriculaTutor);
	}

	public void doar(String matriculaTutor, int totalCentavos) {
		sistema.doar(matriculaTutor, totalCentavos);
	}

	public int totalDinheiroTutor(String emailTutor) {
		return sistema.totalDinheiroTutor(emailTutor);
	}

	public int totalDinheiroSistema() {
		return totalDinheiroSistema();
	}
	
	public void configurarOrdem(String atributo) {
		sistema.configuraOrdem(atributo);
	}
	
	public void salvar() {
		sistema.salvar();
	}
	public void carregar() {
		sistema.carregar();
	}
	public void limpar() {
		sistema.limpar();
		
	}

}