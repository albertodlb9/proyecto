
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class UsuarioDAO {
    
    private Connection conexion;
    private ClaseDAO claseDAO;
    
    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
        this.claseDAO = new ClaseDAO(conexion);
    }
    
    
    public ArrayList<Usuario> extraerUsuarios() throws SQLException{
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios;";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                String tipo = rs.getString("tipo");
                int telefono = rs.getInt("telefono");
                String sexo = rs.getString("sexo");
                String cuentaBancaria = rs.getString("cuentaBancaria");
                String direccion = rs.getString("direccion");
                String nickname = rs.getString("nickname");
                String password = rs.getString("contraseña");
                
                usuarios.add(new Usuario(dni,nombre,apellidos,email,fechaNacimiento,sexo,telefono,direccion,cuentaBancaria,tipo,nickname,password));
            }
        }
        
        return usuarios;
    }
    
    public void crearUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (dni, nombre, apellidos, fechaNacimiento, direccion, email, telefono, sexo, cuentaBancaria, tipo, nickname, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getDni());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getApellidos());
            statement.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
            statement.setString(5, usuario.getDireccion());
            statement.setString(6, usuario.getEmail());
            statement.setInt(7, usuario.getTelefono());
            statement.setString(8,usuario.getSexo());
            statement.setString(9, usuario.getCuentaBancaria());
            statement.setString(10, usuario.getTipo());
            statement.setString(11, usuario.getNickname());
            statement.setString(12, usuario.getPassword());
            statement.executeUpdate();
            System.out.println("Usuario creado exitosamente en la base de datos.");
        }
    }
    
    public Usuario obtenerUsuarioPorDni(String dni) throws SQLException{
       
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE dni = ?;";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, dni);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String dniUsuario = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                String tipo = rs.getString("tipo");
                int telefono = rs.getInt("telefono");
                String sexo = rs.getString("sexo");
                String cuentaBancaria = rs.getString("cuentaBancaria");
                String direccion = rs.getString("direccion");
                String nickname = rs.getString("nickname");
                String password = rs.getString("contraseña");
                
                usuario = new Usuario(dniUsuario,nombre,apellidos,email,fechaNacimiento,sexo,telefono,direccion,cuentaBancaria,tipo,nickname,password);
            }
        }
        return usuario;
    }
    
    public void eliminarUsuario(String dni) throws SQLException{
        String sql = "DELETE FROM usuarios WHERE dni = ?;";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, dni);
            statement.executeUpdate();
            System.out.println("Usuario eliminado"); 
        }
    }
    
    public void actualizarUsuario(Usuario usuario) throws SQLException{
        String sql = "UPDATE usuarios SET dni = ?, nombre = ?, apellidos = ?, email = ?, fechaNacimiento = ?, telefono = ?, sexo = ?, cuentaBancaria = ?, direccion = ?, nickname = ?, contraseña = ? WHERE dni = ?;";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, usuario.getDni());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getApellidos());
            statement.setString(4, usuario.getEmail());
            statement.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
            statement.setInt(6, usuario.getTelefono());
            statement.setString(7,usuario.getSexo());
            statement.setString(8, usuario.getCuentaBancaria());
            statement.setString(9, usuario.getDireccion());
            statement.setString(10,usuario.getNickname());
            statement.setString(11,usuario.getPassword());
            statement.setString(12,usuario.getDni());
            statement.executeUpdate();
        }
    }
    
    public Usuario obtenerUsuarioPorNickname(String nickname) throws SQLException{
       
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE nickname = ?;";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, nickname);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String dniUsuario = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                String tipo = rs.getString("tipo");
                int telefono = rs.getInt("telefono");
                String sexo = rs.getString("sexo");
                String cuentaBancaria = rs.getString("cuentaBancaria");
                String direccion = rs.getString("direccion");
                String nicknameUsuario = rs.getString("nickname");
                String password = rs.getString("contraseña");
                
                usuario = new Usuario(dniUsuario,nombre,apellidos,email,fechaNacimiento,sexo,telefono,direccion,cuentaBancaria,tipo,nicknameUsuario,password);
            }
        }
        return usuario;
    }
    
    public ArrayList<ClaseDia> extraerReservasPorDni(String dni) throws SQLException{
        ArrayList<ClaseDia> clases = new ArrayList<>();
        String sql = "SELECT * FROM clases_usuarios, clases WHERE clases_usuarios.idClase = clases.idClase AND dni = ? ORDER BY CASE dia WHEN 'lunes' THEN 1 WHEN 'martes' THEN 2 WHEN 'miércoles' THEN 3 WHEN 'jueves' THEN 4 WHEN 'viernes' THEN 5 WHEN 'sábado' THEN 6 WHEN 'domingo' THEN 7 END;";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, dni);
        ResultSet rs = statement.executeQuery();
        
        while(rs.next()){
            int idClase = rs.getInt("clases_usuarios.idClase");
            String dia = rs.getString("clases_usuarios.dia");
            LocalTime horaInicio = rs.getTime("clases_usuarios.horaInicio").toLocalTime();
            String nombre = rs.getString("clases.nombre");
            ClaseDia clase = new ClaseDia(horaInicio,null,dia,0,idClase,nombre,"");
            clases.add(clase);
        }
        return clases;
    }
    
    public void realizarReserva(String dni, ClaseDia clase) throws SQLException{
        ArrayList<ClaseDia> clases = claseDAO.extraerClases_calendario();
        boolean comprobacion = false;
        
        for(int i = 0; i < clases.size(); i++){
            if(clases.get(i).getDiaSemana().equals(clase.getDiaSemana()) && clases.get(i).getIdClase() == clase.getIdClase() && clases.get(i).getInicioClase().equals(clase.getInicioClase()) && clases.get(i).getPlazas() > 0){
                comprobacion = true;
            }
        }
        
        if(comprobacion){
            String sql1 = "INSERT INTO clases_usuarios VALUES(?,?,?,?);";
            PreparedStatement statement1 = conexion.prepareStatement(sql1);
            statement1.setInt(1, clase.getIdClase());
            statement1.setString(2, dni);
            statement1.setString(3, clase.getDiaSemana());
            statement1.setTime(4, Time.valueOf(clase.getInicioClase()));
            statement1.executeUpdate();
            
            String sql2 = "UPDATE calendario_clases SET plazas = plazas - 1 WHERE idClase = ? AND horaInicio = ? AND nombre = ?;";
            PreparedStatement statement2 = conexion.prepareStatement(sql2);
            statement2.setInt(1, clase.getIdClase());
            statement2.setTime(2, Time.valueOf(clase.getInicioClase()));
            statement2.setString(3, clase.getDiaSemana());
            statement2.executeUpdate();
            
            System.out.println("La reserva se ha realizado correctamente");
        } else{
            System.err.println("Error: no se ha encontrado ninguna clase que se corresponda con los datos introducidos o no quedan plazas de la misma");
        }
    }
    
    public void cancelarReserva(String dni, ClaseDia clase) throws SQLException{
        ArrayList<ClaseDia> reservas = this.extraerReservasPorDni(dni);
        boolean comprobacion = false;
        
        for(int i = 0; i < reservas.size(); i++){
            if(clase.getIdClase() == reservas.get(i).getIdClase() && clase.getDiaSemana().equals(reservas.get(i).getDiaSemana()) && clase.getInicioClase().equals(reservas.get(i).getInicioClase())){
              comprobacion = true;  
            }
        }
        
        if(comprobacion){
            String sql1 = "DELETE FROM clases_usuarios WHERE dni = ? AND idClase = ? AND dia = ? AND horaInicio = ?;";
            PreparedStatement statement1 = conexion.prepareStatement(sql1);
            statement1.setString(1,dni);
            statement1.setInt(2, clase.getIdClase());
            statement1.setString(3, clase.getDiaSemana());
            statement1.setTime(4,Time.valueOf(clase.getInicioClase()));
            statement1.executeUpdate();
            
            String sql2 = "UPDATE calendario_clases SET plazas = plazas + 1 WHERE idClase = ? AND horaInicio = ? AND nombre = ?;";
            PreparedStatement statement2 = conexion.prepareStatement(sql2);
            statement2.setInt(1, clase.getIdClase());
            statement2.setTime(2, Time.valueOf(clase.getInicioClase()));
            statement2.setString(3, clase.getDiaSemana());
            statement2.executeUpdate();
            
            System.out.println("La cancelacion se ha realizado correctamente");
        } else{
            System.err.println("Error: los datos introducido no se corresponden con ninguna reserva o usted no ha relaizado ninguna reserva aun");
        }
        System.out.println("***************************");
    }
    
    public void cancelarTodasReservasUsuario(String dni) throws SQLException{
        ArrayList<ClaseDia> reservas = this.extraerReservasPorDni(dni);
        
        if(!reservas.isEmpty()){
            for(int i = 0; i < reservas.size(); i++){
                String sql1 = "DELETE FROM clases_usuarios WHERE dni = ? AND idClase = ? AND dia = ? AND horaInicio = ?;";
                PreparedStatement statement1 = conexion.prepareStatement(sql1);
                statement1.setString(1,dni);
                statement1.setInt(2, reservas.get(i).getIdClase());
                statement1.setString(3, reservas.get(i).getDiaSemana());
                statement1.setTime(4,Time.valueOf(reservas.get(i).getInicioClase()));
                statement1.executeUpdate();

                String sql2 = "UPDATE calendario_clases SET plazas = plazas + 1 WHERE idClase = ? AND horaInicio = ? AND nombre = ?;";
                PreparedStatement statement2 = conexion.prepareStatement(sql2);
                statement2.setInt(1, reservas.get(i).getIdClase());
                statement2.setTime(2, Time.valueOf(reservas.get(i).getInicioClase()));
                statement2.setString(3, reservas.get(i).getDiaSemana());
                statement2.executeUpdate();
            }           
        }
    }
    
}
