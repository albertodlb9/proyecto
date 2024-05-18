
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioDAO {
    
    private Connection conexion;
    
    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
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
                
                usuarios.add(new Usuario(dni,nombre,apellidos,email,fechaNacimiento,sexo,telefono,direccion,cuentaBancaria,tipo));
            }
        }
        
        return usuarios;
    }
    
    public void crearUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (dni, nombre, apellidos, fechaNacimiento, direccion, email, telefono, sexo, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getDni());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getApellidos());
            statement.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
            statement.setString(5, usuario.getDireccion());
            statement.setString(6, usuario.getEmail());
            statement.setInt(7, usuario.getTelefono());
            statement.setString(8,usuario.getSexo());
            statement.setString(9, usuario.getTipo());
            statement.executeUpdate();
            System.out.println("Alumno creado exitosamente en la base de datos.");
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
                
                usuario = new Usuario(dniUsuario,nombre,apellidos,email,fechaNacimiento,sexo,telefono,direccion,cuentaBancaria,tipo);
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
        String sql = "UPDATE usuarios SET dni = ?, nombre = ?, apellidos = ?, email = ?, fechaNacimiento = ?, tipo = ?, telefono = ?, sexo = ?, cuentaBancaria = ?, direccion = ?;";
        try(PreparedStatement statement = conexion.prepareStatement(sql)){
            statement.setString(1, usuario.getDni());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getApellidos());
            statement.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
            statement.setString(5, usuario.getDireccion());
            statement.setString(6, usuario.getEmail());
            statement.setInt(7, usuario.getTelefono());
            statement.setString(8,usuario.getSexo());
            statement.setString(9, usuario.getTipo());
            statement.executeUpdate();
        }
    }
    
}
