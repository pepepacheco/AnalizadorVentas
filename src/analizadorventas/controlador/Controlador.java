/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.controlador;

import analizadorventas.modelo.Transaccion;
import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class Controlador {
    private static ModeloTabla dtm = new ModeloTabla();
    
    private static boolean creado =false;
    public static DefaultTableModel InsertarRegistros(String[] cabecera, List<Transaccion> lista){
        //System.out.println("Entra a la funcion");
        dtm.setRowCount(0);
        String[] tableData = new String[cabecera.length];
        //System.out.println("crea tableData");
            if (creado == false){
            dtm.setColumnIdentifiers(cabecera);
            creado = true;
            }
        //System.out.println("crea el tableModel");
        for (int i = 0;i<lista.size();i++) {
            //System.out.println("Entra en el bucle");
            tableData[0] = lista.get(i).getNombreCliente();
            tableData[1] = lista.get(i).getProductoComprado();
            tableData[2] = lista.get(i).getPrecio()+"";
            tableData[3] = lista.get(i).getFecha();
            tableData[4] = lista.get(i).getCiudad();
            dtm.addRow(tableData);
          //  System.out.println("Vuelta de ciclo");
                    }
        //System.out.println("Sale de la funcion");
        return dtm;
    }
    public static List<Transaccion> crearColeccionRegistros(File inFile){
        List<Transaccion> lista = new ArrayList<>();
        String[] data;
        CsvImporter reader = new CsvImporter(inFile, Charset.forName("UTF-8"));
        while ((data =reader.readNextLine()) != null){
            lista.add(new Transaccion(data[4], data[1],Integer.parseInt(data[2]), data[0],data[5]));
        }
        ControladorDb.crearTablaTransaccion(ControladorDb.getConexiondb());
        return lista;
    }
    public static DefaultTableModel nuevoRegistroVacio(String[] cabecera,List<Transaccion> lista){
        lista.add(new Transaccion());
        return InsertarRegistros(cabecera, lista);
    }
     public static DefaultTableModel borrarRegistro(String[] cabecera,List<Transaccion> lista, int fila){
        lista.remove(fila);
        return InsertarRegistros(cabecera, lista);
    }
}
