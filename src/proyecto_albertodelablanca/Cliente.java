
package proyecto_albertodelablanca;

import java.time.LocalDate;

/**
 *
 * @author Alberto
 */
public class Cliente extends Persona{
    
   private LocalDate fechaAlta;
   private String tipoMembresia;
   private String cuentaBancaria;

    public Cliente(LocalDate fechaAlta, String tipoMembresia, String cuentaBancaria, String dni, String nombre, String apellido1, String apellido2, int edad, char sexo, int telefono) {
        super(dni, nombre, apellido1, apellido2, edad, sexo, telefono);
        this.fechaAlta = fechaAlta;
        this.tipoMembresia = tipoMembresia;
        this.cuentaBancaria = cuentaBancaria;
    }

    public Cliente(String dni) {
        super(dni);
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }
   
    public String getCuentaBancaria(){
        return cuentaBancaria;
    }
}
