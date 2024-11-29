package cl.ucn.disc.pa.taller3.modelo.colecciones;

public abstract class Contenedor {

	protected Elemento[] listaElementos;
	protected int cantMaxima;
	protected int cantActual;

	public Contenedor (int cantMaxima){
		this.cantMaxima = cantMaxima;
		this.listaElementos = new Elemento[cantMaxima];
		this.cantActual = 0;
	}

	public boolean agregar (Elemento elemento){

		if (this.cantActual == cantMaxima){
			return false;
		}

		this.listaElementos[this.cantActual] = elemento;
		this.cantActual++;
		return true;

	}

	public Elemento obtener(int posicion){
		return this.listaElementos[posicion];
	}

	public int getCantActual() {
		return cantActual;
	}

	public int getCantMaxima() {
		return cantMaxima;
	}
}
