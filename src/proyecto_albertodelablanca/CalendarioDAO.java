
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
        String sql = "SELECT ;";
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Dia> dias = new ArrayList<>();
        
        while(rs.next()){
            String nombre = rs.getString("nombre");
            LocalTime horaApertura = rs.getTime("horaApertura").toLocalTime();
            LocalTime horaCierre = rs.getTime("horaCierre").toLocalTime();
            
            Dia dia = new Dia(nombre,horaApertura,horaCierre);
            dias.add(dia);
        }
        
        return dias;
    }
}
