/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iespuerto.pro.propietarioscasasmaven.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author José David González Díaz
 */
@Entity
@Table(name = "CASAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Casas.findAll", query = "SELECT c FROM Casas c")
    , @NamedQuery(name = "Casas.findByIdCasa", query = "SELECT c FROM Casas c WHERE c.idCasa = :idCasa")
    , @NamedQuery(name = "Casas.findByDireccion", query = "SELECT c FROM Casas c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Casas.findByMetros", query = "SELECT c FROM Casas c WHERE c.metros = :metros")
    , @NamedQuery(name = "Casas.findByAscensor", query = "SELECT c FROM Casas c WHERE c.ascensor = :ascensor")
    , @NamedQuery(name = "Casas.findByGaraje", query = "SELECT c FROM Casas c WHERE c.garaje = :garaje")
    , @NamedQuery(name = "Casas.findByPrecio", query = "SELECT c FROM Casas c WHERE c.precio = :precio")})
public class Casas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CASA")
    private Integer idCasa;
    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "METROS")
    private int metros;
    @Basic(optional = false)
    @Column(name = "ASCENSOR")
    private boolean ascensor;
    @Basic(optional = false)
    @Column(name = "GARAJE")
    private boolean garaje;
    @Basic(optional = false)
    @Column(name = "PRECIO")
    private int precio;
    @JoinTable(name = "PROPIETARIOS_CASAS", joinColumns = {
        @JoinColumn(name = "REF_ID_CASA", referencedColumnName = "ID_CASA")}, inverseJoinColumns = {
        @JoinColumn(name = "REF_ID_DNI_PROPIETARIO", referencedColumnName = "ID_DNI_PROPIETARIOS")})
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Propietarios> propietariosList;

    public Casas() {
    }

    public Casas(Integer idCasa) {
        this.idCasa = idCasa;
    }

    public Casas(Integer idCasa, String direccion, int metros, boolean ascensor, boolean garaje, int precio) {
        this.idCasa = idCasa;
        this.direccion = direccion;
        this.metros = metros;
        this.ascensor = ascensor;
        this.garaje = garaje;
        this.precio = precio;
    }

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
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

    public boolean getAscensor() {
        return ascensor;
    }

    public void setAscensor(boolean ascensor) {
        this.ascensor = ascensor;
    }

    public boolean getGaraje() {
        return garaje;
    }

    public void setGaraje(boolean garaje) {
        this.garaje = garaje;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Propietarios> getPropietariosList() {
        return propietariosList;
    }

    public void setPropietariosList(List<Propietarios> propietariosList) {
        this.propietariosList = propietariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCasa != null ? idCasa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Casas)) {
            return false;
        }
        Casas other = (Casas) object;
        if ((this.idCasa == null && other.idCasa != null) || (this.idCasa != null && !this.idCasa.equals(other.idCasa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iespuerto.pro.propietarioscasasmaven.modelo.Casas[ idCasa=" + idCasa + " ]";
    }
    
}
