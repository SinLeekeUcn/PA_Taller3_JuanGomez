package cl.ucn.disc.pa.taller3.modelo;
import cl.ucn.disc.pa.taller3.modelo.colecciones.Elemento;

import javax.management.StringValueExp;

public class Funcion implements Elemento {

	protected int codigo;
	protected String nombre;
	protected int duracion;
	protected int precio;
	protected String horario;
	protected String[] asientos;
	protected int asientosDisponibles;
	protected boolean disponible;

	public Funcion(int codigo, String nombre, int duracion, int precio, String horario) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.precio = precio;
		this.horario = horario;
		this.asientosDisponibles = 25;
		this.disponible = true;

		this.asientos = new String[25];
		for (int i = 0; i < asientos.length; i++) {
			asientos[i] = String.valueOf(i+1);
		}
	}

	@Override
	public String toString() {

		String tipoFuncion = "";

		if (this instanceof Documental) {
			tipoFuncion = "Documental";
		}

		if (this instanceof ObraDeTeatro) {
			tipoFuncion = "Obra de Teatro";
		}

		if (this instanceof Pelicula) {
			tipoFuncion = "Película";
		}

		return "****** " + tipoFuncion + " ******" +"\n"+
			"Codigo: " + this.codigo + "\n" +
			"Nombre: " + this.nombre + "\n" +
			"Precio: " + this.precio + "\n" +
			"Horario: " + this.horario + "\n" +
			"Asientos Disponibles: " + this.asientosDisponibles  + "\n";
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public int getPrecio() {
		return precio;
	}

	public String getHorario() {
		return horario;
	}

	public String[] getAsientos() {
		return asientos;
	}

	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public boolean isDisponible() {
		if (this.asientosDisponibles == 0) {
			return false;
		}

		return true;
	}
}