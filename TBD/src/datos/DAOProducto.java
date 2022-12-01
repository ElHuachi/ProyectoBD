package datos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Producto;

/**
 * DataAccessObject
 *
 * @author paveg
 */
public class DAOProducto {

    //Operaciones
    public int agregar(Producto objProducto) throws Exception {
         try {
            if (Conexion.conectar()) {
                String sql = "INSERT INTO products" +
                            " VALUES(default,?,?,?,?,?,?,?,?,?)";
                PreparedStatement sentencia = Conexion.conexion.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                sentencia.setString(1, objProducto.getNombre());
                sentencia.setInt(2, objProducto.getIdProveedor());
                sentencia.setInt(3, objProducto.getIdCategoria());
                sentencia.setString(4, objProducto.getCantidadXUnidad());
                sentencia.setDouble(5, objProducto.getPrecio());
                sentencia.setInt(6, objProducto.getExistencia());
                sentencia.setInt(7, objProducto.getNivelReorden());
                sentencia.setInt(8, objProducto.getUnidadesEnOrden());
                sentencia.setInt(9, objProducto.getDescontinuado());
                
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

    public boolean editar(Producto objProducto) throws Exception {
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
                
                //Sentencia concatenada puede causar fallos de seguridad ante 
                //ataque de inyección SQL al menos con parámetros string
                /*String sql = "DELETE FROM products"
                        + " WHERE ProductId= " +id;
                
                Statement sentencia = Conexion.conexion.createStatement();
                
                if(sentencia.executeUpdate(sql)>0){
                    return true;
                }else{
                    return false;
                }
                */


                //Versión parametrizada de la sentencia sql que evita el fallo
                //de inyección SQL
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

    public ArrayList<Producto> consultarTodos() throws Exception {
        try {
            if (Conexion.conectar()) {
                String sql = "SELECT p.ProductID Clave, p.ProductName,"
                        + "    s.CompanyName, c.CategoryName,"
                        + "    p.QuantityPerUnit, p.UnitPrice,"
                        + "    UnitsInStock, UnitsOnOrder,"
                        + "    ReorderLevel, Discontinued"
                        + " FROM products p"
                        + " JOIN Suppliers s ON p.SupplierID=s.SupplierID"
                        + " JOIN Categories c ON p.CategoryID=c.CategoryID;";
                
                Statement consulta = Conexion.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                ArrayList<Producto> listaProductos=new ArrayList<>();
                while (rsLista.next()) {
                    Producto objP=new Producto(
                            rsLista.getInt("Clave"),
                            rsLista.getString("ProductName"),
                            rsLista.getString("CompanyName"),
                            rsLista.getString("CategoryName"),
                            rsLista.getDouble("UnitPrice"),
                            rsLista.getInt("UnitsInStock"),
                            rsLista.getInt("Discontinued"));
                    listaProductos.add(objP);
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

    public ArrayList<Producto> consultarActivos() {
        return null;
    }

    public Producto consultarUno(int id) throws Exception {
        try {
            if (Conexion.conectar()) {
                String sql = "SELECT p.ProductID, p.ProductName,"
                        + "    p.SupplierId, p.CategoryId,"
                        + "    p.QuantityPerUnit, p.UnitPrice,"
                        + "    UnitsInStock, UnitsOnOrder,"
                        + "    ReorderLevel, Discontinued"
                        + " FROM products p"
                        + " WHERE p.ProductId=" + id;
                Statement consulta = Conexion.conexion.createStatement();
                ResultSet rsLista = consulta.executeQuery(sql);
                
                if (rsLista.next()) {
                    return new Producto(
                            rsLista.getInt("ProductID"),
                            rsLista.getString("ProductName"),
                            rsLista.getInt("SupplierId"),
                            rsLista.getInt("CategoryId"),
                            rsLista.getString("QuantityPerUnit"),
                            rsLista.getDouble("UnitPrice"),
                            rsLista.getInt("UnitsInStock"),
                            rsLista.getInt("UnitsOnOrder"),
                            rsLista.getInt("ReorderLevel"),
                            rsLista.getInt("Discontinued"));
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
    

}
