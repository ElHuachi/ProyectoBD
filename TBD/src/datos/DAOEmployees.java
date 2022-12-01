package datos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.Employees;
import tbd.FrmMenu;
import tbd.FrnInicio;

public class DAOEmployees {
    
     public Employees Login(String User, String Pass) throws Exception {
        try {
            FrnInicio objF = new FrnInicio();
            if (Conexion.conectar()) {
//                String sql = "SELECT Usuario, Pass from Employees WHERE Usuario=? and Pass=sha1(?)";
                PreparedStatement ps = Conexion.conexion.prepareStatement("SELECT Usuario, Pass FROM Employees WHERE Usuario=? and Pass=sha1(?)");
                ps.setString(1, User);
                ps.setString(2, Pass);
                ps.execute();
//                Statement consulta = Conexion.conexion.createStatement();
                ResultSet rsLista = ps.executeQuery();
                
                if (rsLista.next()) {
                    JOptionPane.showMessageDialog(objF, "Bienvenido: " + User);
                    objF.setVisible(false);
                    new FrmMenu().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(objF, "Error de inicio");
                     objF.setVisible(true);
                }
                return null;
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            Conexion.desconectar();
        }
    }
     
     public int agregar(Employees objE) throws Exception {
         try {
            if (Conexion.conectar()) {
                String sql = "INSERT INTO Employees" +
                            " VALUES(default,?,?,?,?,?,?,?,?,?)";
                PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setInt(1, objE.getId());
                sentencia.setString(2, objE.getFirsttname());
                sentencia.setString(3, objE.getLastname());
                sentencia.setString(4, objE.getTitle());
                sentencia.setString(5, objE.getTitle());
                
                sentencia.executeUpdate();
                ResultSet rsClaves=sentencia.getGeneratedKeys();
                
                if (rsClaves.next()) {
                    return rsClaves.getInt(1);
                } else {
                    return 0;
                }
                
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1451){
                throw new Exception("Error al intentar añadir este producto a una categoría o proveedor que no existen");
            }
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            Conexion.desconectar();
        }
    }

    public boolean editar(Employees objProducto) throws Exception {
        try {
            if (Conexion.conectar()) {
                String sql = "{ call paProductoActualizar(?,?,?,?,?,?,?,?,?,?)}";
                CallableStatement sentencia = Conexion.conexion.prepareCall(sql);
                sentencia.setInt(1, objProducto.getId());
                sentencia.setString(2, objProducto.getNombre());
                sentencia.setInt(3, objProducto.getIdProveedor());
                sentencia.setInt(4, objProducto.getIdCategoria());
                sentencia.setString(5, objProducto.getCantidadXUnidad());
                sentencia.setDouble(6, objProducto.getPrecio());
                sentencia.setInt(7, objProducto.getExistencia());
                sentencia.setInt(8, objProducto.getNivelReorden());
                sentencia.setInt(9, objProducto.getUnidadesEnOrden());
                sentencia.setInt(10, objProducto.getDescontinuado());
                
                int filasAfectadas=sentencia.executeUpdate();
                return filasAfectadas>0;                
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1451){
                throw new Exception("Error al intentar añadir este producto a una categoría o proveedor que no existen");
            }
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            Conexion.desconectar();
        }
    }

    public boolean eliminar(int id) throws Exception{
       try {
            if (Conexion.conectar()) {
                String sql = "DELETE FROM products"
                        + " WHERE ProductId = ?";
                PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql);
                sentencia.setInt(1, id);
                
                if(sentencia.executeUpdate()>0){
                    return true;
                }else{
                    return false;
                }
                
            } else {
                throw new Exception("No se ha podido conectar con el servidor");
            }
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1451){
                throw new Exception("No se puede eliminar un producto que tiene historial de ventas");
            }
            throw new Exception("No se ha podido realizar la operación");
        } finally {
            Conexion.desconectar();
        }
    }

    public ArrayList<Employees> consultarTodos() throws Exception {
        try {
            if (Conexion.conectar()) {
                String sql = "SELECT e.EmployeeID Clave, concat(e.FisrtName, ' ', e.LastName) Nombre,"
                        + "    e.Title Puesto, e.Usuario Usuario,"
                        + "    e.Pass Contraseña "
                        + " FROM Employees e";
                
                Statement consulta = Conexion.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Employees> listaEmpleados=new ArrayList<>();
                while (rsLista.next()) {
                    Employees objE=new Employees();
                            rsLista.getInt("Clave"),
                            rsLista.getString("Nombre"),
                            rsLista.getString("Puesto"),
                            rsLista.getString("Usuario"),
                            rsLista.getString("Contraseña"),
                    listaProductos.add(objE);
                }
                return listaProductos;
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
