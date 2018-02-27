package Projeto;

/**
 * 
 * @author Larissa Gabriela Amorim da Costa, Lucas Gomes Aires , Nathalya ,
 *         Yally de Lima Galdino
 *
 */
public class Horario {

	private String email;
	private String horario;
	private String dia;

	public Horario(String email, String horario, String dia) {
		this.email = email;
		this.horario = horario;
		this.dia = dia;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @return
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * 
	 * @return
	 */
	public String getDia() {
		return dia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horario other = (Horario) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		return true;
	}
}
