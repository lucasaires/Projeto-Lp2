package projeto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import easyaccept.EasyAccept;

public class Facade {
	Sistema sistema;

	public Facade() {
		sistema = new Sistema();
	}

	public static void main(String[] args) {
		args = new String[] { "projeto.Facade", "testes/us6_test.txt" };
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

	public void avaliarTutor(int idAjuda, int nota) {
		sistema.avaliarTutor(idAjuda, nota);
	}

	public String pegarNota(String matriculaTutor) {
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
		return sistema.totalDinheiroSistema();
	}

	public void configurarOrdem(String atributo) {
		sistema.configuraOrdem(atributo);
	}

	public void salvar() throws ClassNotFoundException, IOException {
		try {
			String nomeArquivo = "save.bin";
			FileOutputStream f = new FileOutputStream(nomeArquivo);
			ObjectOutputStream obj = new ObjectOutputStream(f);
			obj.writeObject(sistema);
			obj.close();
		} catch (IOException e) {
			throw new IOException("Falha ao Salvar Sistema");
		}

	}

	public void carregar() throws ClassNotFoundException {
		try {
			String nomeArquivo = "save.bin";
			File arquivo = new File(nomeArquivo);
			if (arquivo.exists()) {
				FileInputStream f = new FileInputStream(nomeArquivo);
				ObjectInputStream obj = new ObjectInputStream(f);
				Sistema sistemaLido = (Sistema) obj.readObject();
				this.sistema = sistemaLido;
				obj.close();
			} else {
				this.sistema = new Sistema();
			}
		} catch (IOException e) {
			throw new ClassNotFoundException("Falha na leitura");
		}
	}

	public void limpar() {
		this.sistema = new Sistema();

	}

}