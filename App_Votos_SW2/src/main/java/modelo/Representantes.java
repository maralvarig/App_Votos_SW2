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
@Table(name="representantes")
public class Representantes implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idRepresentantes;

    @Column(name="Nombre")
    private String Nombre;
    
    @Column(name="Primer_Apellido")
    private String Primer_Apellido;
    
    @Column(name="Segundo_Apellido")
    private String Segundo_Apellido;
    
    @JoinColumn(name="idPartidos")
    @OneToMany(cascade=CascadeType.PERSIST)
    private Partidos Partidos_idPartidos;

    public int getIdRepresentantes() {
        return idRepresentantes;
    }

    public void setIdRepresentantes(int idRepresentantes) {
        this.idRepresentantes = idRepresentantes;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPrimer_Apellido() {
        return Primer_Apellido;
    }

    public void setPrimer_Apellido(String Primer_Apellido) {
        this.Primer_Apellido = Primer_Apellido;
    }

    public String getSegundo_Apellido() {
        return Segundo_Apellido;
    }

    public void setSegundo_Apellido(String Segundo_Apellido) {
        this.Segundo_Apellido = Segundo_Apellido;
    }

    public Partidos getPartidos_idPartidos() {
        return Partidos_idPartidos;
    }

    public void setPartidos_idPartidos(Partidos Partidos_idPartidos) {
        this.Partidos_idPartidos = Partidos_idPartidos;
    }
    
}
