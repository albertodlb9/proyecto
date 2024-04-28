
package proyecto_albertodelablanca;

import java.util.HashSet;

/**
 *
 * @author Alberto
 */
public class Gimnasio {
   
    private HashSet<Cliente> gimnasio;
    private String nombre;

    public Gimnasio() {
        this.gimnasio = new HashSet<Cliente>();
        this.nombre = "";
    }

    public HashSet<Cliente> getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(HashSet<Cliente> gimnasio) {
        this.gimnasio = gimnasio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
