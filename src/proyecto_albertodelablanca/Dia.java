
package proyecto_albertodelablanca;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public class Dia {
    
    private String nombre;
    private ArrayList<ClaseDia> clases;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public Dia(String nombre) {
        this.clases = new ArrayList<>();
        this.nombre = nombre;
    }

    public ArrayList<ClaseDia> getClases() {
        return clases;
    }

    public void setClases(ArrayList<ClaseDia> clases) {
        this.clases = clases;
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
    
    public void addClase(ClaseDia clase){
        int contador = 0;
        if(clase.getInicioClase().isAfter(horaApertura) && clase.getFinalClase().isBefore(horaCierre)){
            for(int i = 0; i < this.clases.size(); i++){
                ClaseDia claseDia = this.clases.get(i);
                if(clase.getFinalClase().isBefore(claseDia.getInicioClase()) && clase.getInicioClase().isAfter(claseDia.getFinalClase())){
                    contador++;
                }
            }
        }
        if(clases.size() == contador){
            clases.add(clase);
            System.out.println("La clase se añadio correctamente");
        } else{
            System.out.println("No se puede añadir la clase por incompatibilidad horaria");
        }
    }
}
