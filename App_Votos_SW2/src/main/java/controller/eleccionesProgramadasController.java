/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EleccionesFacadeLocal;
import EJB.PartidosFacadeLocal;
import EJB.PersonasFacadeLocal;
import EJB.VotoFacadeLocal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Partidos;
import modelo.Personas;
import modelo.Voto;


/**
 *
 * @author dnarc
 */

@Named
@SessionScoped
public class eleccionesProgramadasController implements Serializable{
    
    private String partido;
    
    private String DNI;
    
    private Voto voto;
    
    private boolean seguro;
    
    private List<Partidos> listaPartidos;
    
    private List<Elecciones> listaElecciones;
    
    private Elecciones eleccion;
    
    private Partidos part;
    
    @EJB
    private PersonasFacadeLocal personaEJB;
    
    @EJB
    private EleccionesFacadeLocal eleccionesEJB;
    
    @EJB
    private VotoFacadeLocal votoEJB;
    
    @EJB
    private PartidosFacadeLocal partidoEJB;
    
    
    

    
    @PostConstruct
    public void init(){
        part = new Partidos();
        voto = new Voto();

        eleccion = new Elecciones();
        //TO DO
        listaElecciones = eleccionesEJB.findAll();
        listaPartidos = partidoEJB.findAll();
        
    }
    
    //Metodo que lleva a la pagina de elecciones anteriores
    public String irVotar(Elecciones eleccion){
        this.eleccion=eleccion;
        return "votar.xhtml?faces-redirect=true";
    }
    
    public boolean puedoVotar(Elecciones eleccionTemp){
        String[] fechaEleccion = eleccionTemp.getFecha().split("/");
        if(LocalDateTime.now().getYear()!=Integer.parseInt(fechaEleccion[2]))
            return true;
        if(LocalDateTime.now().getMonthValue()!=Integer.parseInt(fechaEleccion[1]))
            return true;
        if(LocalDateTime.now().getDayOfMonth()!=Integer.parseInt(fechaEleccion[0]))
            return true;

        return false;
    }
        
    public String visualizarPartidos(Elecciones eleccion) {
        this.eleccion = eleccion;
        return "votar.xhtml?faces-redirect=true";
    }
    
   
    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }

    public List<Partidos> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partidos> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public PersonasFacadeLocal getPersonaEJB() {
        return personaEJB;
    }

    public void setPersonaEJB(PersonasFacadeLocal personaEJB) {
        this.personaEJB = personaEJB;
    }

    public EleccionesFacadeLocal getEleccionesEJB() {
        return eleccionesEJB;
    }

    public void setEleccionesEJB(EleccionesFacadeLocal eleccionesEJB) {
        this.eleccionesEJB = eleccionesEJB;
    }

    public VotoFacadeLocal getVotoEJB() {
        return votoEJB;
    }

    public void setVotoEJB(VotoFacadeLocal votoEJB) {
        this.votoEJB = votoEJB;
    }

    public PartidosFacadeLocal getPartidoEJB() {
        return partidoEJB;
    }

    public void setPartidoEJB(PartidosFacadeLocal partidoEJB) {
        this.partidoEJB = partidoEJB;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public Partidos getPart() {
        return part;
    }

    public void setPart(Partidos part) {
        this.part = part;
    }


    public List<Elecciones> getListaElecciones() {
        return listaElecciones;
    }

    public void setListaElecciones(List<Elecciones> listaElecciones) {
        this.listaElecciones = listaElecciones;
    }

    public Elecciones getEleccion() {
        return eleccion;
    }

    public void setEleccion(Elecciones eleccion) {
        this.eleccion = eleccion;
    }


    
    
    

}
