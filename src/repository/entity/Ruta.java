package repository.entity;

//Creamos un record para la entidad Ruta ya que sus valores son inmutables
public record Ruta(OrigenRuta origen, String destino, double distanciaKm) {

    public Ruta{
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser un valor positivo.");
        }
    }
}
