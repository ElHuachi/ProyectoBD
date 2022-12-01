/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import modelos.Proveedor;

/**
 * DataAccessObject
 *
 * @author paveg
 */
public class DAOProveedor {

    public ArrayList<Proveedor> consultarTodos() throws Exception {
        try {
            if (Conexion.conectar()) {
                String sql = "SELECT SupplierId, CompanyName"
                        + " FROM Suppliers"
                        + " Order By CompanyName;";
                
                Statement consulta = Conexion.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Proveedor> lista=new ArrayList<>();
                while (rsLista.next()) {
                    Proveedor objP=new Proveedor();
                    objP.setIdProveedor(rsLista.getInt("SupplierId"));
                    objP.setProveedor(rsLista.getString("CompanyName"));
                    lista.add(objP);
                }
                return lista;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            Conexion.desconectar();
        }
    }

}
