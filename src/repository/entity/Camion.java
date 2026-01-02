package repository.entity;

public class Camion extends Vehiculo{
    private int numeroEjes;
    private double volumenCarga;
    private TipoMotor tipoMotor;
    private double kilometraje;

    //Constructor con atributos de la clase padre y de la clase hija
    public Camion(String id, String marca, String modelo, int capacidadMaxima, Estado estado, int numeroEjes, double volumenCarga, TipoMotor tipoMotor, double kilometraje) {
        super(id, marca, modelo, capacidadMaxima, estado);
        this.numeroEjes = numeroEjes;
        this.volumenCarga = volumenCarga;
        this.tipoMotor = tipoMotor;
        this.kilometraje = kilometraje;
    }
}
