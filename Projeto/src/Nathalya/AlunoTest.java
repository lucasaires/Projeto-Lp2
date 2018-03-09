package Nathalya;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlunoTest {

	Aluno a;

	@Before
	public void testAluno() {
		a = new Aluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
	}

	@Test
	public void testGetMatricula() {
		assertEquals("13", a.getMatricula());
	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoVazio1() {

		Aluno ilegal = new Aluno("", "000", 11, "9987", "ee@ff");

	}

	@Test(expected = IllegalAccessException.class)
	public void testCadastraAlunoVazio2() {

		Aluno ilegal = new Aluno("Larissa", "", 11, "9987", "ee@ff");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoVazio3() {

		Aluno ilegal = new Aluno("Nathalya", "000", 0, "9987", "ee@ff");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoVazio4() {

		Aluno ilegal = new Aluno("Lucas", "000", 11, "", "ee@ff");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoVazio5() {

		Aluno ilegal = new Aluno("Yally", "000", 11, "9987", "");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull() {

		Aluno ilegal = new Aluno(null, "11111", 22, "3321", "aa@bb");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull1() {

		Aluno ilegal = new Aluno("Yally", null, 33, "6654", "bb@cc");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull2() {

		Aluno ilegal = new Aluno("Yally", "2222", 0, "6654", "d@cc");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull3() {

		Aluno ilegal = new Aluno("Yally", "3333", 55, null, "db@cc");

	}

	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull4() {

		Aluno ilegal = new Aluno("Yally", "888", 98, "6454", null);

	}

	@Test
	public void testGetNome() {
		assertEquals("Son Goku", a.getNome());
	}

	@Test
	public void testGetNota() {
		assertEquals(5, a.getNota());
	}

	@Test
	public void testSetNota() {
		a.setNota(15);

		assertEquals(15, a.getNota());
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
		Aluno b = new Aluno("Vegeta", "13", 69, "33106000", "vegeta@dbs.com");
		assertTrue(a.getMatricula().equals(b.getMatricula()));
	}

	@Test
	public void testToString() {
		assertEquals("13 - Son Goku - 5 - 40028922 - songoku@dbs.com", a.toString());
	}

}