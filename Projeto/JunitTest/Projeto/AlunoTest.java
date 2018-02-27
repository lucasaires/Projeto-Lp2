package Projeto;

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
		public void testCadastraAlunoVazio(){
		
		Aluno ilegal = new Aluno("","",0,"","");
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testCadastraAlunoNull(){
		
		Aluno ilegal = new Aluno(null,null,0,null,null);
		
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
		assertEquals("13 - Son Goku - 5 - 40028922 - songoku@dbs.com",a.toString());
	}

}