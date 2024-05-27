
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class DiaDAO {
    
    private static Connection conexion;
    private static ClaseDAO claseDAO = new ClaseDAO(conexion); 
    
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
    
    public void comprobarHorario(ClaseDia clase){
        ArrayList<ArrayList> semana = claseDAO.extraerClasesPorDia();
        switch(clase.getDiaSemana()){
            case "Lunes":{
                int contador = 0;
                for(int i = 0; i < semana.get(0).size(); i++){
                    if(clase.getInicioClase().isAfter(semana.get(0).get(i).) && clase.getFinalClase().isBefore(horaCierre)){
                        for(int i = 0; i < this.clases.size(); i++){
                            ClaseDia claseDia = this.clases.get(i);
                            if(clase.getFinalClase().isBefore(claseDia.getInicioClase()) && clase.getInicioClase().isAfter(claseDia.getFinalClase())){
                                contador++;
                            }
                        }
                    }
                }
                if(clases.size() == contador){
                    clases.add(clase);
                    System.out.println("La clase se añadio correctamente");
                }
            }
        }
    }
}
