/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dnarc
 */
@Entity
@Table(name="personas")
public class Personas implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPersona;
    
    @Column(name="DNI")
    private String DNI;
    
    @Column(name="Nombre")
    private String Nombre;
    
    @Column(name="Primer_Apellido")
    private String Primer_Apellido;
    
    @Column(name="Segundo_Apellido")
    private String Segundo_Apellido;
    
    @Column(name="Pasaporte")
    private String Pasaporte;
    
    @JoinColumn(name="idLocalidad")
    @ManyToOne
    private Localidad idLocalidad;

    @ManyToMany
    @JoinTable(
    name = "confirmacionvoto", 
    joinColumns = @JoinColumn(name = "Personas_idPersona"), 
    inverseJoinColumns = @JoinColumn(name = "Elecciones_idElecciones"))
    ArrayList<Elecciones> Elecciones;
    
    public void addEleccion(Elecciones elecciones){
        if(this.Elecciones == null){
            this.Elecciones = new ArrayList<Elecciones>();
        }
        this.Elecciones.add(elecciones);
    }
    
    public int getIdPersona() {
        return idPersona;
    }

    public Localidad getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Localidad idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public ArrayList<Elecciones> getElecciones() {
        return Elecciones;
    }

    public void setElecciones(ArrayList<Elecciones> Elecciones) {
        this.Elecciones = Elecciones;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public String getPasaporte() {
        return Pasaporte;
    }

    public void setPasaporte(String Pasaporte) {
        this.Pasaporte = Pasaporte;
    }

   

}
