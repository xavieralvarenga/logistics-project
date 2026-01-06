package repository.entity;

public class Cliente {
    private String nombre;
    private String identificacion;

    //Validación de datos en el constructor
    public Cliente(String nombre, String identificacion) {
        if ((nombre == null || nombre.isBlank()) || (identificacion == null || identificacion.isBlank())) {
            throw new IllegalArgumentException("Nombre e identificación no pueden ser nulos o vacíos.");
        }
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
