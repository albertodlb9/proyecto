
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
public class Clase {
    
    private int idClase;
    private String nombre;
    private String descripcion;

    public Clase(int idClase, String nombre, String descripcion) {
        this.idClase = idClase;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "idClase: " + this.idClase + " Nombre: " + this.nombre + " Descripcion: " + this.descripcion;
    } 
}
