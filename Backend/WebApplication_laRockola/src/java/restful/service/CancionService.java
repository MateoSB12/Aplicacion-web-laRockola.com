package restful.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import restful.Model.CancionModel;
import restful.Model.Conexion;

public class CancionService {
    public ArrayList<CancionModel> getCanciones() {
        ArrayList<CancionModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM cancion";

        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                CancionModel cancion = new CancionModel();
                cancion.setId_can(rs.getString("id_can"));
                cancion.setNombre_can(rs.getString("nombre_can"));
                cancion.setId_alb(rs.getString("id_alb"));
                cancion.setDuracion_can(rs.getString("duracion_can"));
                lista.add(cancion);
            }
        } catch (SQLException e) {
        }

        return lista;
    }    

    public CancionModel getCancion(String id) {
        CancionModel cancion = new CancionModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM cancion WHERE id_can = ?";

        try {

            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                
                cancion.setId_can(rs.getString("id_can"));
                cancion.setNombre_can(rs.getString("nombre_can"));
                cancion.setId_alb(rs.getString("id_alb"));
                cancion.setDuracion_can(rs.getString("duracion_can"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cancion;
    }   
    
    public CancionModel addCancion(CancionModel cancion) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO cancion(id_can,nombre_can,id_alb,duracion_can)";
        Sql = Sql + "values (?,?,?,?)";

        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setString(1, cancion.getId_can());
            pstm.setString(2, cancion.getNombre_can());
            pstm.setString(3, cancion.getId_alb());
            pstm.setString(4, cancion.getDuracion_can());
            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return cancion;
    }    
    
    public CancionModel updateCancion(CancionModel cancion) {
        Conexion conn = new Conexion();
        String sql = "UPDATE cancion SET nombre_can=?,id_alb=?,duracion_can=? WHERE id_can= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            
            pstm.setString(1, cancion.getNombre_can());
            pstm.setString(2, cancion.getId_alb());
            pstm.setString(4, cancion.getId_can());
            pstm.setString(3, cancion.getDuracion_can());
            
            pstm.executeUpdate();

        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al ectualizar  " + excepcion.getMessage());
            return null;
        }
        return cancion;
    }
    
    public String delCancion(String id) {
        Conexion conn = new Conexion();

        String sql = "DELETE FROM cancion WHERE id_can= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }    
}
