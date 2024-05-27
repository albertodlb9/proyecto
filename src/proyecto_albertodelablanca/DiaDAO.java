
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */

import java.sql.*;
import java.util.ArrayList;

public class DiaDAO {
    
    private static Connection conexion;
    private static ClaseDAO claseDAO = new ClaseDAO(conexion); 
    private static CalendarioDAO calendarioDAO = new CalendarioDAO(conexion);
    
    public DiaDAO(Connection conexion){
        this.conexion = conexion;
    }
    
    public void añadirDia(Dia dia) throws SQLException{
        String sql = "INSERT INTO calendario (nombre,horarioApertura,horarioCierre) VALUES (?,?,?);";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, dia.getNombre());
        statement.setTime(2, Time.valueOf(dia.getHoraApertura()));
        statement.setTime(3, Time.valueOf(dia.getHoraCierre()));
        statement.executeUpdate();
    }
    
    public void comprobarHorario(ClaseDia clase) throws SQLException{
        ArrayList<ArrayList> semana = claseDAO.extraerClasesPorDia();
        ArrayList<Dia> dias = calendarioDAO.extraerDias();
        switch(clase.getDiaSemana()){
            case "Lunes":{
                ArrayList<ClaseDia> lista = semana.get(0);
                for(int i = 0; i < lista.size()-1; i++){
                    if(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase())){
                        if(clase.getInicioClase().)
                        }
                    }
                }
                
            }
        }
    }
}
