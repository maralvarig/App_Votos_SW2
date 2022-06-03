/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EleccionesFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Elecciones;

/**
 *
 * @author dnarc
 */
@Named
@SessionScoped
public class buscadorEleccionesController implements Serializable{
    
    private Elecciones eleccion;
    private List<Elecciones> listaElecciones;
    
    @EJB
    private EleccionesFacadeLocal eleccionEJB;
    
    
    
    @PostConstruct
    public void init(){
        eleccion = new Elecciones();
    }
    
    public void buscarElecciones(){
        listaElecciones = eleccionEJB.buscarElecciones(eleccion);
    }
    
    public String visualizarPartidos(){
        
        return "/privado/administrador/elecciones/visualizarPartidos.xhtml?faces-redirect=true";
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
    
    
}
