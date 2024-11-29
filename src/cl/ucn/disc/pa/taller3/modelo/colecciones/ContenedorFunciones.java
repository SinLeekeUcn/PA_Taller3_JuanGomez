package cl.ucn.disc.pa.taller3.modelo.colecciones;
import cl.ucn.disc.pa.taller3.modelo.Funcion;

public class ContenedorFunciones extends Contenedor {

	public ContenedorFunciones(int cantMaxima) {
		super(cantMaxima);
	}

	//TODO: que retorne los ToString de cada funcion y solo les hago append.
	public int[] buscar(String tipoBusqueda, String referencia) {

		int[] posiciones = new int[this.cantMaxima];
		int aux = 0; // Índice auxiliar

		for (int i = 0; i < this.cantActual; i++) {
			Funcion funcionABuscar = (Funcion) obtener(i);

			if (funcionABuscar == null) {
				continue; // Si no hay función en esa posicion saltamos a la siguiente
			}

			// Comparación según el tipo de búsqueda
			if (tipoBusqueda.equalsIgnoreCase("codigo") && funcionABuscar.getCodigo() == Integer.parseInt(referencia)) {

				posiciones[aux++] = i; // Almacena posición y avanza el índice
			}

			if (tipoBusqueda.equalsIgnoreCase("nombre") && funcionABuscar.getNombre().equalsIgnoreCase(referencia)) {
				posiciones[aux++] = i;
			}

			if (tipoBusqueda.equalsIgnoreCase("horario") && funcionABuscar.getHorario().equals(referencia)) {
				posiciones[aux++] = i;
			}
		}

		// Copio el arreglo de posiciones con un tamaño igual a la cantidad de funciones encontradas.
		return java.util.Arrays.copyOf(posiciones, aux);
	}
}