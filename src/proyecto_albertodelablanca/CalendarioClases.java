
package proyecto_albertodelablanca;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Alberto
 */

//Quiero hacer un calendario para guardar las clases con horarios y clientes
// pero todavia tengo que pensar mejor como hacerlo
public class CalendarioClases {
    
    private ArrayList<HashMap<LocalTime,ArrayList<Clase>>> calendario[];
    private String mes;

    public CalendarioClases() {
        LocalDate fechaActual = LocalDate.now();
        int dias = fechaActual.lengthOfMonth();
        int diaMes = fechaActual.getDayOfMonth();
        int diaSemana = fechaActual.getDayOfWeek().getValue();
        String diasSemana[] = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        
       
        
        this.mes = fechaActual.getMonth().name();
       ;
        
        
    }

 
    
    
    
}
