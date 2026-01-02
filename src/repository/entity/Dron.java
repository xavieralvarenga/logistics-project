package repository.entity;

public class Dron extends Vehiculo{
    private double altitudMaxima;
    private double autonomiaMaxima;
    private boolean tieneCamara;

    //Constructor con atributos de la clase padre y de la clase hija
    public Dron(String id, String marca, String modelo, int capacidadMaxima, Estado estado, double altitudMaxima, double autonomiaMaxima, boolean tieneCamara) {
        super(id, marca, modelo, capacidadMaxima, estado);
        this.altitudMaxima = altitudMaxima;
        this.autonomiaMaxima = autonomiaMaxima;
        this.tieneCamara = tieneCamara;
    }
}
