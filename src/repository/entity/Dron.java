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

    public double getAltitudMaxima() {
        return altitudMaxima;
    }

    public double getAutonomiaMaxima() {
        return autonomiaMaxima;
    }

    public boolean isTieneCamara() {
        return tieneCamara;
    }

    public void setAltitudMaxima(double altitudMaxima) {
        // Validación: La altitud no puede ser negativa
        if (altitudMaxima >= 0) {
            this.altitudMaxima = altitudMaxima;
        } else {
            throw new IllegalArgumentException("La altitud máxima no puede ser negativa.");
        }
    }

    public void setAutonomiaMaxima(double autonomiaMaxima) {
        // Validación: La autonomía (tiempo o distancia) debe ser mayor a cero
        if (autonomiaMaxima > 0) {
            this.autonomiaMaxima = autonomiaMaxima;
        } else {
            throw new IllegalArgumentException("La autonomía debe ser un valor positivo.");
        }
    }

    public void setTieneCamara(boolean tieneCamara) {
        this.tieneCamara = tieneCamara;
    }
}
