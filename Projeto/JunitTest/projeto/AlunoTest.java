package projeto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projeto.Entidades.Aluno;

public class AlunoTest {

	Aluno a;

	@Before
	public void testAluno() {
		a = new Aluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com", 1);
	}

	@Test
	public void testGetMatricula() {
		assertEquals("13", a.getMatricula());
	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoVazio1() {

		a = new Aluno("", "000", 11, "9987", "ee@ff", 2);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraAlunoVazio2() {

		a = new Aluno("Yally", "000", 11, "9987", "", 6);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraAlunoEmailInvalido() {

		a = new Aluno("Nath", "000", 11, "9987", "@hotmail.com", 6);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testCadastraAlunoEmailInvalido1() {

		a = new Aluno("Nath", "000", 11, "9987", "nath@", 6);

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull1() {

		a = new Aluno(null, "11111", 22, "3321", "aa@bb", 7);

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull2() {

		a = new Aluno("Yally", "888", 98, "6454", null, 11);

	}

	@Test
	public void testGetNome() {
		assertEquals("Son Goku", a.getNome());
	}

	@Test
	public void testGetCodigoCurso() {
		assertEquals(5, a.getCodigoCurso());
	}

	@Test
	public void testGetEmail() {
		assertEquals("songoku@dbs.com", a.getEmail());
	}

	@Test
	public void testGetTelefone() {
		assertEquals("40028922", a.getTelefone());
	}

	@Test
	public void testEqualsObject() {
		Aluno b = new Aluno("Vegeta", "13", 69, "33106000", "vegeta@dbs.com", 1);
		assertTrue(a.getMatricula().equals(b.getMatricula()));
	}

	@Test
	public void testToString() {
		assertEquals("13 - Son Goku - 5 - 40028922 - songoku@dbs.com", a.toString());
	}

	@Test
	public void getPosicaoCadastroTest() {
		assertEquals(1, a.getPosicaoCadastro());

	}

}