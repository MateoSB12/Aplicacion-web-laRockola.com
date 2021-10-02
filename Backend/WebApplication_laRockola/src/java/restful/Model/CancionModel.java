package restful.Model;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class CancionModel {
    
    private String id_can;
    private String nombre_can;
    private String id_alb;
    private String duracion_can;

    public CancionModel() {
    }

    public CancionModel(String id_can, String nombre_can, String id_alb, String duracion_can) {
        this.id_can = id_can;
        this.nombre_can = nombre_can;
        this.id_alb = id_alb;
        this.duracion_can = duracion_can;
    }

    public String getId_can() {
        return id_can;
    }

    public void setId_can(String id_can) {
        this.id_can = id_can;
    }

    public String getNombre_can() {
        return nombre_can;
    }

    public void setNombre_can(String nombre_can) {
        this.nombre_can = nombre_can;
    }

    public String getId_alb() {
        return id_alb;
    }

    public void setId_alb(String id_alb) {
        this.id_alb = id_alb;
    }

    public String getDuracion_can() {
        return duracion_can;
    }

    public void setDuracion_can(String duracion_can) {
        this.duracion_can = duracion_can;
    }
    
    
}
