package cl.ucn.disc.pa.taller3.modelo;
import cl.ucn.disc.pa.taller3.modelo.colecciones.Elemento;

public class Usuario implements Elemento {

	private String rut;
	private String contrasenia;
	private boolean membresia;

	public Usuario(String rut, String contrasenia) {
		this.rut = rut;
		this.contrasenia = contrasenia;
		this.membresia = false;
	}

	public String getRut() {
		return rut;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public boolean membresia() {
		return membresia;
	}

	public void setMembresia(boolean membresia) {
		this.membresia = membresia;
	}
}
