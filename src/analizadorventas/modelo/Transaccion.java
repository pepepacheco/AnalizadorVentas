/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.modelo;

import java.time.LocalDate;

/**
 *
 * @author root
 */
public class Transaccion {
    private String nombreCliente;
    private String productoComprado;
    private int precio;
    private LocalDate fecha;
    private String ciudad;

    public Transaccion(String nombre, String producto,int precio, String fecha, String ciudad) {
        String dia;
        String mes;
        String ano;
        if (nombre.matches(".* .*"))
            this.nombreCliente = nombre.trim().substring(0,nombre.indexOf(" "));
        else
            this.nombreCliente = nombre.trim();
        this.productoComprado = producto.trim();
        //System.out.println(this.nombreCliente + " " +  precio);
        this.precio = precio;
        mes=fecha.substring(0,fecha.indexOf('/'));
        if (mes.matches("\\d"))
            mes = "0" + mes;
        //System.out.println(dia);
        dia=fecha.substring(fecha.indexOf('/')+1,fecha.lastIndexOf("/"));
        if (dia.matches("\\d"))
            dia = "0" + dia;
        //System.out.println(mes);
        ano = fecha.substring(fecha.lastIndexOf("/")+1,fecha.indexOf(" "));
        if (ano.matches("\\d\\d"))
            ano = "20"+ano;
        //System.out.println(ano);
        this.fecha = LocalDate.parse(ano+"-"+mes+"-"+dia);
        this.ciudad = ciudad;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getProductoComprado() {
        return productoComprado;
    }

    public int getPrecio() {
        return precio;
    }

    public String getFecha() {
        return fecha.toString();
    }

    public String getCiudad() {
        return ciudad;
    }
    
    
    
}
