package Projeto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {
	Aluno a;
	Sistema sistema;
	Tutor t;
	@Before
	public void testAluno() {
		a = new Aluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		sistema = new Sistema();
		
	}

	@Test
	public void tornarTutorTest() {
		sistema.cadastrarAluno("Son Goku", "13", 5, "40028922", "songoku@dbs.com");
		
		try {
			sistema.tornarTutor("13", "p2", 5);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Erro na definicao de papel: Ja eh tutor dessa disciplina");
		}
	}
	
	
	
	
	
	
	
	
	
	
}