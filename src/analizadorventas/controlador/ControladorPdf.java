/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.controlador;

import analizadorventas.modelo.Transaccion;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author root
 */

public class ControladorPdf {
    private static final String[] header={"Nombre","Producto","Precio","Fecha","Ciudad"};
        //Metodo que nos añade los campos de la lista a un archivo pdf 
        /**
          * @param lista 
          */   
    public static void CrearPdf(List<Transaccion> lista){    
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(new File("acercaDe.pdf")));
            documento.open();
            PdfPTable fila = new PdfPTable(5);
            fila.setHeaderRows(1);
            for (String cadena : header) {
                fila.addCell(cadena);
            }
            for (Transaccion transaccion : lista) {
                fila.setSpacingAfter(10);
                fila.addCell(transaccion.getNombreCliente());
                fila.addCell(transaccion.getProductoComprado());
                fila.addCell(transaccion.getPrecio()+"");
                fila.addCell(transaccion.getFecha());
                fila.addCell(transaccion.getCiudad());
            }
            documento.add(fila);
            documento.close();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(ControladorPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
