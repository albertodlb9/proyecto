package proyecto_albertodelablanca;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
import java.util.Random;
import com.sun.mail.util.MailConnectException;
import java.util.Iterator;
import javax.activation.DataHandler;

/**
 *
 * @author Alberto
 */
public class MetodosGimnasio {
    
    private static Connection conexion = Conexion.getConexion();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static Calendario calendario = new Calendario();
    private static DiaDAO diaDAO = new DiaDAO(conexion);
    private static ClaseDAO claseDAO = new ClaseDAO(conexion);
    
    public static void primeraConexion(){
        boolean comprobacion = true;
        do{
            try{
                ArrayList<Usuario> usuarios = usuarioDAO.extraerUsuarios();
                if(usuarios.isEmpty()){
                    System.out.println("Bienvenido al programa de gestion de gimnasios.");
                    System.out.println("Al no haber ninguna cuenta registrada debera crear una cuenta de administrador.");
                    crearCuenta("admin");   
                } 
                comprobacion = false;
            }
            catch(SQLException e){
            System.err.println(e.getMessage());
            }
            catch(Exception e){
                System.err.println("Error");
            }
        }while(comprobacion);
        do{
            try{
                comprobacion = true;
                establecerHorario();
                comprobacion = false;
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }while(comprobacion);
    }
    
    public static void crearCuenta(String tipo){
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
    
     public static void establecerHorario() throws SQLException{
        ArrayList<Usuario> usuarios = usuarioDAO.extraerUsuarios();
        if(usuarios.isEmpty()){
            System.out.println("A continuacion debe establecer el horario del gimnasio");
            String[] semana = {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo" };
            for(int i = 0; i < calendario.getDias().length; i++){
                System.out.print(semana[i] + " apertura (hh:mm): ");
                String apertura = sc.next();
                System.out.print(semana[i] + " cierre (hh:mm): ");
                String cierre = sc.next();

                calendario.getDias()[i].setHoraApertura(LocalTime.parse(apertura+":00"));
                calendario.getDias()[i].setHoraCierre(LocalTime.parse(cierre+":00"));
                diaDAO.añadirDia(calendario.getDias()[i]);
            }
        }
    }
     
     public static void login(){
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
                            Menus.menuAdmin(usuario);
                        } else{
                            Menus.menuUsuario(usuario);
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
                                Menus.menuInicial();
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
                System.err.println("El nickname es incorrecto");
            }
        } while(salida);
    }
     
     public static void recuperarPassword(){
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
     
    public static void buscarCliente(){
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
      
    public static void mostrarClientes(){
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
    
    public static boolean eliminarCliente(Usuario usuario){
        boolean salida = false;
        if(usuario.getTipo().equals("admin")){
            try{
                System.out.print("Introduzca el dni del cliente: ");
                String dni = sc.next();
                usuarioDAO.cancelarTodasReservasUsuario(dni);
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
                usuarioDAO.cancelarTodasReservasUsuario(usuario.getDni());
                usuarioDAO.eliminarUsuario(usuario.getDni());
                salida = true;
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
        return salida;
    }
    
    public static void actualizarUsuario(Usuario usuario){
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
    
    public static void mostrarHorariosClases(){
        try{
            ArrayList<ArrayList<ClaseDia>> semana = claseDAO.extraerClasesPorDia();
            int contador = 0;
            
            for(int i = 0; i < semana.size(); i++){
                ArrayList<ClaseDia> dia = semana.get(i); 
                if(!dia.isEmpty()){
                    System.out.println(dia.get(0).getDiaSemana());
                    contador++;
                }
                for(int j = 0; j < dia.size(); j++){
                   System.out.println(dia.get(j).toString());        
                }
            }
            if(contador > 0){
                System.out.println("No hay ninguna clase programada");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch(IndexOutOfBoundsException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void mostrarInformacionClases(){
        System.out.println("***********************************");
        try{
            ArrayList<Clase> clases = claseDAO.extraerInformacionClases();
            if(!clases.isEmpty()){
                for(int i = 0; i < clases.size(); i++){
                    System.out.println(clases.get(i).toString());
                }
            } else{
                System.out.println("No hay clases creadas");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("***********************************");
    }
    
    public static void incluirClase(){
        try{
            System.out.print("Introduzca el id de la clase: ");
            int idClase = sc.nextInt();
            System.out.print("Introduzca el nombre de la clase: ");
            String nombre = sc.next();
            System.out.print("Introduzca una breve descripcion: ");
            String descripcion = sc.next();
            claseDAO.crearClase(idClase, nombre, descripcion);
            System.out.println("Clase creada correctamente");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
    }
     
    public static void establecerClase(){
        try{
            System.out.print("Introduzca el id de la clase: ");
            int id = sc.nextInt();
            System.out.print("Introduzca el dia de la clase: ");
            String dia = sc.next();
            System.out.print("Introduzca la hora de inicio(hh:mm): ");
            String horaInicio = sc.next();
            System.out.print("Introduzca la hora de final(hh:mm): ");
            String horaFinal = sc.next();
            System.out.print("Introduzca el numero de plazas: ");
            int plazas = sc.nextInt();
            
            ClaseDia clase = new ClaseDia(LocalTime.parse(horaInicio + ":00"), LocalTime.parse(horaFinal + ":00"),dia,plazas,id,"","");
            
            if(diaDAO.comprobarHorario(clase)){
                claseDAO.establecerClase(id, dia, LocalTime.parse(horaInicio + ":00"), LocalTime.parse(horaFinal + ":00"), plazas);
                System.out.println("Clase establecida con exito");
            } else{
                System.out.println("No se pudo establecer la clase por incompatibilidad horaria");
            }
            
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void eliminarClase(){
        try{
            System.out.println("Introduzca el id de la clase: ");
            int idClase = sc.nextInt();
            claseDAO.eliminarClase(idClase);
            System.out.println("Clase eliminada con exito");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void mostrarInformacionCliente(Usuario usuario){
        System.out.println(usuario.toString());
    }
    
    public static boolean mostrarMisReservas(Usuario usuario){
        boolean resultado = false;
        try{
            ArrayList<ClaseDia> clases = usuarioDAO.extraerReservasPorDni(usuario.getDni());
            String dia = "";
            int contador = 0;
            
            
            if(!clases.isEmpty()){
                dia = clases.get(0).getDiaSemana();
                for(int i = 0; i < clases.size(); i++){
                    if(!dia.equals(clases.get(i).getDiaSemana())){
                        dia = clases.get(i).getDiaSemana();
                        contador = 0;
                    }
                    if(contador == 0){
                        System.out.println(dia);
                    }
                    System.out.println(clases.get(i).toString());
                    contador++;
                }
                resultado = true;
            } else{
                System.out.println("Usted no ha realizado ninguna reserva");
            }            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        } 
        return resultado;
    }
    
    public static void reservarClase(Usuario usuario){
        try{
            System.out.println("A continuacion se muestran las clases disponibles para hacer una reserva:");
            mostrarHorariosClases();
            System.out.println("**************************");
            System.out.print("Introduzca el id de la clase: ");
            int idClase = sc.nextInt();
            System.out.print("Introduzca el dia de la clase: ");
            String dia = sc.next();
            System.out.print("Introduzca la hora de inicio(hh:mm): ");
            LocalTime horaInicio = LocalTime.parse(sc.next() + ":00");
            
            ClaseDia clase  = new ClaseDia(horaInicio,null,dia,0,idClase,"","");
            usuarioDAO.realizarReserva(usuario.getDni(), clase);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        catch(InputMismatchException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void eliminarReserva(Usuario usuario){
        System.out.println("A continuacion se muestran sus reservas:");
        if(mostrarMisReservas(usuario)){
            System.out.println("************************");
            try{
                System.out.print("Introduzca el id de la clase: ");
                int idClase = sc.nextInt();
                System.out.print("Introduzca el dia de la clase: ");
                String dia = sc.next();
                System.out.print("Introduzca la hora de inicio(hh:mm): ");
                LocalTime horaInicio = LocalTime.parse(sc.next() + ":00");
                
                ClaseDia clase  = new ClaseDia(horaInicio,null,dia,0,idClase,"","");
                usuarioDAO.cancelarReserva(usuario.getDni(), clase);
            }
            catch(SQLException e){
                System.err.println(e.getMessage());
            }
            catch(InputMismatchException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
