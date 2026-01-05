package repository.entity;

public class Camion extends Vehiculo{
    private int numeroEjes;
    private double volumenCarga;
    private TipoMotor tipoMotor;
    private double kilometraje;

    //Constructor con atributos de la clase padre y de la clase hija
    public Camion(String id, String marca, String modelo, int capacidadMaxima, Estado estado, int numeroEjes, double volumenCarga, TipoMotor tipoMotor, double kilometraje) {
        super(id, marca, modelo, capacidadMaxima, estado);
        setNumeroEjes(numeroEjes);
        setVolumenCarga(volumenCarga);
        this.tipoMotor = tipoMotor;
        setKilometraje(kilometraje);
    }

    @Override
    public String protocoloMantenimiento() {
        return "Protocolo de mantenimiento para Camión: Revisar motor cada 10,000 km, cambiar aceite cada 5,000 km, inspeccionar frenos y neumáticos cada 15,000 km.";
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public int getNumeroEjes() {
        return numeroEjes;
    }

    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    public double getVolumenCarga() {
        return volumenCarga;
    }

    //Métodos set con validaciones
    public void setKilometraje(double kilometraje) {
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser negativo.");
        }
        this.kilometraje = kilometraje;
    }

    //Métodos set con validaciones
    public void setNumeroEjes(int numeroEjes) {
        if (numeroEjes < 0) {
            throw new IllegalArgumentException("El número de ejes no puede ser negativo.");
        }
        this.numeroEjes = numeroEjes;
    }

    public void setTipoMotor(TipoMotor tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    //Métodos set con validaciones
    public void setVolumenCarga(double volumenCarga) {
        if (volumenCarga < 0) {
            throw new IllegalArgumentException("El volumen de carga no puede ser negativo.");
        }
        this.volumenCarga = volumenCarga;
    }
}
