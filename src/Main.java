import repository.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Se implementa una lista para la flota de vehículos a través de la clase Vehículo usando ArrayList
        List<Vehiculo> flotaActiva = new ArrayList<>();

        //Instansciación de Camion
        Camion camion1 = new Camion("ABC123", "Volvo", "FH16", 20000, Estado.DISPONIBLE, 4, 60.0, TipoMotor.DIESEL, 150000.0);

        //Instansciación de Dron
        Dron dron1 = new Dron("DRN001", "DJI", "Phantom 4", 5, Estado.DISPONIBLE, 500.0, 30.0, true);

        //Agregar vehículos a la flota activa
        flotaActiva.add(camion1);
        flotaActiva.add(dron1);

        //Llamada al método para obtener el protocolo de mantenimiento de cada vehículo
        obtenerProtocoloMantenimiento(flotaActiva);
    }

    //Uso de pattern matching con switch para obtener el protocolo de mantenimiento de cada vehículo
    public static void obtenerProtocoloMantenimiento(List<Vehiculo> flotaActiva){
        for (Vehiculo v : flotaActiva) {
            String protocolo = switch (v) {
                case Camion c -> c.protocoloMantenimiento();
                case Dron d -> d.protocoloMantenimiento();
                default -> "Tipo de vehículo no reconocido para mantenimiento.";
            };
            System.out.println("Protocolo de mantenimiento para el vehículo con ID " + v.getId() + ": " + protocolo);
        }
    }



}
