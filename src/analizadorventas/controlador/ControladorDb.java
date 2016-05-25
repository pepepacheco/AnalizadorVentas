/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.controlador;

import analizadorventas.modelo.Transaccion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;


/**
 *
 * @author root
 */
public class ControladorDb {
    private static Connection conexiondb =null; 
    private static Statement sentencia;
    private ControladorDb(){};
    //Metodo que, siguiendo el patron Singleton crea una conexion unica, si esta no existe
    /**
     * @return Connection
     */
    public static Connection getConexiondb(){
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        if (conexiondb == null){
            try {
                ResourceBundle rb = ResourceBundle.getBundle("sqlite");
                String url = rb.getString("url");
                String driver = rb.getString("driver");
                Class.forName(driver);
                SQLiteConfig config = new SQLiteConfig();
                conexiondb= DriverManager.getConnection(url,config.toProperties());
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return conexiondb;
    }
    //Metodo que actualiza el registro que le especifiquemos de la base de datos
    /**
     * @param con
     * @param t Transaccion
     * @param posicionLista
     * @return int
     */
    public static int actualizarRegistro(Connection con, Transaccion t, int posicionLista){
        int resultado = 0;
        try {
            PreparedStatement s;
            String sql = "UPDATE transaccion SET nombre=?,precio=?,producto=?,fecha=?,ciudad=?"
                    + "WHERE id=?";
            s = con.prepareStatement(sql);
            s.setString(1, t.getNombreCliente());
            s.setString(2, t.getPrecio()+"");
            s.setString(3, t.getProductoComprado());
            s.setString(4, t.getFecha());
            s.setString(5, t.getCiudad());
            s.setString(6, posicionLista+"");
            resultado = s.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    //Metodo que borra el registro que le especifiquemos de la base de datos
    /**
     * @param con
     * @param index
     * @return int
     */
    public static int borrarRegistro(Connection con, int index){
        int resultado=0;
        try {
            PreparedStatement s;
            String sql = "DELETE FROM transaccion WHERE id=?";
            s = con.prepareStatement(sql);
            s.setString(1, index+"");
            resultado = s.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
        
    }
    //Metodo que hace la primera insercion de datos en la tabla y la rellena
    /**
     * @param con
     * @param lista 
     */
    public static void primeraInsercion(Connection con, List<Transaccion> lista){
        for (Transaccion t : lista) {
            try {
                String sql = "INSERT INTO transaccion VALUES"
                        + "(null,"+"'"+t.getNombreCliente()+"',"+t.getPrecio()+",'"+t.getProductoComprado()+"','"+t.getFecha()+"','"+t.getCiudad()+"')";
                sentencia = con.createStatement();
                sentencia.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //Metodo que nos devuelve el numero de registros de la tabla, lo usamos para
    // comprobar que la tabla no esta vacia
    /**
     * @param con
     * @return int
     */
    public static int verNumeroRegistros(Connection con){
        ResultSet resultado = null;
        int nRegistros=0;
        try {
            sentencia = con.createStatement();
            String sql="Select COUNT(id) from transaccion";
            resultado = sentencia.executeQuery(sql);
            nRegistros = Integer.parseInt(resultado.getString(1));
        } catch (SQLException ex) {
            return 0;
        }
        finally{
            try {
                sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nRegistros;
    }
    //Metodo que usamos para cargar la lista de objetos de la base de datos
    /**
     * @param con
     * @return List
     */
    public static List<Transaccion> devolverLista(Connection con){
        List <Transaccion> lista = new ArrayList<>();
        try {
            ResultSet resultado = null;
            sentencia = con.createStatement();
            String sql = "Select * from transaccion";
            resultado = sentencia.executeQuery(sql);
            while (resultado.next()){
                lista.add(new Transaccion(resultado.getString(2), resultado.getString(4), Integer.parseInt(resultado.getString(3)), resultado.getString(5), resultado.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
        
    }
    //Metodo que usamos para la creacion de una fila vacia en la base de datos
    /**
     * @param con 
     */
    public static void insertarFilaVacia(Connection con){
        LocalDate ahora = LocalDate.now();
        String texto = ahora.toString();
        System.out.println(texto);
        try {
            String sql = "INSERT INTO transaccion VALUES"
                    + "(null,null,null,null,'"+texto+"',null)";
            sentencia = con.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Metodo que crea la tabla
    /**
     * @param con 
     */
    public static void crearTablaTransaccion(Connection con){
        String sql = "CREATE TABLE IF NOT EXISTS transaccion("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT,"
                + "precio INTEGER,"
                + "producto TEXT,"
                + "fecha TEXT,"
                + "ciudad TEXT)";
        try {
            sentencia=con.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                sentencia.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Listener que cierra la conexion a la BD al cerrar el programa
    static class ShutdownHook extends Thread{

        @Override
        public void run() {
            if (conexiondb!=null){
                try {
                    conexiondb.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorDb.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
            
    }
}