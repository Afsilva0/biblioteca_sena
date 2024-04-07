package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private String base = "tienda";
    private String user = "root";
    private String password = "";
    // Asegúrate de incluir la zona horaria para evitar problemas de conexión
    private String url = "jdbc:mysql://localhost:3306/" + base + "?serverTimezone=UTC";

    private Connection con = null;

    public Connection getConexion() {
        try {
            // Actualiza aquí el nombre de la clase del controlador
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            System.err.println("Conectado");
        } catch (SQLException e) {
            System.err.println("Error " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
