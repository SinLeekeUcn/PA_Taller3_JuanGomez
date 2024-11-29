package cl.ucn.disc.pa.taller3.servicios;
import cl.ucn.disc.pa.taller3.infraestructura.ISistemaTeatro;
import cl.ucn.disc.pa.taller3.modelo.*;
import cl.ucn.disc.pa.taller3.modelo.colecciones.*;
import ucn.ArchivoEntrada;
import ucn.Registro;
import java.io.IOException;


	public class SistemaTeatro implements ISistemaTeatro {

		private ContenedorFunciones listaFunciones;
		private ContenedorUsuarios listaUsuarios;
		private Usuario usuarioLogueado;
		private final String NOMBRE_ARCHIVO_TEXTO;

		public SistemaTeatro() {

			listaFunciones = new ContenedorFunciones(500);
			listaUsuarios = new ContenedorUsuarios(100);
			this.usuarioLogueado = null;
			NOMBRE_ARCHIVO_TEXTO = "Funciones.txt";


			Usuario usuarioauxiliar = new Usuario("1","1");
			listaUsuarios.agregar(usuarioauxiliar);
		}

		@Override
		public boolean lecturaDeDocumentos() {

			Funcion nuevaFuncion = null;

			try{

				ArchivoEntrada archivo = new ArchivoEntrada(NOMBRE_ARCHIVO_TEXTO);

				while (!archivo.isEndFile()){

					Registro linea = archivo.getRegistro();

					int codigo = linea.getInt();
					String nombre = linea.getString();
					int duracion = linea.getInt();
					int precio = linea.getInt();
					String horario = linea.getString();

					if (codigo >= 100 && codigo < 200){

						String genero = linea.getString();
						String nominada = linea.getString();
						boolean nominadaAlOscar;
						if (nominada.equalsIgnoreCase("si")){
							nominadaAlOscar = true;
						} else {
							nominadaAlOscar = false;
						}

						nuevaFuncion = new Pelicula(codigo,nombre,duracion,precio,horario,genero,nominadaAlOscar);
					}

					if (codigo >= 200 && codigo < 300){

						String autor = linea.getString();
						int cantidadDeActos = linea.getInt();

						nuevaFuncion = new ObraDeTeatro(codigo,nombre,duracion,precio,horario,autor,cantidadDeActos);
					}

					if (codigo >= 300 && codigo < 400){

						String tema = linea.getString();
						int anioProduccion = linea.getInt();

						nuevaFuncion = new Documental(codigo,nombre,duracion,precio,horario,tema,anioProduccion);
					}

					if (!this.listaFunciones.agregar(nuevaFuncion)) {
						break;
					}

				}

				archivo.close();
				return true;

			}catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public void cerrarSesion() {
			this.usuarioLogueado = null;
		}

		@Override
		public boolean iniciarSesion(String rut, String contrasenia) {

			if (this.usuarioLogueado != null) {
				return false;
			}

			// Paso 2: Busco el usuario en la lista.
			Usuario usuarioBuscado = this.listaUsuarios.obtenerUsuario(rut);

			// Paso 3: Si el usuario no existe, no inicia sesión.
			if (usuarioBuscado == null) {
				return false;
			}

			// Paso 4: Si la contraseña no coincide, no inicia sesion.
			if (!usuarioBuscado.getContrasenia().equals(contrasenia)) {
				return false;
			}

			// Paso 5: Inicio sesión.
			this.usuarioLogueado = usuarioBuscado;
			return true;
		}

		@Override
		public boolean registrarse(String rut, String contrasenia) {

			Usuario nuevoUsuario;

			if (this.usuarioLogueado != null) {
				return false;
			}

			if (this.listaUsuarios.obtenerUsuario(rut) != null) {
				return false;
			}

			nuevoUsuario = new Usuario(rut, contrasenia);
			return this.listaUsuarios.agregar(nuevoUsuario);
		}

		@Override
		public boolean comprarEntrada(int cantEntradas, int[] asientosComprados, String codigoFuncion) {

			int[] auxFuncion = listaFunciones.buscar("codigo",codigoFuncion);

			Funcion funcion = (Funcion) listaFunciones.obtener(auxFuncion[0]);



			return false;
		}

		@Override
		public String verFuncionesDisponibles() {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < listaFunciones.getCantActual(); i++){

				Funcion aux = (Funcion) listaFunciones.obtener(i);

				if (aux.isDisponible()){
					sb.append(aux.toString());
				}
			}

			if (sb.length() > 0) {
				return sb.toString();
			} else {
				return "No hay funciones disponibles.";
			}
		}

		public String buscarFuncion(String opcionDeBusqueda, String referencia, String tipoDato) {
			int[] aux = listaFunciones.buscar(opcionDeBusqueda, referencia);
			StringBuilder sb = new StringBuilder();

			//Si el arreglo esta vacio, significa que no encontro ninguna funcion entonces retornamos un mensaje de error.
			if (aux == null || aux.length == 0) {
				return "No hay funciones disponibles.";
			}

			for (int auxPos : aux) {
				// Obtenemos la función si el índice es válido
				Funcion funcion = (Funcion) listaFunciones.obtener(auxPos);

				if (funcion != null) {
					// Verificamos el tipo de dato solicitado y lo añadimos al StringBuilder
					if (tipoDato.equalsIgnoreCase("nombre")) {
						sb.append(funcion.getNombre()).append("\n");
					} else if (tipoDato.equalsIgnoreCase("tostring")) {
						sb.append(funcion.toString());
					}
				}
			}

			// Si se generaron datos en el StringBuilder, los devolvemos
			if (sb.length() > 0) {
				return sb.toString();
			}

			return "No hay funciones disponibles.";
		}

		@Override
		public Usuario returnUsuario() {
			return this.usuarioLogueado;
		}

		@Override
		public String verAsientos(String codigoFuncion) {
			int funciones[] = listaFunciones.buscar("codigo", codigoFuncion);

			if (funciones.length == 0) {
				return "Funcion no encontrada.";
			}

			Funcion funcion = (Funcion) listaFunciones.obtener(funciones[0]);
			if (funcion == null) {
				return "Funcion no disponible.";
			}

			StringBuilder sb = new StringBuilder();
			String[] asientos = funcion.getAsientos();

			if (!funcion.isDisponible() || asientos == null || asientos.length == 0) {
				return "Funcion no disponible o sin asientos.";
			}

			int asientosDisponibles = Math.min(funcion.getAsientosDisponibles(), asientos.length);

			for (int i = 0; i < asientosDisponibles; i++) {
				sb.append("[").append(asientos[i]).append("] ");
				if ((i + 1) % 5 == 0) { // Cada 5 asientos, agrega un salto de línea
					sb.append("\n");
				}
			}

			return sb.toString();
		}
	}