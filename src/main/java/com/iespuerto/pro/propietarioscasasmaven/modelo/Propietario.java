/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuerto.pro.propietarioscasasmaven.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tote
 */
@XmlRootElement(name = "propietario")
public class Propietario implements Domicilios<Casa, Propietario> {

    @XmlElementWrapper
    @XmlElement(name = "casa")
    private HashMap<String, Casa> casasMap;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "apellidos")
    private String apellidos;
    @XmlElement(name = "dni")
    private String dni;
    @XmlElement(name = "casas")
    private ArrayList<Casa> casas;
    
//la librería xml precisa de un constructor sin parámetros

    public Propietario() {
        this.casasMap = new HashMap<>();
        this.casas = new ArrayList<Casa>();
    }

    public Propietario(String nombre, String apellidos,
            String dni, Casa casa) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.casasMap = new HashMap<>();
        this.casas = new ArrayList<Casa>();
        casasMap.put(casa.getDireccion(), casa);
        casas.add(casa);
    }

    public String getListaPropiedades() {
        String propiedadesString = "Sin propiedad.";
        for (Map.Entry<String, Casa> o : casasMap.entrySet()) {
            Casa obj = o.getValue();
            propiedadesString = obj.toString() + "\n";
        }
        return propiedadesString;
    }

    @Override
    public void add(Casa o) {
        casasMap.put(o.getDireccion(), o);
        casas.add(o);
    }

    @Override
    public boolean escribir(Propietario obj) {
        boolean retorno = false;
        File f = new File(this.toString() + ".txt");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bf = new BufferedWriter(osw);
            try (PrintWriter pw = new PrintWriter(bf)) {
                pw.write("Nombre: " + obj.toString() + "\nPropiedades:\n" + obj.getListaPropiedades());
            }
        } catch (FileNotFoundException e) {
            
        }
        return retorno;
    }

   
    
    @Override
    public String toString() {
        return this.getClass()+" "+this.nombre + " " + this.apellidos;
    }

    public HashMap<String, Casa> getCasasMap() {
        return casasMap;
    }

    public void setCasasMap(HashMap<String, Casa> casasMap) {
        this.casasMap = casasMap;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public void setCasas(ArrayList<Casa> casas) {
        this.casas = casas;
    }
    
    

}
