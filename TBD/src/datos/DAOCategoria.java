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
import modelos.Categoria;

/**
 * DataAccessObject
 *
 * @author paveg
 */
public class DAOCategoria {

    public ArrayList<Categoria> consultarTodos() throws Exception {
        try {
            if (Conexion.conectar()) {
                String sql = "SELECT CategoryId, Categoryname"
                        + " FROM categories"
                        + " ORDER BY 2;";
                
                Statement consulta = Conexion.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Categoria> lista=new ArrayList<>();
                while (rsLista.next()) {
                    Categoria objCategoria=new Categoria();
                    objCategoria.setIdCategoria(rsLista.getInt("CategoryId"));
                    objCategoria.setCategoria(rsLista.getString("Categoryname"));
                    lista.add(objCategoria);
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
