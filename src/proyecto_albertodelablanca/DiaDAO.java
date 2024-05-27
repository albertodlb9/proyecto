
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
    
    public DiaDAO(Connection conexion){
        this.conexion = conexion;
    }
    
    public void añadirDia(Dia dia) throws SQLException{
        String sql = "INSERT INTO calendario (nombre,horarioApertura,horarioCierre) VALUES (?,?,?);";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, dia.getNombre());
        statement.setInt(2, (dia.getHoraApertura()));
        statement.setInt(3, (dia.getHoraCierre()));
        statement.executeUpdate();
    }
    
    public void comprobarHorario(ClaseDia clase){
   
    }
}
