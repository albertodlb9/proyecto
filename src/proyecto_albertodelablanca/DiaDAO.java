
package proyecto_albertodelablanca;

/**
 *
 * @author Alberto
 */

import java.sql.*;
import java.util.ArrayList;

public class DiaDAO {
    
    private static Connection conexion;
    private static ClaseDAO claseDAO; 
    private static CalendarioDAO calendarioDAO;
    
    public DiaDAO(Connection conexion){
        this.conexion = conexion;
        this.claseDAO = new ClaseDAO(conexion); 
        this.calendarioDAO = new CalendarioDAO(conexion);
    }
    
    public void añadirDia(Dia dia) throws SQLException{
        String sql = "INSERT INTO calendario (nombre,horarioApertura,horarioCierre) VALUES (?,?,?);";
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, dia.getNombre());
        statement.setTime(2, Time.valueOf(dia.getHoraApertura()));
        statement.setTime(3, Time.valueOf(dia.getHoraCierre()));
        statement.executeUpdate();
    }
    
    public boolean comprobarHorario(ClaseDia clase) throws SQLException{
        ArrayList<ArrayList<ClaseDia>> semana = claseDAO.extraerClasesPorDia();
        ArrayList<Dia> dias = calendarioDAO.extraerDias();
        boolean resultado = true;
        switch(clase.getDiaSemana()){
            case "Lunes":{
                ArrayList<ClaseDia> lista = semana.get(0);
                for(int i = 0; i < lista.size(); i++){
                    if(!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))){
                        if(!(clase.getInicioClase().isAfter(dias.get(0).getHoraApertura()) && clase.getFinalClase().isBefore(dias.get(0).getHoraCierre()))){
                            resultado = false;
                        }
                    }
                }
                break; 
            }
            case "Martes":{
                ArrayList<ClaseDia> lista = semana.get(1);
                for(int i = 0; i < lista.size(); i++){
                    if(!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))){
                        if(!(clase.getInicioClase().isAfter(dias.get(1).getHoraApertura()) && clase.getFinalClase().isBefore(dias.get(1).getHoraCierre()))){
                            resultado = false;
                        }
                    }
                }
                break;
            }
            case "Miercoles":{
                ArrayList<ClaseDia> lista = semana.get(2);
                for(int i = 0; i < lista.size(); i++){
                    if(!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))){
                        if(!(clase.getInicioClase().isAfter(dias.get(2).getHoraApertura()) && clase.getFinalClase().isBefore(dias.get(2).getHoraCierre()))){
                            resultado = false;
                        }
                    }
                }
                break;
            }
            case "Jueves":{
                ArrayList<ClaseDia> lista = semana.get(3);
                for(int i = 0; i < lista.size(); i++){
                    if(!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))){
                        if(!(clase.getInicioClase().isAfter(dias.get(3).getHoraApertura()) && clase.getFinalClase().isBefore(dias.get(3).getHoraCierre()))){
                            resultado = false;
                        }
                    }
                }
                break;
            }
            case "Viernes":{
                ArrayList<ClaseDia> lista = semana.get(4);
                for(int i = 0; i < lista.size(); i++){
                    if(!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))){
                        if(!(clase.getInicioClase().isAfter(dias.get(4).getHoraApertura()) && clase.getFinalClase().isBefore(dias.get(4).getHoraCierre()))){
                            resultado = false;
                        }
                    }
                }
                break;
            }
            case "Sabado":{
                ArrayList<ClaseDia> lista = semana.get(5);
                for(int i = 0; i < lista.size(); i++){
                    if(!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))){
                        if(!(clase.getInicioClase().isAfter(dias.get(5).getHoraApertura()) && clase.getFinalClase().isBefore(dias.get(5).getHoraCierre()))){
                            resultado = false;
                        }
                    }
                }
                break;
            }
            case "Domingo":{
                ArrayList<ClaseDia> lista = semana.get(6);
                for(int i = 0; i < lista.size(); i++){
                    if(!(clase.getInicioClase().isAfter(lista.get(i).getFinalClase()) || clase.getFinalClase().isBefore(lista.get(i).getInicioClase()))){
                        if(!(clase.getInicioClase().isAfter(dias.get(6).getHoraApertura()) && clase.getFinalClase().isBefore(dias.get(6).getHoraCierre()))){
                            resultado = false;
                        }
                    }
                }
                break;
            }
        }
        return resultado;
    }

    public Connection getConexion() {
        return conexion;
    }
    
    
}
