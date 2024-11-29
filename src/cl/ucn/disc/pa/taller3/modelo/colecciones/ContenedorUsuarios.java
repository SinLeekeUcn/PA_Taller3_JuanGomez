package cl.ucn.disc.pa.taller3.modelo.colecciones;
import cl.ucn.disc.pa.taller3.modelo.Usuario;

public class ContenedorUsuarios extends Contenedor {

	public ContenedorUsuarios(int cantMaxima) {
		super(cantMaxima);
	}

	public Usuario obtenerUsuario(String rut) {

		for (int i = 0; i < this.cantActual; i++) {
			Usuario usuario =  (Usuario) listaElementos[i];

			if (usuario.getRut().equals(rut)) {
				return usuario;
			}
		}

		// Retorna null si el usuario no existe.
		return null;
	}
}