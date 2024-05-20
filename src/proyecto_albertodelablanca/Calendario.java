
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
public class Calendario {
    
    private final int NUMERO_DIAS = 7;
    private Dia[] dias;
    
    public Calendario(){
        dias = new Dia[NUMERO_DIAS];
        dias[0] = new Dia("Lunes");
        dias[1] = new Dia("Martes");
        dias[2] = new Dia("Miercoles");
        dias[3] = new Dia("Jueves");
        dias[4] = new Dia("Viernes");
        dias[5] = new Dia("Sabado");
        dias[6] = new Dia("Domingo");
    }

    public Dia[] getDias() {
        return dias;
    }

    public void setDias(Dia[] dias) {
        this.dias = dias;
    }
    
    
}
