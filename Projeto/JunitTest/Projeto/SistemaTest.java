package Projeto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {

	Aluno a;
	Sistema sistema;

	@Before
	public void testSistema() {
		a = new Aluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema = new Sistema();

	}

	@Test
	public void testCadastrarAluno() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraAlunoRepetido() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.cadastrarAluno("George", "13", 5, "33106000", "principegeorge@imperio.com");
	}

	@Test
	public void testRecuperaAluno() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		assertEquals("13 - Son Goku - " + 5 + " - 40028922 - songoku@dbs.com", sistema.recuperaAluno(a.getMatricula()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRecuperaAlunoNaoCadastrado() {
		sistema.recuperaAluno("3");
	}

	@Test
	public void testListarAlunos() {
		sistema.cadastrarAluno("George", "11", 3, "33106000", "principegeorge@imperio.com");
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");

		assertEquals("11 - George - " + 3 + " - 33106000 - principegeorge@imperio.com, 13 - Son Goku - " + 5
				+ " - 40028922 - songoku@dbs.com", sistema.listarAlunos());
	}

	@Test
	public void testGetInfoAlunoNome() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		assertEquals("Son Goku", sistema.getInfoAluno("13", "Nome"));
	}

	@Test
	public void testGetInfoAlunoTelefone() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		assertEquals("40028922", sistema.getInfoAluno("13", "Telefone"));
	}

	@Test
	public void testGetInfoAlunoEmail() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		assertEquals("songoku@dbs.com", sistema.getInfoAluno("13", "Email"));
	}

	@Test
	public void testGetInfoAlunoAtributoinvalido() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		assertEquals("Atributo invalido", sistema.getInfoAluno("13", "Sexo"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetInfoAlunoMatriculaInvalida() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		assertEquals("Atributo invalido", sistema.getInfoAluno("3", "Nome"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorProficienciaInsuficiente() {

		sistema.tornarTutor("13", "Prog2", -1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorMatriculaNaoCadastrada() {
		sistema.tornarTutor("2", "Prog2", 3);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTornarTutorDisciplinaRepetida() {
		sistema.tornarTutor("13", "Prog2", 3);
		sistema.tornarTutor("13", "Prog2", 3);

	}

	@Test
	public void testRecuperaTutor() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.tornarTutor("13", "Prog2", 4);
		assertEquals("13 - Son Goku - " + 5 + " - 40028922 - songoku@dbs.com", sistema.recuperaTutor("13"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testRecuperaTutorInvalido() {
		sistema.tornarTutor("18", "C2", 3);

	}

	@Test
	public void testListarTutores() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.cadastrarAluno("George", "11", 3, "33106000", "principegeorge@imperio.com");
		sistema.tornarTutor("13", "Prog2", 4);
		sistema.tornarTutor("11", "Prog2", 3);

		assertEquals("11 - George - " + 3 + " - 33106000 - principegeorge@imperio.com, 13 - Son Goku - " + 5
				+ " - 40028922 - songoku@dbs.com", sistema.listarTutores());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarHorarioEmailInvalido() {

		sistema.cadastrarHorario("", "15h", "seg");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarHorarioInvalido() {

		sistema.cadastrarHorario("songoku@dbs.com", "", "seg");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarHorarioDiaInvalido() {

		sistema.cadastrarHorario("songoku@dbs.com", "12h", "");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarHorarioTutorNCadastrado() {

		sistema.cadastrarHorario("narutokun@shipuden.com", "18h", "sex");

	}

	@Test
	public void testConsultaHorario() {

		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.tornarTutor("13", "Prog2", 5);
		sistema.cadastrarHorario("songoku@dbs.com", "12h", "seg");

		assertTrue(sistema.consultaHorario("songoku@dbs.com", "12h", "seg"));
	}

	@Test
	public void testConsultaHorarioFalse() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.tornarTutor("13", "Prog2", 5);
		sistema.cadastrarHorario("songoku@dbs.com", "15h", "seg");

		assertFalse(sistema.consultaHorario("songoku@dbs.com", "12h", "seg"));
	}

	@Test
	public void testConsultaHorarioTutorInvisivel() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.tornarTutor("13", "Prog2", 5);
		sistema.cadastrarHorario("songoku@dbs.com", "15h", "seg");

		assertFalse(sistema.consultaHorario("narutokun@shipuden.com", "18h", "sex"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarLocalDeAtendimentoEmailVazio() {
		sistema.cadastrarLocalDeAtendimento("", "patio");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarLocalDeAtendimentoLocalVazio() {
		sistema.cadastrarLocalDeAtendimento("songoku@dbs.com", "");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastrarLocalDeAtendimento() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.tornarTutor("13", "Prog2", 5);

		sistema.cadastrarLocalDeAtendimento("principegeorge@imperio.com", "Inglaterra");
	}

	@Test
	public void testConsultaLocal() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.tornarTutor("13", "Prog2", 5);
		sistema.cadastrarLocalDeAtendimento("songoku@dbs.com", "Namekusei");

		assertTrue(sistema.consultaLocal("songoku@dbs.com", "Namekusei"));

	}

	public void testConsultaLocalDeNovo() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema.tornarTutor("13", "Prog2", 5);
		sistema.cadastrarLocalDeAtendimento("songoku@dbs.com", "Namekusei");

		assertFalse(sistema.consultaLocal("songoku@dbs.com", "Mundo Vazio"));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testConsultaLocalEmailInvalido() {
		sistema.consultaLocal("", "Terra");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testConsultaLocalInvalido() {
		sistema.consultaLocal("songoku@dbs.com", "");

	}

}
