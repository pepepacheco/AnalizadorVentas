/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorventas.modelo;

import java.time.LocalDate;
import java.util.Objects;

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
        if (fecha.matches("\\d*-\\d*-\\d*")){
            ano=fecha.substring(0,fecha.indexOf('-'));
            if (ano.matches("\\d\\d"))
                ano = "20"+ano;
            mes=fecha.substring(fecha.indexOf('-')+1,fecha.lastIndexOf("-"));
            if (mes.matches("\\d"))
                mes = "0" + mes;
            dia = fecha.substring(fecha.lastIndexOf("-")+1,fecha.length());
            if (dia.matches("\\d"))
                dia = "0" + dia;
            this.fecha = LocalDate.parse(ano+"-"+mes+"-"+dia);
        }
        else{
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
        }
        this.ciudad = ciudad.trim();
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

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setProductoComprado(String productoComprado) {
        this.productoComprado = productoComprado;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setFecha(String fecha) {
        String dia;
        String mes;
        String ano;
        ano=fecha.substring(0,fecha.indexOf('-'));
        if (ano.matches("\\d\\d"))
            ano = "20"+ano;
        mes=fecha.substring(fecha.indexOf('-')+1,fecha.lastIndexOf("-"));
        if (mes.matches("\\d"))
            mes = "0" + mes;
        dia = fecha.substring(fecha.lastIndexOf("-")+1,fecha.length());
        if (dia.matches("\\d"))
            dia = "0" + dia;
        this.fecha = LocalDate.parse(ano+"-"+mes+"-"+dia);
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nombreCliente);
        hash = 59 * hash + Objects.hashCode(this.productoComprado);
        hash = 59 * hash + this.precio;
        hash = 59 * hash + Objects.hashCode(this.fecha);
        hash = 59 * hash + Objects.hashCode(this.ciudad);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaccion other = (Transaccion) obj;
        if (this.precio != other.precio) {
            return false;
        }
        if (!Objects.equals(this.nombreCliente, other.nombreCliente)) {
            return false;
        }
        if (!Objects.equals(this.productoComprado, other.productoComprado)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
