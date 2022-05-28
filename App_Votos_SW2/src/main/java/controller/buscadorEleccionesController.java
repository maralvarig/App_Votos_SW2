/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EleccionesFacadeLocal;
import EJB.PartidosFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Partidos;

/**
 *
 * @author dnarc
 */
@Named
@SessionScoped
public class buscadorEleccionesController implements Serializable{
    
    private Elecciones eleccion;
    private List<Elecciones> listaElecciones;
    private List<Partidos> listaPartidos;
    private Partidos partido;
    
    @EJB
    private PartidosFacadeLocal partidosEJB;
    
    @EJB
    private EleccionesFacadeLocal eleccionEJB;
    
    
    
    @PostConstruct
    public void init(){
        eleccion = new Elecciones();
        partido = new Partidos();
    }
    
    public void buscarElecciones(){
        listaElecciones = eleccionEJB.buscarElecciones(eleccion);
    }
    
    public String visualizarPartidos(Elecciones eleccion){
        this.eleccion = eleccion;
        return "crearEleccion.xhtml?faces-redirect=true";
    }
    
    public void generarEscrutinio(){
        
    }

    public Elecciones getEleccion() {
        return eleccion;
    }

    public void setEleccion(Elecciones eleccion) {
        this.eleccion = eleccion;
    }

    public List<Elecciones> getListaElecciones() {
        return listaElecciones;
    }

    public void setListaElecciones(List<Elecciones> listaElecciones) {
        this.listaElecciones = listaElecciones;
    }

    public EleccionesFacadeLocal getEleccionEJB() {
        return eleccionEJB;
    }

    public void setEleccionEJB(EleccionesFacadeLocal eleccionEJB) {
        this.eleccionEJB = eleccionEJB;
    }

    public List<Partidos> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partidos> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public Partidos getPartido() {
        return partido;
    }

    public void setPartido(Partidos partido) {
        this.partido = partido;
    }

    public PartidosFacadeLocal getPartidosEJB() {
        return partidosEJB;
    }

    public void setPartidosEJB(PartidosFacadeLocal partidosEJB) {
        this.partidosEJB = partidosEJB;
    }
}
