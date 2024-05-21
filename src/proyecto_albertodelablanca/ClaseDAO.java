
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class ClaseDAO {
    
    private Connection conexion;

    public ClaseDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void extraerClasesPorDia() throws SQLException{
        
        ArrayList<ArrayList> semana = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            semana.add(new ArrayList<>());
        }
        String sql = "SELECT clases.*, calendario_clases.horaInicio, calendario_clases.horaFinal, calendario_clases.nombre FROM clases LEFT JOIN calendario_clases ON clases.idClase = calendario_clases.idClase;";
        
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int idClase = rs.getInt("idClase");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                //LocalTime horaInicio = LocalTime.;
            
        }
    }
}
