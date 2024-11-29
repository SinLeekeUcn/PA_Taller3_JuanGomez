package cl.ucn.disc.pa.taller3.utilidades;

import ucn.StdIn;
import ucn.StdOut;

public class ValidadorEntrada {

    public static int validadorNumerico(String frase){

        String datoLeido = "";

        while (true){

            StdOut.println(frase);
            datoLeido = StdIn.readLine();


            if (esNumerico(datoLeido)){
                return Integer.parseInt(datoLeido);
            }
        }
    }

    public static boolean esNumerico(String dato){

        try{
            Integer.parseInt(dato);
            return true;
        }catch (NumberFormatException e){
            StdOut.println("El dato ingresado no es numerico.");
            return false;
        }
    }
}
