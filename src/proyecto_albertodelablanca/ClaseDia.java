
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
public class ClaseDia extends Clase {
    
    private int inicioClase;
    private int finalClase;
    private String diaSemana;
    
    private int plazas;

    public ClaseDia(int inicioClase, int finalClase, String diaSemana, int plazas, int idClase, String nombre, String descripcion) {
        super(idClase, nombre, descripcion);
        this.inicioClase = inicioClase;
        this.finalClase = finalClase;
        this.diaSemana = diaSemana;
        
    }

    public int getInicioClase() {
        return inicioClase;
    }

    public void setInicioClase(int inicioClase) {
        this.inicioClase = inicioClase;
    }

    public int getFinalClase() {
        return finalClase;
    }

    public void setFinalClase(int finalClase) {
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
}
