package proyecto_albertodelablanca;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.activation.DataHandler;
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
        menuInicial();  
    }
    
    public static void menuAdmin(Usuario usuario){
        System.out.println("********************************");
        System.out.println("Bienvenido " + usuario.getNombre() + " " + usuario.getApellidos() + " al sistema de gestion del gimnasio");
        int opcion = 0;
        do{
            System.out.println("********************************");
            System.out.println("-----------------MENU-----------------");
            System.out.println("1. Mostrar la informacion de un cliente");
            System.out.println("2. Mostrar la informacion de todos los clientes");
            System.out.println("3. Dar de baja un cliente");
            System.out.println("4. Menu de gestion de clases");
            System.out.println("5. Actualizar informacion del usuario");
            System.out.println("6. Cerrar sesion");
            try{
                System.out.print("Introduzca la opcion: ");
                opcion = sc.nextInt();

                switch(opcion){
                    case 1:{
                        buscarCliente();
                        break;
                    }
                    case 2:{
                        mostrarClientes();
                        break;
                    }
                    case 3:{
                        eliminarCliente(usuario);
                        break;
                    }
                    case 4:{
                        menuClases();
                        break;
                    }
                    case 5:{
                        actualizarUsuario(usuario);
                        break;
                    }
                    case 6:{
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
    
    private static void menuUsuario(Usuario usuario){
        System.out.println("********************************");
        System.out.println("Bienvenido " + usuario.getNombre() + " " + usuario.getApellidos() + "!");
    }
    
    private static void menuClases(){
        
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
    
    private static void menuInicial(){
       
        int opcion;
        do{
            System.out.println("***************************************");
            System.out.println("Bienvenido");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrar nuevo usuario");
            System.out.println("3. Ha olvidado su contraseña?");
            System.out.println("4. Salir del programa");
            System.out.print("Introduzca una opcion: ");
            opcion = sc.nextInt();
            
            switch(opcion){
                case 1:{
                   login();
                   break;
                }
                case 2:{
                    crearCuenta("cliente");
                    break;
                }
                case 3:{
                    recuperarPassword();
                    break;
                }
                case 4:{
                    System.out.println("Hasta luego!");
                    break;
                }
                default:{
                    System.out.println("La opcion introducida es incorrecta");
                }
            }
        }while(opcion != 4);  
    }
    
    private static void login(){
         boolean salida = true;
         int contador = 0;
        do{
            try{
                System.out.println("***************************************");
                if(contador < 3){
                    System.out.print("Nickname: ");
                    String nickname = sc.next();
                    System.out.print("Password: ");
                    String password = sc.next();
                    contador++;

                    Usuario usuario = usuarioDAO.obtenerUsuarioPorNickname(nickname);
                    if(password.equals(usuario.getPassword())){
                        if(usuario.getTipo().equals("admin")){
                            menuAdmin(usuario);
                        } else{
                            menuUsuario(usuario);
                        }
                         salida = false;
                    } else{
                        System.err.println("Password incorrecto");
                    }   
                } else{
                    int opcion;
                    do{
                        System.out.println("Ha fallado en el login varias veces");
                        System.out.println("1. Intentarlo de nuevo");
                        System.out.println("2. Volver al menu anterior");
                        System.out.print("Introduzca su opcion: ");
                        opcion = sc.nextInt();
                        
                        switch(opcion){
                            case 1:{
                                contador =0;
                                break;
                            }
                            case 2:{
                                menuInicial();
                                salida = false;
                                break;
                            }
                            default:{
                                System.out.println("Opcion introducida incorrecta");
                                salida = false;
                            }
                        }
                        
                    }while(opcion != 1 && opcion != 2);
                }
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
            catch(NullPointerException e){
                System.err.println("El nickname es incorrecto es incorrecto");
            }
        } while(salida);
    }
    
    private static void buscarCliente(){
        try{
            System.out.print("Introduzca el dni del cliente: ");
            String dni = sc.next();
            Usuario usuario = usuarioDAO.obtenerUsuarioPorDni(dni);
            System.out.println(usuario.toString());
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(NullPointerException e){
            System.out.println("No se ha encontrado ningun cliente con ese dni");
        }
    }
    
    private static void mostrarClientes(){
        try{
            ArrayList<Usuario> usuarios = usuarioDAO.extraerUsuarios();
            Iterator <Usuario> it = usuarios.iterator();
            
            while(it.hasNext()){
                Usuario usuario = it.next();
                System.out.println(usuario.toString());
                System.out.println("*****************************");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private static void actualizarUsuario(Usuario usuario){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("Introduzca a continuacion sus datos para actualizar:");
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

            Usuario usuarioActualizado = new Usuario(usuario.getDni(),nombre,apellidos,email,LocalDate.parse(fechaNacimiento,formatter),sexo,telefono,direccion,cuentaBancaria,usuario.getTipo(),usuario.getNickname(),usuario.getPassword());
            usuarioDAO.actualizarUsuario(usuarioActualizado);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    private static void eliminarCliente(Usuario usuario){
        if(usuario.getTipo().equals("admin")){
            try{
                System.out.print("Introduzca el dni del cliente: ");
                String dni = sc.next();
                usuarioDAO.eliminarUsuario(dni);
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
            catch(NullPointerException e){
                System.err.println(e.getMessage());
            }
        }else{
            try{
                usuarioDAO.eliminarUsuario(usuario.getDni());
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }
    
    private static void recuperarPassword(){
        System.out.println("*****************************");
        System.out.print("Introduzca su nickname: ");
        String nickname = sc.next();
        try{
            Usuario usuario = usuarioDAO.obtenerUsuarioPorNickname(nickname);
            String email = usuario.getEmail();
            Random random = new Random();
            String letrasMayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
            String digitos = "0123456789";
            String password = "";
            for(int i = 0; i < 12; i++){
                switch(random.nextInt(3)){
                    case 0:{
                        password += letrasMayusculas.charAt(random.nextInt(letrasMayusculas.length()));
                        break;
                    }
                    case 1:{
                        password += letrasMinusculas.charAt(random.nextInt(letrasMinusculas.length()));
                        break;
                    }
                    case 2:{
                        password += digitos.charAt(random.nextInt(digitos.length()));
                        break;
                    }
                }
            }
            usuario.setPassword(password);
            usuarioDAO.actualizarUsuario(usuario);
            Email.enviarConGMail(email, "Recuperar contraseña", "Su nueva contraseña es: " + password);
            System.out.println("Se ha enviado la nueva contraseña al correo " + usuario.getEmail());
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(NullPointerException e){
            System.err.println("No se ha encontrado el nickname");
        }
    }
}