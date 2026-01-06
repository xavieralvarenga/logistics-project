package repository.entity;

import utils.LogisticaConfig;

public class Envio {
    private String id;
    private String codigoEnvio;
    private Cliente cliente; //Relación de Asociación
    private Vehiculo vehiculo; //Relación de Asociación
    private double peso;
    private String distancia;
    private Ruta ruta; //Relación de Asociación

    public Envio(String id, String codigoEnvio, Cliente cliente, Vehiculo vehiculo, double peso, String distancia) {
        this.cliente = cliente;
        this.codigoEnvio = codigoEnvio;
        this.distancia = distancia;
        this.id = id;
        validarCapacidad(peso); //Validamos la capacidad del vehículo con el peso del envío
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getCodigoEnvio() {
        return codigoEnvio;
    }

    public String getDistancia() {
        return distancia;
    }

    public String getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    //Aplicamos reglas de negocio para el peso: Validar capacidad del vehículo < peso del envío
    private void validarCapacidad(double peso){
        if (peso > vehiculo.getCapacidadMaxima()) {
            throw new IllegalArgumentException("El peso del envío excede la capacidad máxima del vehículo seleccionado.");
        }
        this.peso = peso;
    }

    //Gracias a asociación con Ruta y Vehículo podemos calcular el costo del envío
    public double calcularCostoEnvio(double tarifaPorKm) {
        double tarifa = (vehiculo instanceof Camion) ? LogisticaConfig.COSTO_KM_CAMION : LogisticaConfig.COSTO_KM_DRON;
        return (ruta.distanciaKm() * tarifa); //Costo sin IVA
    }
}
