package proyecto_albertodelablanca;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * 
 * @author Alberto
 */
public class Principal {
    
    private static Gimnasio gimnasio;
    private static CalendarioClases calendario;
    private static Scanner sc;
    
    public static void main(String[] args) {
        
        creacionGimnasio();
        menu();
    }
    
    
    public static void menu(){
        
        System.out.println("Bienvenido al sistema de gestion del gimnasio");
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
    
    public static void creacionGimnasio(){
        gimnasio = new Gimnasio();
        actualizacionGimnasio();
        
        if(gimnasio.getNombre().equals("")){
            System.out.print("Introduzca el nombre de su gimnasio: ");
            gimnasio.setNombre(sc.nextLine());
        }
    }
    
    public static void actualizacionGimnasio(){
        //metodo para leer los datos del archivo y actualizar el gimnasio
    }

}