/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author paveg
 */
public class Conexion {

    static Connection conexion;

    public static boolean conectar() {
        try {
            conexion
              = DriverManager.getConnection("jdbc:mysql://localhost:3308"
                            + "/northwind?"
                            + "user=root&password=Rec159432@");
            
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static void desconectar() {
        try {
            conexion.close();
        } catch (Exception ex) {
        }
    }

}
