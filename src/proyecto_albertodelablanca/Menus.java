package proyecto_albertodelablanca;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alberto
 */
public abstract class Menus {
    
    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    
    
    
    public static void menuInicial(){  
        int opcion = 0;
        do{
            try{
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
                       MetodosGimnasio.login();
                       break;
                    }
                    case 2:{
                        MetodosGimnasio.crearCuenta("cliente");
                        break;
                    }
                    case 3:{
                        MetodosGimnasio.recuperarPassword();
                        break;
                    }
                    case 4:{
                        System.out.println("Hasta luego!");
                        Conexion.cerrarConexion();
                        break;
                    }
                    default:{
                        System.out.println("La opcion introducida es incorrecta");
                    }
                }
            }
            catch(InputMismatchException e){
                System.err.println("Error: el dato introducido no es correcto");
                sc.next();
            }
        }while(opcion != 4);  
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
            System.out.println("6. Mostrar horario gimnasio");
            System.out.println("7. Cerrar sesion");
            try{
                System.out.print("Introduzca la opcion: ");
                opcion = sc.nextInt();

                switch(opcion){
                    case 1:{
                        MetodosGimnasio.buscarCliente();
                        break;
                    }
                    case 2:{
                        MetodosGimnasio.mostrarClientes();
                        break;
                    }
                    case 3:{
                        MetodosGimnasio.eliminarCliente(usuario);
                        break;
                    }
                    case 4:{
                        menuClasesAdmin();
                        break;
                    }
                    case 5:{
                        MetodosGimnasio.actualizarUsuario(usuario);
                        break;
                    }
                    case 6:{
                        MetodosGimnasio.mostrarHorarioGimnasio();
                        break;
                    }
                    case 7:{
                        break;
                    }
                    default :{
                        System.err.println("Error: la opcion introducida no es correcta");
                    }
                }
            }catch(InputMismatchException e){
                System.err.println("Error: la opcion introducida es incorrecto");
            }
            
        }while(opcion != 7);
    }
     
     
     private static void menuClasesAdmin(){
        int opcion;
        do{   
            System.out.println("**********************************");
            System.out.println("1. Mostrar horarios de clases");
            System.out.println("2. Mostrar informacion de las clases");
            System.out.println("3. Crear una clase");
            System.out.println("4. Establecer horario de una clase");
            System.out.println("5. Eliminar una clase");
            System.out.println("6. Salir");
            System.out.print("Introduzca la opcion: ");
            opcion = sc.nextInt();
            
            switch(opcion){
                case 1:{
                    MetodosGimnasio.mostrarHorariosClases();
                    break;
                }
                case 2:{
                    MetodosGimnasio.mostrarInformacionClases();
                    break;
                }
                case 3:{
                    MetodosGimnasio.incluirClase();
                    break;
                }
                case 4:{
                    MetodosGimnasio.establecerClase();
                    break;
                }
                case 5:{
                     MetodosGimnasio.eliminarClase();
                     break;
                }
                case 6:{
                    System.out.println("Saliendo del menu de opciones...");
                    break;
                }
                default:{
                    System.err.println("La opcion introducida es incorrecta");
                    break;
                }
            }
        }while(opcion != 6);
    } 
       
    public static void menuUsuario(Usuario usuario){
        int opcion = 0;
        do{
            System.out.println("********************************");
            System.out.println("Bienvenido " + usuario.getNombre() + " " + usuario.getApellidos() + "!");
            System.out.println("********************************");
            System.out.println("-----------------MENU-----------------");
            System.out.println("1. Mostrar tus datos");
            System.out.println("2. Actualizar tus datos");
            System.out.println("3. Menu de clases");
            System.out.println("4. Mostrar horario gimnasio");
            System.out.println("5. Borrar cuenta");
            System.out.println("6. Cerrar sesion");
            try{
                System.out.print("Introduzca la opcion: ");
                opcion = sc.nextInt();
                
                switch(opcion){
                    case 1:{
                        MetodosGimnasio.mostrarInformacionCliente(usuario);
                        break;
                    }
                    case 2:{
                        MetodosGimnasio.actualizarUsuario(usuario);
                        break;
                    }
                    case 3:{
                        menuClasesCliente(usuario);
                        break;
                    }
                    case 4:{
                        MetodosGimnasio.mostrarHorarioGimnasio();
                        break;
                    }
                    case 5:{
                        if(MetodosGimnasio.eliminarCliente(usuario)){
                            opcion = 5;
                        }
                        break;
                    }
                    case 6:{
                        System.out.println("Cerrando sesion...");
                        System.out.println("Hasta luego!");
                        break;
                    }
                    default:{
                        System.err.println("opcion introducida incorrecta");
                    }
                }
            }
            catch(InputMismatchException e){
                System.err.println("Error: la opcion introducida es incorrecto");
            }
        }while(opcion != 6);   
    } 
     
    private static void menuClasesCliente(Usuario usuario){
        int opcion = 0;
        do{    
             System.out.println("**************************");
            System.out.println("1. Mostrar horarios de clases");
            System.out.println("2. Mostrar mis clases reservadas");
            System.out.println("3. Mostrar informacion de las clases");
            System.out.println("4. Reservar una clase");
            System.out.println("5. Eliminar una reserva");
            System.out.println("6. Salir");
            System.out.print("Introduzca su opcion: ");
            try{
                opcion = sc.nextInt();

                switch(opcion){
                    case 1:{
                        MetodosGimnasio.mostrarHorariosClases();
                        break;
                    }
                    case 2:{
                        MetodosGimnasio.mostrarMisReservas(usuario);
                        break;
                    }
                    case 3:{
                        MetodosGimnasio.mostrarInformacionClases();
                        break;
                    }
                    case 4:{
                        MetodosGimnasio.reservarClase(usuario);
                        break;
                    }
                    case 5:{
                        MetodosGimnasio.eliminarReserva(usuario);
                        break;
                    }
                    case 6:{
                        System.out.println("Saliendo del menu de clases...");
                        break;
                    }
                    default:{
                        System.err.println("Opcion introducida incorrecta");
                    }
                }
            }
            catch(InputMismatchException e){
                System.err.println(e.getMessage());
            }
        }while(opcion != 6);
    } 
        
}

