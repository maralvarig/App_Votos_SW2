/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
@Table(name="elecciones")
public class Elecciones implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int idElecciones;
    
    @Column(name="Fecha")
    private String Fecha;
    
    @Column(name="Tipo")
    private String Tipo;
    
    @JoinColumn(name="Localidad_idLocalidad")
    @ManyToOne
    private Localidad Localidad_idLocalidad;

    public int getIdElecciones() {
        return idElecciones;
    }

    public void setIdElecciones(int idElecciones) {
        this.idElecciones = idElecciones;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public Localidad getLocalidad_idLocalidad() {
        return Localidad_idLocalidad;
    }

    public void setLocalidad_idLocalidad(Localidad Localidad_idLocalidad) {
        this.Localidad_idLocalidad = Localidad_idLocalidad;
    }
    
    
    
    
}
