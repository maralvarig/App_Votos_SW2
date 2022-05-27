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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dnarc
 */
@Entity
@Table(name="partidos")
public class Partidos implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPartidos;
    
    @Column(name="Nombre")
    private String Nombre;
    
    @JoinColumn(name="idElecciones")
    @ManyToOne
    private Elecciones Elecciones_idElecciones; 

    public int getIdPartidos() {
        return idPartidos;
    }

    public void setIdPartidos(int idPartidos) {
        this.idPartidos = idPartidos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Elecciones getElecciones_idElecciones() {
        return Elecciones_idElecciones;
    }

    public void setElecciones_idElecciones(Elecciones Elecciones_idElecciones) {
        this.Elecciones_idElecciones = Elecciones_idElecciones;
    }
    
}