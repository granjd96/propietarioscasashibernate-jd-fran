/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuerto.pro.propietarioscasasmaven.modelo;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tote
 */
@XmlRootElement(name = "casa")
public class Casa implements Domicilios<Propietario, Casa> {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "direccion")
    private String direccion;
    @XmlElement(name = "metros")
    private int metros;
    @XmlElement(name = "planta")
    private int planta;
    @XmlElement(name = "ascensor")
    private boolean ascensor;
    @XmlElement(name = "garaje")
    private boolean garaje;
    @XmlElement(name = "precio")
    private int precio;
    @XmlElementWrapper
    @XmlElement(name = "Propietario")
    private HashMap<String, Propietario> propietariosMap;

    public Casa() {
    } //necesitamos un constructor por defecto para marshall

    public Casa(int id, String direccion, int metros, int planta, boolean ascensor,
            boolean garaje, int precio) {
        this.id = id;
        this.direccion = direccion;
        this.metros = metros;
        this.planta = planta;
        this.ascensor = ascensor;
        this.garaje = garaje;
        this.precio = precio;
        propietariosMap = new HashMap<>();
    }

    @Override
    public void add(Propietario o) {
        propietariosMap.put(o.toString(), o);
    }

    public String mostrarListadoPropietarios() {
        String propietariosString = null;
        if (propietariosMap.isEmpty()) {
            propietariosString = "Sin propietarios";
        } else {
            for (Map.Entry<String, Propietario> o : propietariosMap.entrySet()) {
                Propietario obj = o.getValue();
                propietariosString = obj.toString() + "\n";
            }
        }
        return propietariosString;
    }

    @Override
    public String toString() {
        return this.getClass()+" "+this.direccion;
    }

    @Override
    public boolean escribir(Casa obj) {
        boolean retorno = false;
        File f = new File(this.toString() + ".xml");
        try {

            String texto = obj.mostrarListadoPropietarios();
            FileOutputStream fos = new FileOutputStream(f);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            try (PrintWriter pw = new PrintWriter(bw)) {
                pw.write(texto);
            }

        } catch (IOException ex) {

        }

        return retorno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getMetros() {
        return metros;
    }

    public void setMetros(int metros) {
        this.metros = metros;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public boolean isAscensor() {
        return ascensor;
    }

    public void setAscensor(boolean ascensor) {
        this.ascensor = ascensor;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public HashMap<String, Propietario> getPropietariosMap() {
        return propietariosMap;
    }

    public void setPropietariosMap(HashMap<String, Propietario> propietariosMap) {
        this.propietariosMap = propietariosMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGaraje() {
        return garaje;
    }

    public void setGaraje(boolean garaje) {
        this.garaje = garaje;
    }

    
    
    
}
