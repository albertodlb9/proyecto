
package proyecto_albertodelablanca;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public class ClaseDia extends Clase {
    
    private LocalTime inicioClase;
    private LocalTime finalClase;
    private String diaSemana;
    private ArrayList<Usuario> usuarios;
    private int plazas;

    public ClaseDia(LocalTime inicioClase, LocalTime finalClase, String diaSemana, int plazas, String idClase, String nombre, String descripcion) {
        super(idClase, nombre, descripcion);
        this.inicioClase = inicioClase;
        this.finalClase = finalClase;
        this.diaSemana = diaSemana;
        this.usuarios = new ArrayList<>();
    }

    public LocalTime getInicioClase() {
        return inicioClase;
    }

    public void setInicioClase(LocalTime inicioClase) {
        this.inicioClase = inicioClase;
    }

    public LocalTime getFinalClase() {
        return finalClase;
    }

    public void setFinalClase(LocalTime finalClase) {
        this.finalClase = finalClase;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }
    
    public int getPlazasLibres(){
        return plazas - this.usuarios.size();
    }
    
    public void addUsuario(Usuario usuario){
        if(usuarios.size() < plazas){
            usuarios.add(usuario);
        } else{
            System.out.println("Esta clase ya esta llena");
        }
    }
}
