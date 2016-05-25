/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.controlador;

import analizadorventas.modelo.Transaccion;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author root
 */
public class Controlador {
    private static ModeloTabla dtm = new ModeloTabla();
    
    private static boolean creado =false;
    //funcion que, dandole la cabecera de la tabla y la lista de registro, nos devuelve un modelo
    //Para usar en nuestro JTable
    /**
     * @param cabecera
     * @param lista
     * @return DefaultTableModel
     */
    public static DefaultTableModel InsertarRegistros(String[] cabecera, List<Transaccion> lista){
        dtm.setRowCount(0);
        String[] tableData = new String[cabecera.length];
        //System.out.println("crea tableData");
            if (creado == false){
            dtm.setColumnIdentifiers(cabecera);
            creado = true;
            }
        for (int i = 0;i<lista.size();i++) {
            tableData[0] = lista.get(i).getNombreCliente();
            tableData[1] = lista.get(i).getProductoComprado();
            tableData[2] = lista.get(i).getPrecio()+"";
            tableData[3] = lista.get(i).getFecha();
            tableData[4] = lista.get(i).getCiudad();
            dtm.addRow(tableData);
                    }
        return dtm;
    }
    //Metodo, que crea la lista de transacciones dandole el fichero
    /**
     * @param inFile fichero
     * @return List lista transaccion
     */
    public static List<Transaccion> crearColeccionRegistros(File inFile){
        List<Transaccion> lista = new ArrayList<>();
        String[] data;
        CsvImporter reader = new CsvImporter(inFile, Charset.forName("UTF-8"));
        while ((data =reader.readNextLine()) != null){
            lista.add(new Transaccion(data[4], data[1],Integer.parseInt(data[2]), data[0],data[5]));
        }
        ControladorDb.crearTablaTransaccion(ControladorDb.getConexiondb());
        ControladorDb.primeraInsercion(ControladorDb.getConexiondb(), lista);
        return lista;
    }
    //Funcion que crea un registro vacio al final
    /**
     * @param cabecera
     * @param lista
     * @return DefaultTableModel 
     */
    public static DefaultTableModel nuevoRegistroVacio(String[] cabecera,List<Transaccion> lista){
        ControladorDb.insertarFilaVacia(ControladorDb.getConexiondb());
        lista.add(new Transaccion());
        return InsertarRegistros(cabecera, lista);
    }
    //Metodo que borra el registro que le digamos de la tabla
    /**
     * @param cabecera
     * @param lista
     * @param fila
     * @return DefaultTableModel
     */
     public static DefaultTableModel borrarRegistro(String[] cabecera,List<Transaccion> lista, int fila){
        ControladorDb.borrarRegistro(ControladorDb.getConexiondb(), fila+1);
        lista.remove(fila);
        return InsertarRegistros(cabecera, lista);
    }
}