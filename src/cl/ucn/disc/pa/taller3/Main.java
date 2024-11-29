package cl.ucn.disc.pa.taller3;
import cl.ucn.disc.pa.taller3.infraestructura.ISistemaTeatro;
import cl.ucn.disc.pa.taller3.infraestructura.Vista;
import cl.ucn.disc.pa.taller3.servicios.SistemaTeatro;
import ucn.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ISistemaTeatro parteTrasera = new SistemaTeatro();
        Vista parteFrontal = new Vista(parteTrasera);
        parteFrontal.init();

    }
}