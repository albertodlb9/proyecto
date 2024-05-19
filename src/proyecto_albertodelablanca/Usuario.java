
package proyecto_albertodelablanca;

import java.time.LocalDate;

/**
 *
 * @author Alberto
 */
public class Usuario {
    
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private LocalDate fechaNacimiento;
    private String sexo;
    private int telefono;
    private String cuentaBancaria;
    private String tipo;
    private String direccion;
    private String nickname;
    private String password;

    public Usuario(String dni, String nombre, String apellidos, String email, LocalDate fechaNacimiento, String sexo, int telefono, String direccion, String cuentaBancaria, String tipo, String nickname, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cuentaBancaria = cuentaBancaria;
        this.tipo = tipo;
        this.nickname = nickname;
        this.password = password;
    }
    
    public Usuario(){
        
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "*****************************\n*DNI: " + this.getDni() + "\n*Nombre: " + this.getNombre() + "\n*Apellidos: " + this.getApellidos() + "\n*Email: " + this.getEmail() + "\n*Fecha de nacimiento: " + this.getFechaNacimiento().toString() + "\n*Sexo: " + this.getSexo() + "\n*Direccion: " + this.getDireccion() + "\n*Telefono: " + this.getTelefono() + "\n*Numero de cuenta: " + this.getCuentaBancaria() + "\n*Nickname: " + this.getNickname() + "\n*Password: " + this.getPassword();
    } 
}
