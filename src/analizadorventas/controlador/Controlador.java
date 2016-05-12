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
    public static DefaultTableModel InsertarRegistros(String[] cabecera, List<Transaccion> lista){
        String[] tableData = new String[cabecera.length];
        DefaultTableModel dtm = new DefaultTableModel(cabecera,0);
        for (Transaccion t : lista) {
            tableData[0] = t.getNombreCliente();
            tableData[1] = t.getProductoComprado();
            tableData[2] = t.getPrecio()+"";
            tableData[3] = t.getFecha();
            tableData[4] = t.getCiudad();
            dtm.addRow(tableData);
                    }
        return dtm;
    }
    public static List<Transaccion> crearColeccionRegistros(File inFile){
        List<Transaccion> lista = new ArrayList<>();
        String[] data;
        CsvImporter reader = new CsvImporter(inFile, Charset.forName("UTF-8"));
        while ((data =reader.readNextLine()) != null){
            lista.add(new Transaccion(data[4], data[1],Integer.parseInt(data[2]), data[0],data[5]));
        }
        return lista;
    }
}
