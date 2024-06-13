
package proyecto_albertodelablanca;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public class ClaseDia extends Clase {
    
    private LocalTime inicioClase;
    private LocalTime finalClase;
    private String diaSemana;
    private int plazas;

    public ClaseDia(LocalTime inicioClase, LocalTime finalClase, String diaSemana, int plazas, int idClase, String nombre, String descripcion) {
        super(idClase, nombre, descripcion);
        this.inicioClase = inicioClase;
        this.finalClase = finalClase;
        this.diaSemana = diaSemana;
        this.plazas = plazas;
    }

    public LocalTime getInicioClase() {
        return inicioClase;
    }

    public void setInicioClase(LocalTime inicioClase) {
        this.inicioClase = inicioClase;
    }

    public LocalTime getFinalClase() {
        return finalClase;
    }

    public void setFinalClase(LocalTime finalClase) {
        this.finalClase = finalClase;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }  

    @Override
    public String toString() {
        return "idClase: " + super.getIdClase() + " nombre: " + super.getNombre() + " Hora de inicio: " + this.getInicioClase() + " Hora de finalizacion: " + this.getFinalClase() + " Plazas: " + this.getPlazas();
    }
    
    
}
