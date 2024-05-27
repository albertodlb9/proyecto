
package proyecto_albertodelablanca;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public class Dia {
    
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public Dia(String nombre) {
        this.nombre = nombre;
    }
    
    public Dia(String nombre, LocalTime horaApertura, LocalTime horaCierre){
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
