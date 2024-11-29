package cl.ucn.disc.pa.taller3.modelo;

public class Documental extends Funcion{

	private String tema;
	private int anioProduccion;

	public Documental(int codigo, String nombre, int duracion, int precio, String horario, String tema, int anioProduccion) {
		super(codigo, nombre, duracion, precio, horario);
		this.tema = tema;
		this.anioProduccion = anioProduccion;
	}

	public String getTema() {
		return tema;
	}

	public int getAnioProduccion() {
		return anioProduccion;
	}


}
