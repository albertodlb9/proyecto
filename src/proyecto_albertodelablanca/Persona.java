
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */
public abstract class Persona {
     
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private char sexo;
    private int telefono;

    public Persona(String dni, String nombre, String apellido1, String apellido2, int edad, char sexo, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.sexo = sexo;
        this.telefono = telefono;
    }

    public Persona(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public char getSexo() {
        return sexo;
    }
    
    public int getTelefono(){
        return telefono;
    }
    
    
}
