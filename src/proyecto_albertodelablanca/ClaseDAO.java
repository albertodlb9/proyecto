
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
    
    public ArrayList<ArrayList> extraerClasesPorDia() throws SQLException{
        
        ArrayList<ArrayList> semana = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            semana.add(new ArrayList<>());
        }
        String sql = "SELECT clases.*, calendario_clases.horaInicio, calendario_clases.horaFinal, calendario_clases.nombre,calendario_clases.plazas FROM clases LEFT JOIN calendario_clases ON clases.idClase = calendario_clases.idClase;";
        
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            int idClase = rs.getInt("idClase");
            String nombre = rs.getString("clases.nombre");
            String descripcion = rs.getString("descripcion");
            LocalTime horaInicio = rs.getTime("horaInicio").toLocalTime();
            LocalTime horaFinal = rs.getTime("horaFinal").toLocalTime();
            String nombreDia = rs.getString("nombre");
            int plazas = rs.getInt("calendario_clases.plazas");

            ClaseDia clase = new ClaseDia(horaInicio,horaFinal,nombreDia,plazas,idClase,nombre,descripcion);

            switch(clase.getDiaSemana()){
                case "Lunes":{
                    semana.get(0).add(clase);
                    break;
                }
                case "Martes":{
                    semana.get(1).add(clase);
                    break;
                }
                case "Miercoles":{
                    semana.get(2).add(clase);
                    break;
                }
                case "Jueves":{
                    semana.get(3).add(clase);
                    break;
                }
                case "Viernes":{
                    semana.get(4).add(clase);
                    break;
                }
                case "Sabado":{
                    semana.get(5).add(clase);
                    break;
                }
                case "Domingo":{
                    semana.get(6).add(clase);
                    break;
                }
            }    
        }
        return semana;
    }
}
