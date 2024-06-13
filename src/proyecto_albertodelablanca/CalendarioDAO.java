
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarioDAO {
    
    private Connection conexion;
    
    public CalendarioDAO(Connection conexion){
        this.conexion = conexion;
    }
    
    public ArrayList<Dia> extraerDias() throws SQLException{
        String sql = "SELECT * FROM calendario;";
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Dia> dias = new ArrayList<>();
        
        while(rs.next()){
            String nombre = rs.getString("nombre");
            LocalTime horaApertura = rs.getTime("horarioApertura").toLocalTime();
            LocalTime horaCierre = rs.getTime("horarioCierre").toLocalTime();
            
            Dia dia = new Dia(nombre,horaApertura,horaCierre);
            dias.add(dia);
        }
        
        return dias;
    }
}
