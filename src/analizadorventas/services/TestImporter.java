/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.services;

import java.nio.charset.Charset;

/**
 *
 * @author root
 */
public class TestImporter {
    public static void main(String[] args) {
        CsvImporter in = new CsvImporter("in/SalesJan2009.csv", Charset.forName("UTF-8"));
        /* Modo de funcionamiento nº 1
        //Problema: El csv debe estar bien construido, si no te devolvera todos los espacios que tenga o caracteres extraños, etc
        String[] linea;
        linea = in.readNextLine();
        System.out.println(linea[4].trim() + linea[3] + linea[5]);
        */
        /* Modo de funcionamiento nº 2*/
        String valor;
        in.readNextLine();
        in.readNextLine();
        System.out.println(in.readValue(5) + " " + in.readValue(4));
        in.readNextLine();
        
    }
}
