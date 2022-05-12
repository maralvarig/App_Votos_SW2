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
@Table(name="Escrutinio")
public class Escrutinio implements Serializable{
     @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idEscrutinio;
    
    @Column(name="Resultados")
    private String Resultados;
    
    @JoinColumn(name="Elecciones_idElecciones")
    @ManyToOne
    private Elecciones Elecciones_idElecciones;

    @JoinColumn(name="Elecciones_Localidad_idLocalidad")
    @ManyToOne
    private Localidad Elecciones_Localidad_idLocalidad;

    public int getIdEscrutinio() {
        return idEscrutinio;
    }

    public void setIdEscrutinio(int idEscrutinio) {
        this.idEscrutinio = idEscrutinio;
    }

    public String getResultados() {
        return Resultados;
    }

    public void setResultados(String Resultados) {
        this.Resultados = Resultados;
    }

    public Elecciones getElecciones_idElecciones() {
        return Elecciones_idElecciones;
    }

    public void setElecciones_idElecciones(Elecciones Elecciones_idElecciones) {
        this.Elecciones_idElecciones = Elecciones_idElecciones;
    }

    public Localidad getElecciones_Localidad_idLocalidad() {
        return Elecciones_Localidad_idLocalidad;
    }

    public void setElecciones_Localidad_idLocalidad(Localidad Elecciones_Localidad_idLocalidad) {
        this.Elecciones_Localidad_idLocalidad = Elecciones_Localidad_idLocalidad;
    }
    
}
