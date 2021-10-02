
package restful.Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection con;
    //private static final String DB_DRIVER = "org.gjt.mm.mysql.Driver";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "localhost:3306";
    private static final String DB = "mi_rockola";
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DB +"?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "gisela98";

    public Conexion() {
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexión: "+ e.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }

    public void desconectar() {
        try {
            if (con != null) {
                con.close();
                System.out.println("La desconexion fue exitosa");
            }

        } catch (Exception excepcion) {
            System.out.println("Ha ocurrido un error al desconectar  " + excepcion.getMessage());
        }
    }

}
