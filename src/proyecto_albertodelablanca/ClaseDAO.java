
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClaseDAO {
    
    private Connection conexion;

    public ClaseDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public ArrayList<ArrayList<ClaseDia>> extraerClasesPorDia() throws SQLException {
        ArrayList<ArrayList<ClaseDia>> semana = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            semana.add(new ArrayList<>());
        }

        String sql = "SELECT clases.*, calendario_clases.horaInicio, calendario_clases.horaFinal, calendario_clases.nombre, calendario_clases.plazas FROM clases, calendario_clases WHERE clases.idClase = calendario_clases.idClase ORDER BY calendario_clases.horaInicio;";        
        PreparedStatement statement = this.conexion.prepareStatement(sql); 
        ResultSet rs = statement.executeQuery();

        Map<String, Integer> mapaDias = this.mapaDias();
        ArrayList<ClaseDia> comprobacion = this.extraerClases_calendario();

        if(!comprobacion.isEmpty()){
            while (rs.next()) {
                int idClase = rs.getInt("idClase");
                String nombre = rs.getString("clases.nombre");
                String descripcion = rs.getString("descripcion");
                LocalTime horaInicio = rs.getTime("calendario_clases.horaInicio").toLocalTime();
                LocalTime horaFinal = rs.getTime("calendario_clases.horaFinal").toLocalTime();
                String nombreDia = rs.getString("calendario_clases.nombre");
                int plazas = rs.getInt("calendario_clases.plazas");

                ClaseDia clase = new ClaseDia(horaInicio, horaFinal, nombreDia, plazas, idClase, nombre, descripcion);
                Integer indice = mapaDias.get(clase.getDiaSemana());
                if (indice != null) {
                    semana.get(indice).add(clase);
                }
            }
        }

        return semana;
    }

    public Map<String, Integer> mapaDias() {
        Map<String, Integer> mapaDias = new HashMap<>();
        mapaDias.put("lunes", 0);
        mapaDias.put("martes", 1);
        mapaDias.put("miercoles", 2);
        mapaDias.put("jueves", 3);
        mapaDias.put("viernes", 4);
        mapaDias.put("sabado", 5);
        mapaDias.put("domingo", 6);
        return mapaDias;
    }

    
    
    public void crearClase(int idClase, String nombre, String descripcion) throws SQLException{        
        String sql = "INSERT INTO clases VALUES (?, ?, ?);";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, idClase);
        statement.setString(2, nombre);
        statement.setString(3, descripcion);

        statement.executeUpdate();       
    }
    
    public void eliminarClase(int idClase) throws SQLException{
        String sql = "DELETE FROM clases WHERE idClase = ?;";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, idClase);
        statement.executeUpdate();
    }
    
    public void establecerClase(int idClase, String dia, LocalTime horaInicio, LocalTime horaFinal, int plazas) throws SQLException{
        
        String sql = "INSERT INTO calendario_clases VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(2, idClase);
        statement.setString(1, dia);
        statement.setTime(3, Time.valueOf(horaInicio));
        statement.setTime(4, Time.valueOf(horaFinal));
        statement.setInt(5, plazas);
        statement.executeUpdate();
    }

    public ArrayList<ClaseDia> extraerClases_calendario() throws SQLException{
    
        String sql = "SELECT * FROM calendario_clases;";
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<ClaseDia> clases = new ArrayList<>();
        
        while (rs.next()){
            String dia = rs.getString("nombre");
            int idClase = rs.getInt("idClase");
            LocalTime horaInicio = rs.getTime("horaInicio").toLocalTime();
            LocalTime horaFinal = rs.getTime("horaFinal").toLocalTime();
            int plazas = rs.getInt("plazas");
            
            clases.add(new ClaseDia(horaInicio,horaFinal,dia,plazas,idClase,"",""));
        }
        return clases;
    }
    
    public ArrayList<Clase> extraerInformacionClases() throws SQLException{
        String sql = "SELECT * FROM clases ORDER BY idClase;";
        PreparedStatement statement = conexion.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        ArrayList<Clase> clases = new ArrayList<>();
        
        while(rs.next()){
            int idClase = rs.getInt("idClase");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            
            Clase clase = new Clase(idClase,nombre,descripcion);
            clases.add(clase);
        }
        return clases;
    }
    
    
}
