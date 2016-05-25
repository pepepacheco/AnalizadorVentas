/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.controlador;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class CsvImporter {
    //Clase que cree para la implementacion de opencsv con mi codigo
    private CSVReader reader;
    private String[] currentLine;
    private int rowNumber = 0;
   //Constructor de la clase, que me crea la ruta y pasa la primera linea de la cabecera
    /**
     * @param inFile
     * @param charset 
     */
    public CsvImporter(File inFile,Charset charset) {
        if (inFile.getAbsolutePath().matches(".*\\.csv")){
            try {
                reader = new CSVReader(new BufferedReader(new FileReader(inFile)), ',');
                currentLine = reader.readNext();
            } catch (IOException ex) {
                Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Metodo que no da la siguiente linea del csv
    /**
     * @return currentLine
     */
    public String[] readNextLine(){
        if (reader.verifyReader()){
            try {
                currentLine = reader.readNext();
                rowNumber +=1;
                return currentLine;
            } catch (IOException ex) {
                Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
