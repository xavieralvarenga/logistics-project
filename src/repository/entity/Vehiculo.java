package repository.entity;

public abstract class Vehiculo {
    protected String id;
    protected String marca;
    protected String modelo;
    protected int capacidadMaxima;
    protected Estado estado;

    //Constructor con id
    protected Vehiculo(String id, String marca, String modelo, int capacidadMaxima, Estado estado) {
        this.id = id; //Puede ser el número de placa
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

}
