
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiaDAO {
    
    private static Connection conexion;
    private static ClaseDAO claseDAO; 
    private static CalendarioDAO calendarioDAO;
    
    public DiaDAO(Connection conexion){
        this.conexion = conexion;
        this.claseDAO = new ClaseDAO(conexion); 
        this.calendarioDAO = new CalendarioDAO(conexion);
    }
    
    public void añadirDia(Dia dia) throws SQLException{
        String sql = "INSERT INTO calendario (nombre,horarioApertura,horarioCierre) VALUES (?,?,?);";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, dia.getNombre());
        statement.setTime(2, Time.valueOf(dia.getHoraApertura()));
        statement.setTime(3, Time.valueOf(dia.getHoraCierre()));
        statement.executeUpdate();
    }
    
    public boolean comprobarHorario(ClaseDia clase) throws SQLException{
        ArrayList<ArrayList<ClaseDia>> semana = claseDAO.extraerClasesPorDia();
        ArrayList<Dia> dias = calendarioDAO.extraerDias();
        boolean resultado = true;
        
        Map<String, Integer> diaIndice = new HashMap<>();
        diaIndice.put("lunes", 0);
        diaIndice.put("martes", 1);
        diaIndice.put("miercoles", 2);
        diaIndice.put("jueves", 3);
        diaIndice.put("viernes", 4);
        diaIndice.put("sabado", 5);
        diaIndice.put("domingo", 6);
        
         Integer indice = diaIndice.get(clase.getDiaSemana().toLowerCase());
        
         if (indice != null) {
            ArrayList<ClaseDia> lista = semana.get(indice);
            for (int i = 0; i < lista.size(); i++) {
                if (!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))) {
                    if (!(clase.getInicioClase().isBefore(dias.get(indice).getHoraApertura()) || clase.getFinalClase().isAfter(dias.get(indice).getHoraCierre()))) {
                        resultado = false;
                    }
                }
            }
         }  
         return resultado;   
    }   
}
