/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author dnarc
 */
@Entity
@Table(name="Localidad")
public class Localidad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idLocalidad;
    
    @Column(name="Pais")
    private String Pais;
    
    @Column(name="Comunidad_Autonoma")
    private String Comunidad_Autonoma;
    
    @Column(name="Provincia")
    private String Provincia;
    
    @Column(name="Municipio")
    private String Municipio;
    
    @Column(name="Codigo_Postal")
    private String Codigo_Postal;

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getComunidad_Autonoma() {
        return Comunidad_Autonoma;
    }

    public void setComunidad_Autonoma(String Comunidad_Autonoma) {
        this.Comunidad_Autonoma = Comunidad_Autonoma;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String Codigo_Postal) {
        this.Codigo_Postal = Codigo_Postal;
    }
    
    
}
