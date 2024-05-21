
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */

import java.sql.*;
import java.time.LocalTime;

public class DiaDAO {
    
    Connection conexion;
    
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
}
