package proyecto_albertodelablanca;

import com.sun.mail.util.MailConnectException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public static void main(String[] args) {  
        MetodosGimnasio.primeraConexion();
        Menus.menuInicial();  
    }       
}