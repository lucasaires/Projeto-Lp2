package projeto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projeto.Entidades.Tutor;

public class TutorTest {

	Tutor t;

	@Before
	public void setup() {
		t = new Tutor("Son Goku", "13", 5, "40028922", "songoku@dbs.com", "Prog2", 3, 1);
	}

	@Test(expected = NullPointerException.class)
	public void testTutorDisciplinaVazia() {
		t = new Tutor("Larissa", "22", 2, "9985", "aa@bb", "", 3, 1);

	}

	@Test(expected = NullPointerException.class)
	public void testTutorProficienciaInvalida() {
		t = new Tutor("Larissa", "22", 2, "9985", "aa@bb", "C2", -1, 1);
	}

	@Test(expected = NullPointerException.class)
	public void testTutorDisciplinaNull() {
		t = new Tutor("Larissa", "22", 2, "9985", "aa@bb", null, 3, 1);
	}

	@Test
	public void testGetMatricula() {
		assertEquals("13", t.getMatricula());
	}

	@Test
	public void testToString() {
		assertEquals("13 - Son Goku - 5 - 40028922 - songoku@dbs.com", t.toString());
	}

	@Test
	public void testEqualsObject() {

		Tutor s = new Tutor("Son Goku", "13", 5, "40028922", "songoku@dbs.com", "Prog2", 3, 1);

		assertTrue(s.equals(t));
	}

	@Test
	public void testVerificaDisciplinas() {
		String Disciplina = "Prog2";
		String Disciplina2 = "C2";

		assertEquals("Prog2", (Disciplina));
		assertEquals("C2", (Disciplina2));
	}

	@Test
	public void testGetProficiencia() {
		assertEquals(3, t.getProficiencia());
	}

	@Test
	public void testSetProficiencia() {
		t.setProficiencia(6);

		assertEquals(6, t.getProficiencia());
	}

	@Test
	public void testGetDinheiro() {
		assertEquals(0, t.getDinheiro());
	}

	@Test
	public void testSetDinheiro() {
		t.setDinheiro(50);
		assertEquals(50, t.getDinheiro());
	}

	@Test
	public void consultaLocalTest() {
		t.cadastrarLocal("lcc");
		assertTrue(t.consultaLocal("lcc"));

	}

	@Test
	public void consultaHorarioTest() {
		t.cadastraHorario("15:00", "seg");
		assertTrue(t.consultaHorario("15:00", "seg"));
	}

	@Test
	public void toStringTest() {
		assertEquals("13 - Son Goku - 5 - 40028922 - songoku@dbs.com", t.toString());
	}

}