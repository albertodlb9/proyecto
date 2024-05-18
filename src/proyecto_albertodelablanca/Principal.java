package proyecto_albertodelablanca;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        
        menu();
    }
    
    
    public static void menu(){
        
        System.out.println("Bienvenido al sistema de gestion del gimnasio" + 0);
        int opcion = 0;
        do{
            System.out.println("-----------------MENU-----------------");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Dar de baja cliente");
            System.out.println("3. Modificar datos de un cliente");
            System.out.println("4. Mostrar clientes");
            System.out.println("5. Menu gestion de clases");
            System.out.println("6. Guardar y salir");
            System.out.print("Elija su opcion: ");
            try{
                opcion = sc.nextInt();

                switch(opcion){
                    case 1:{
                        //añadirCliente();
                        break;
                    }
                    case 2:{
                        //eliminarCliente();
                        break;
                    }
                    case 3:{
                        //modificarCliente();
                        break;
                    }
                    case 4:{
                        //mostrarClientes();
                        break;
                    }
                    case 5:{
                        //menuClases();
                        break;
                    }
                    case 6:{
                        //guardarCambios();
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
            
        }while(opcion != 6);
    }
    
    private static void primeraConexion(){
        try{
            ArrayList<Usuario> usuarios = usuarioDAO.extraerUsuarios();
            if(usuarios.isEmpty()){
                System.out.println("Bienvenido al programa de gestion de gimnasios.");
                System.out.println("Al no haber ninguna cuenta registrada debera crear una cuenta de administrador.");
                
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
            System.out.print("Fecha de nacimiento (yyyy/mm/dd): ");
            String fechaNacimiento = sc.next();
            System.out.print("Sexo (M/F): ");
            String sexo = sc.next();
            System.out.print("Telefono: ");
            int telefono = sc.nextInt();
            System.out.print("Direccion: ");
            String direccion = sc.next();
            System.out.print("Numero de cuenta bancaria: ");
            String cuentaBancaria = sc.next();
            
            Usuario nuevoUsuario = new Usuario(dni,nombre,apellidos,email,LocalDate.parse(fechaNacimiento,formatter),sexo,telefono,direccion,cuentaBancaria,tipo);
            usuarioDAO.crearUsuario(nuevoUsuario);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
    }

}