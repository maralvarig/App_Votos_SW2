/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dnarc
 */
@Entity
@Table(name="voto")
public class Voto implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idVoto;

    @Column(name="Voto")
    private String Voto;
    
    @JoinColumn(name="idLocalidad")
    @OneToMany(cascade=CascadeType.PERSIST)
    private Localidad Localidad_idLocalidad;
    
    @JoinColumn(name="idElecciones")
    @OneToMany(cascade=CascadeType.PERSIST)
    private Elecciones Elecciones_idElecciones;

    public int getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public String getVoto() {
        return Voto;
    }

    public void setVoto(String Voto) {
        this.Voto = Voto;
    }

    public Localidad getLocalidad_idLocalidad() {
        return Localidad_idLocalidad;
    }

    public void setLocalidad_idLocalidad(Localidad Localidad_idLocalidad) {
        this.Localidad_idLocalidad = Localidad_idLocalidad;
    }

    public Elecciones getElecciones_idElecciones() {
        return Elecciones_idElecciones;
    }

    public void setElecciones_idElecciones(Elecciones Elecciones_idElecciones) {
        this.Elecciones_idElecciones = Elecciones_idElecciones;
    }
    
}
