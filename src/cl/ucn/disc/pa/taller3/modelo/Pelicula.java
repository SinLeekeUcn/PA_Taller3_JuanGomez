package cl.ucn.disc.pa.taller3.modelo;

public class Pelicula extends Funcion {

	private String genero;
	private boolean nominadaAlOscar;

	public Pelicula(int codigo, String nombre, int duracion, int precio, String horario, String genero, boolean nominadaAlOscar) {
		super(codigo, nombre, duracion, precio, horario);
		this.genero = genero;
		this.nominadaAlOscar = nominadaAlOscar;
	}

	public String getGenero() {
		return genero;
	}

	public boolean isNominadaAlOscar() {
		return nominadaAlOscar;
	}
}
