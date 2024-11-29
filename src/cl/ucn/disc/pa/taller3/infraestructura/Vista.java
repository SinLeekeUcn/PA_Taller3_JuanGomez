package cl.ucn.disc.pa.taller3.infraestructura;
import cl.ucn.disc.pa.taller3.utilidades.ValidadorEntrada;
import ucn.StdIn;
import ucn.StdOut;

public class Vista {

	ISistemaTeatro sistema;

	public Vista(ISistemaTeatro sistema) {
		this.sistema = sistema;
	}

	public void init(){

		if (!sistema.lecturaDeDocumentos()){
			StdOut.println("No se ha podido leer el archivo, cerrando programa...");
		}

		StdOut.println("Bienvenido Usuario!!!");

		while (true){

			int opcion;

			StdOut.println("""
				******** Sistema Teatro ********
				[1] Registrarse
				[2] Iniciar Sesión
				[3] Salir""");

			opcion = ValidadorEntrada.validadorNumerico("Ingrese su opcion:");

			if (opcion == 1){
				if (registrarse()){
					StdOut.println("Registro realizado exitosamente.");
					continue;
				}
				StdOut.println("El usuario ya existe en el sistema o sistema lleno.");
				continue;
			}

			if (opcion == 2){
				if (!iniciarSesion()) {
					StdOut.println("Datos inválidos, inténtelo nuevamente.");
					continue;
				}
				StdOut.println("Sesión iniciada con éxito.");
				menuPrincipal();
				continue;
			}

			if (opcion == 3){
				StdOut.println("Adios usuario!!!");
				break;
			}

			StdOut.println("La opcion ingresada no esta dentro del rango de opciones, intentelo nuevamente.");

		}
	}

	private void menuPrincipal() {
		while (sistema.returnUsuario() != null){

			int opcion;

			StdOut.println("""
				**********************
				*	Menu Principal   *
				* Teatro Antofagasta *
				**********************
				[1] Comprar entrada
				[2] Ver funciones
				[3] Buscar una funcion
				[4] Cerrar Sesion""");

			opcion = ValidadorEntrada.validadorNumerico("Ingrese su opcion:");

			if (opcion == 1){
				opcion1();
				continue;
			}

			if (opcion == 2){
				opcion2();
				continue;
			}

			if (opcion == 3){
				opcion3();
				continue;
			}

			if (opcion == 4){
				sistema.cerrarSesion();
				continue;
			}

			StdOut.println("La opcion ingresada no esta dentro del rango de opciones, intentelo nuevamente.");

		}
	}

	private void opcion1(){

		while (!sistema.returnUsuario().membresia()){

			int opcion;

			StdOut.println("""
				**********************
				¿Desea ser miembro del
				ClubTeatroAntofagasta?
				**********************
				[1] Si
				[2] No""");

			opcion = ValidadorEntrada.validadorNumerico("Ingrese su opcion:");

			if (opcion == 1){
				this.sistema.returnUsuario().setMembresia(true);
				continue;
			}

			if (opcion == 2){
				break;
			}

			StdOut.println("La opcion ingresada no esta dentro del rango de opciones, intentelo nuevamente.");
		}

		StdOut.println(sistema.verFuncionesDisponibles());

		int codigoFuncion = ValidadorEntrada.validadorNumerico("Ingrese el codigo de la Funcion que desea comprar boletos:");
		StdOut.println("******************************");
		StdOut.println("Funcion: " + sistema.buscarFuncion("codigo",String.valueOf(codigoFuncion),"nombre"));
		StdOut.println(sistema.verAsientos(String.valueOf(codigoFuncion)));
		int numeroAsientos = ValidadorEntrada.validadorNumerico("Ingrese el numero de boletos que desea comprar:");

	}

	private void opcion2() {
		StdOut.println(sistema.verFuncionesDisponibles());
	}

	private void opcion3(){

		while (true){

			int opcion;

			StdOut.println("""
				**********************
				 OPCIONES DE BUSQUEDA
				**********************
				[1] Codigo
				[2] Nombre
				[3] Horario
				[4] Volver""");

			opcion = ValidadorEntrada.validadorNumerico("Ingrese su opcion:");

			if (opcion == 1){

				int codigo = ValidadorEntrada.validadorNumerico("Ingrese el codigo de la funcion a buscar:");

				StdOut.println(sistema.buscarFuncion("codigo", String.valueOf(codigo),"tostring"));
				continue;
			}

			if (opcion == 2){

				StdOut.println("Ingrese el nombre de la funcion a buscar:");
				String nombre = StdIn.readString();
				StdOut.println(sistema.buscarFuncion("nombre", nombre,"tostring"));
				continue;
			}

			if (opcion == 3){
				StdOut.println("Ingrese el horario de las funciones a buscar:");
				String horario = StdIn.readString();
				StdOut.print(sistema.buscarFuncion("horario", horario,"tostring"));
				continue;
			}

			if (opcion == 4){
				break;
			}

			StdOut.println("La opcion ingresada no esta dentro del rango de opciones, intentelo nuevamente.");

		}
	}

	private boolean iniciarSesion(){
		StdOut.println("***** Iniciar sesion *****");
		StdOut.println("Ingrese su rut:");
		String rut = StdIn.readString();
		StdOut.println("Ingrese su contraseña:");
		String contrasenia = StdIn.readString();

		return sistema.iniciarSesion(rut, contrasenia);
	}

	private boolean registrarse(){
		StdOut.println("***** Registro *****");
		StdOut.println("Ingrese su rut:");
		String rut = StdIn.readString();
		StdOut.println("Ingrese su contraseña:");
		String contrasenia = StdIn.readString();

		return sistema.registrarse(rut, contrasenia);
	}
}
