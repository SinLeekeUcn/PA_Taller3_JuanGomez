package cl.ucn.disc.pa.taller3.infraestructura;

import cl.ucn.disc.pa.taller3.modelo.Funcion;
import cl.ucn.disc.pa.taller3.modelo.Usuario;

public interface ISistemaTeatro {

	boolean lecturaDeDocumentos();
	boolean iniciarSesion(String rut, String contrasenia);
	boolean registrarse(String rut, String contrasenia);
	void cerrarSesion();
	boolean comprarEntrada(int cantEntradas, int[] asientosComprados, String codigoFuncion);
	String verAsientos(String codigoFuncion);
	String verFuncionesDisponibles();
	String buscarFuncion(String opcionDeBusqueda, String referencia, String tipoDato);
	Usuario returnUsuario();
}
