package proyecto_albertodelablanca;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 * 
 * @author Alberto
 */
public class Principal {
    
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Connection conexion = Conexion.getConexion();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
    
    public static void main(String[] args) {  
        primeraConexion();
        login();  
    }
    
    public static void menuAdmin(){
        
        System.out.println("Bienvenido al sistema de gestion del gimnasio");
        int opcion = 0;
        do{
            System.out.println("-----------------MENU-----------------");
            System.out.println("1. Mostrar la informacion de un cliente");
            System.out.println("2. Mostrar la informacion de todos los clientes");
            System.out.println("3. Menu de gestion de clases");
            System.out.println("4. Actualizar informacion del usuario");
            System.out.println("5. Salir");
            try{
                System.out.print("Introduzca la opcion: ");
                opcion = sc.nextInt();

                switch(opcion){
                    case 1:{
                        //buscarCliente();
                        break;
                    }
                    case 2:{
                        //mostrarClientes();
                        break;
                    }
                    case 3:{
                        //menuClases();
                        break;
                    }
                    case 4:{
                        //actualizarUsuario();
                        break;
                    }
                    case 5:{
                        System.out.println("Guardando cambios...");
                        System.out.println("Saliendo del programa. Hasta luego!");
                        break;
                    }
                    default :{
                        System.err.println("Error: la opcion introducida no es correcta");
                    }
                }
            }catch(InputMismatchException e){
                System.err.println("Error: la opcion introducida es incorrecto");
            }
            
        }while(opcion != 5);
    }
    
    private static void menuUsuario(){
        
    }
    
    private static void primeraConexion(){
        try{
            ArrayList<Usuario> usuarios = usuarioDAO.extraerUsuarios();
            if(usuarios.isEmpty()){
                System.out.println("Bienvenido al programa de gestion de gimnasios.");
                System.out.println("Al no haber ninguna cuenta registrada debera crear una cuenta de administrador.");
                crearCuenta("admin");
            }
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    private static void crearCuenta(String tipo){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            System.out.println("Introduzca a continuacion sus datos personales:");
            System.out.print("DNI: ");
            String dni = sc.next();
            System.out.print("Nombre: ");
            String nombre = sc.next();
            System.out.print("Apellidos: ");
            String apellidos = sc.next();
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Fecha de nacimiento (dd/mm/yyyy): ");
            String fechaNacimiento = sc.next();
            System.out.print("Sexo (M/F): ");
            String sexo = sc.next();
            System.out.print("Telefono: ");
            int telefono = sc.nextInt();
            System.out.print("Direccion: ");
            String direccion = sc.next();
            System.out.print("Numero de cuenta bancaria: ");
            String cuentaBancaria = sc.next();
            System.out.print("Nombre de usuario: ");
            String nickname = sc.next();
            
            boolean salida = true;
            String password;
            do{
                System.out.print("Introduzca su contraseña: ");
                password = sc.next();
                System.out.print("Introduzca de nuevo su contraseña: ");
                String password2 = sc.next();
                if(password.equals(password2)){
                    salida = false;
                } else{
                    System.err.println("Las contraseñas no coinciden. Intentelo de nuevo");
                }
            } while(salida);
            
            
            Usuario nuevoUsuario = new Usuario(dni,nombre,apellidos,email,LocalDate.parse(fechaNacimiento,formatter),sexo,telefono,direccion,cuentaBancaria,tipo,nickname,password);
            usuarioDAO.crearUsuario(nuevoUsuario);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
        catch(DateTimeParseException e){
            System.err.println(e.getMessage());
        }
    }
    
    private static void login(){
        boolean salida = true;
        do{
            try{
                System.out.println("***************************************");
                System.out.println("Bienvenido");
                System.out.print("Nickname: ");
                String nickname = sc.next();
                System.out.print("Password: ");
                String password = sc.next();
                Usuario usuario = usuarioDAO.obtenerUsuarioPorNickname(nickname);
                
                if(usuario.getTipo().equals("admin")){
                    menuAdmin();
                } else{
                    menuUsuario();
                }
                salida = false;
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
        } while(salida);
    }

}