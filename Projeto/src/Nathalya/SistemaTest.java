package Nathalya;

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
	this.avaliacao = Avaliacao.APRENDIZ.getAvaliacao();
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

	@Test
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

	// us4

	@Test
	public void pedirAjudaPresencialTest() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Luana", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);
		sistema.cadastrarHorario("larissa@ccc.com", "15:00", "seg");
		sistema.cadastrarLocalDeAtendimento("larissa@ccc.com", "LCC3");

		assertEquals(1, sistema.pedirAjudaPresencial("116215555", "Prog2", "15:00", "seg", "LCC3"));
	}

	@Test
	public void pedirAjudaPresencialTest1() {
		sistema.cadastrarAluno("Lucas", "116210878", 5, "40028922", "lucas@ccc.com");
		sistema.cadastrarAluno("Yally", "11621255", 5, "885645", "yally@ccc.com");
		sistema.tornarTutor("116210878", "cal", 5);
		sistema.cadastrarHorario("lucas@ccc.com", "21:00", "ter");
		sistema.cadastrarLocalDeAtendimento("lucas@ccc.com", "lcc");

		assertEquals(1, sistema.pedirAjudaPresencial("11621255", "cal", "21:00", "ter", "lcc"));

	}

	@Test
	public void pedirAjudaOnlineTest() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Geo", "116210498", 6, "3321", "geo@ccc.com");
		sistema.cadastrarAluno("Luana", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "IC", 5);
		sistema.tornarTutor("116210493", "PROG", 4);
		sistema.tornarTutor("116210498", "PROG", 5);

		assertEquals(1, sistema.pedirAjudaOnline("11621555", "IC"));
		assertEquals(2, sistema.pedirAjudaOnline("11621555", "PROG"));
	}

	@Test
	public void getInfoAjudaTest() {
		sistema.cadastrarAluno("Lucas", "116210878", 5, "40028922", "lucas@ccc.com");
		sistema.cadastrarAluno("Yally", "11621255", 5, "885645", "yally@ccc.com");
		sistema.tornarTutor("116210878", "cal", 5);
		sistema.cadastrarHorario("lucas@ccc.com", "21:00", "ter");
		sistema.cadastrarLocalDeAtendimento("lucas@ccc.com", "lcc");
		sistema.pedirAjudaPresencial("11621255", "cal", "21:00", "ter", "lcc");

		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.tornarTutor("116210493", "prog", 5);
		sistema.pedirAjudaOnline("11621255", "prog");

		assertEquals("21:00", sistema.getInfoAjuda(1, "horario"));
		assertEquals("prog", sistema.getInfoAjuda(2, "disciplina"));
		assertEquals("ter", sistema.getInfoAjuda(1, "dia"));
		assertEquals("lcc", sistema.getInfoAjuda(1, "localInteresse"));
	}

	// testar
	// aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
	// o erro
	@Test
	public void pegarTutorTest() {
		sistema.cadastrarAluno("Lucas", "116210878", 5, "40028922", "lucas@ccc.com");
		sistema.tornarTutor("116210878", "cal", 5);
		sistema.cadastrarHorario("lucas@ccc.com", "21:00", "ter");
		sistema.cadastrarLocalDeAtendimento("lucas@ccc.com", "lcc");

		sistema.cadastrarAluno("Nathalya", "11621244", 5, "885645", "nathalya@ccc.com");
		sistema.tornarTutor("11621244", "prog", 5);
		sistema.cadastrarHorario("nathalya@ccc.com", "21:00", "quar");
		sistema.cadastrarLocalDeAtendimento("nathalya@ccc.com", "lcc1");

		// assertEquals("Tutor - 116210878, "
		// + "disciplina - cal", sistema.pegarTutor(1));

	}

	@Test(expected = IllegalArgumentException.class)
	public void pedirAjudaPresencialTest2() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Luana", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);
		sistema.cadastrarHorario("larissa@ccc.com", "15:00", "seg");
		sistema.cadastrarLocalDeAtendimento("larissa@ccc.com", "LCC3");

		sistema.pedirAjudaPresencial("", "Prog2", "15:00", "seg", "LCC3");

	}

	@Test(expected = IllegalArgumentException.class)
	public void pedirAjudaPresencialTest3() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Luana", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);
		sistema.cadastrarHorario("larissa@ccc.com", "15:00", "seg");
		sistema.cadastrarLocalDeAtendimento("larissa@ccc.com", "LCC3");

		sistema.pedirAjudaPresencial("116215555", "  ", "15:00", "seg", "LCC3");

	}

	@Test(expected = IllegalArgumentException.class)
	public void pedirAjudaPresencialTest4() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Luana", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);
		sistema.cadastrarHorario("larissa@ccc.com", "15:00", "seg");
		sistema.cadastrarLocalDeAtendimento("larissa@ccc.com", "LCC3");

		sistema.pedirAjudaPresencial("11621555", "Prog2", "                ", "seg", "LCC3");

	}

	@Test(expected = IllegalArgumentException.class)
	public void pedirAjudaPresencialTest5() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Luana", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);
		sistema.cadastrarHorario("larissa@ccc.com", "15:00", "seg");
		sistema.cadastrarLocalDeAtendimento("larissa@ccc.com", "LCC3");

		sistema.pedirAjudaPresencial("116215555", "Prog2", "15:00", "  ", "LCC3");

	}

	@Test(expected = IllegalArgumentException.class)
	public void pedirAjudaPresencialTest6() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Luana", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);
		sistema.cadastrarHorario("larissa@ccc.com", "15:00", "seg");
		sistema.cadastrarLocalDeAtendimento("larissa@ccc.com", "LCC3");

		sistema.pedirAjudaPresencial("116215555", "Prog2", "15:00", "seg", "");

	}

	@Test(expected = IllegalArgumentException.class)
	public void pedirAjudaOnlineTest1() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Nathalya", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);

		sistema.pedirAjudaOnline("", "Prog2");

	}

	@Test(expected = IllegalArgumentException.class)
	public void pedirAjudaOnlineTest2() {
		sistema.cadastrarAluno("Larissa", "116210493", 5, "40028922", "larissa@ccc.com");
		sistema.cadastrarAluno("Nathalya", "116215555", 5, "885645", "luana@ccc.com");
		sistema.tornarTutor("116210493", "Prog2", 5);

		sistema.pedirAjudaOnline("116215555", "          ");

	}

	@Test(expected = IllegalArgumentException.class)
	public void getInfoAjudaTest1() {
		sistema.getInfoAjuda(-1, "disciplina");

	}

	@Test(expected = IllegalArgumentException.class)
	public void getInfoAjudaTest2() {
		sistema.getInfoAjuda(11000, "disciplina");

	}

	@Test(expected = IllegalArgumentException.class)
	public void getInfoAjudaTest3() {
		sistema.getInfoAjuda(1, "  ");

	}

	@Test(expected = IllegalArgumentException.class)
	public void getInfoAjudaTest4() {
		sistema.getInfoAjuda(1, "documentacao");

	}

	@Test(expected = IllegalArgumentException.class)
	public void pegarTutorTest1() {
		sistema.pegarTutor(-1);

	}

	@Test(expected = IllegalArgumentException.class)
	public void pegarTutorTest2() {
		sistema.pegarTutor(100);

	}
}
