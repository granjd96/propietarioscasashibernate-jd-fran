/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuerto.pro.propietarioscasasmaven.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author granj
 */
@Entity
@Table(name = "PROPIETARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Propietarios.findAll", query = "SELECT p FROM Propietarios p")
    , @NamedQuery(name = "Propietarios.findByIdDniPropietarios", query = "SELECT p FROM Propietarios p WHERE p.idDniPropietarios = :idDniPropietarios")
    , @NamedQuery(name = "Propietarios.findByNombre", query = "SELECT p FROM Propietarios p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Propietarios.findByApellidos", query = "SELECT p FROM Propietarios p WHERE p.apellidos = :apellidos")})
public class Propietarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DNI_PROPIETARIOS")
    private String idDniPropietarios;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @ManyToMany(mappedBy = "propietariosList")
    private List<Casas> casasList;

    public Propietarios() {
    }

    public Propietarios(String idDniPropietarios) {
        this.idDniPropietarios = idDniPropietarios;
    }

    public Propietarios(String idDniPropietarios, String nombre, String apellidos) {
        this.idDniPropietarios = idDniPropietarios;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getIdDniPropietarios() {
        return idDniPropietarios;
    }

    public void setIdDniPropietarios(String idDniPropietarios) {
        this.idDniPropietarios = idDniPropietarios;
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

    @XmlTransient
    public List<Casas> getCasasList() {
        return casasList;
    }

    public void setCasasList(List<Casas> casasList) {
        this.casasList = casasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDniPropietarios != null ? idDniPropietarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propietarios)) {
            return false;
        }
        Propietarios other = (Propietarios) object;
        if ((this.idDniPropietarios == null && other.idDniPropietarios != null) || (this.idDniPropietarios != null && !this.idDniPropietarios.equals(other.idDniPropietarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iespuerto.pro.propietarioscasasmaven.modelo.Propietarios[ idDniPropietarios=" + idDniPropietarios + " ]";
    }
    
}
