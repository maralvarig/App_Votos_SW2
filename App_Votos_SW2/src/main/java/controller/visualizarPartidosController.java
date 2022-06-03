/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.PartidosFacadeLocal;
import EJB.RepresentantesFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Partidos;
import modelo.Representantes;

/**
 *
 * @author dnarc
 */
@Named
@ViewScoped
public class visualizarPartidosController implements Serializable{
    
    @Inject
    private buscadorEleccionesController busEleCon;
    
    private Elecciones eleccion;        
    private List<Partidos> listaPartidos;
    private Partidos partido;
    private List<Representantes> listaRepresentantes;
    
    @EJB
    private PartidosFacadeLocal partidosEJB;
    
    @EJB
    private RepresentantesFacadeLocal representanteEJB;
    
    @PostConstruct
    public void init(){
        partido = new Partidos();
        eleccion = busEleCon.getEleccion();
        
        listaPartidos = partidosEJB.encontrarPartidos(eleccion);
    }
    
    public void cargarRepresentantes(Partidos partido){
        listaRepresentantes = representanteEJB.encontrarRepresentantes(partido);
        System.out.println("TAM:"+listaRepresentantes.size());
    }

    public buscadorEleccionesController getBusEleCon() {
        return busEleCon;
    }

    public void setBusEleCon(buscadorEleccionesController busEleCon) {
        this.busEleCon = busEleCon;
    }

    public Elecciones getEleccion() {
        return eleccion;
    }

    public void setEleccion(Elecciones eleccion) {
        this.eleccion = eleccion;
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

    public List<Representantes> getListaRepresentantes() {
        return listaRepresentantes;
    }

    public void setListaRepresentantes(List<Representantes> listaRepresentantes) {
        this.listaRepresentantes = listaRepresentantes;
    }

    public RepresentantesFacadeLocal getRepresentanteEJB() {
        return representanteEJB;
    }

    public void setRepresentanteEJB(RepresentantesFacadeLocal representanteEJB) {
        this.representanteEJB = representanteEJB;
    }
    
    
    
}