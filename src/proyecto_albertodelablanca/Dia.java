
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
public class Dia {
    
    private String nombre;
    private int horaApertura;
    private int horaCierre;

    public Dia(String nombre) {
        this.nombre = nombre;
    }
    
    public Dia(String nombre, int horaApertura, int horaCierre){
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public int getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(int horaApertura) {
        this.horaApertura = horaApertura;
    }

    public int getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(int horaCierre) {
        this.horaCierre = horaCierre;
    }

  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
