package cl.ucn.disc.pa.taller3.modelo;

public class ObraDeTeatro extends Funcion{

	private String autor;
	private int cantidadDeActos;

	public ObraDeTeatro(int codigo, String nombre, int duracion, int precio, String horario, String autor, int cantidadDeActos) {
		super(codigo, nombre, duracion, precio, horario);
		this.autor = autor;
		this.cantidadDeActos = cantidadDeActos;
	}

	public String getAutor() {
		return autor;
	}

	public int getCantidadDeActos() {
		return cantidadDeActos;
	}
}
