package utils;

public class LogisticaConfig {

    public static final double IVA = 0.13; // Impuesto al Valor Agregado del 13%

    //Tarifas por tipo de transporte por km
    public static final double COSTO_KM_CAMION = 3.8; // Costo por km para camiones en $(dólares)
    public static final double COSTO_KM_DRON = 1.9;   //Costo por km para drones en $(dólares)

    //Establecemos un constructor privador para evitar la instanciación de la clase
    private LogisticaConfig() {} //Patrón "Utility Class"
}
