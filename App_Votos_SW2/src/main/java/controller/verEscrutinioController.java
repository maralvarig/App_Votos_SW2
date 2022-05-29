/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EscrutinioFacadeLocal;
import EJB.PartidosFacadeLocal;
import EJB.RepresentantesFacadeLocal;
import EJB.VotoFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Escrutinio;
import modelo.Partidos;
import modelo.Representantes;

/**
 *
 * @author dnarc
 */
@Named
@ViewScoped
public class verEscrutinioController implements Serializable{
    
    @Inject
    private buscadorEleccionesController busEleCon;
    
    private String resultado;
    private List<Escrutinio> resultados;
    private Elecciones eleccion;        
    private List<Partidos> listaPartidos;
    private Partidos partido;
    private List<Representantes> listaRepresentantes;

    
    @EJB
    private PartidosFacadeLocal partidosEJB;
    
    @EJB
    private RepresentantesFacadeLocal representanteEJB;
    
    @EJB
    private VotoFacadeLocal votoEJB;
    
     @EJB
    private EscrutinioFacadeLocal escrutinioEJB;
    
    @PostConstruct
    public void init(){
        partido = new Partidos();
        eleccion = busEleCon.getEleccion();
        resultados = escrutinioEJB.obtenerResultado(eleccion);
        resultado = resultados.get(0).getResultados();
        listaPartidos = partidosEJB.encontrarPartidos(eleccion);
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

    public List<Escrutinio> getResultados() {
        return resultados;
    }

    public void setResultados(List<Escrutinio> resultados) {
        this.resultados = resultados;
    }

    public EscrutinioFacadeLocal getEscrutinioEJB() {
        return escrutinioEJB;
    }

    public void setEscrutinioEJB(EscrutinioFacadeLocal escrutinioEJB) {
        this.escrutinioEJB = escrutinioEJB;
    }
    
    public VotoFacadeLocal getVotoEJB() {
        return votoEJB;
    }

    public void setVotoEJB(VotoFacadeLocal votoEJB) {
        this.votoEJB = votoEJB;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}