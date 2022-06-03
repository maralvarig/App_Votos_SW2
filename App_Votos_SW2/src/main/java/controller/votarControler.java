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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Partidos;
import modelo.Personas;
import modelo.Voto;

/**
 *
 * @author maral
 */
@Named
@ViewScoped
public class votarControler implements Serializable{
    
     
    @Inject
    private eleccionesProgramadasController elecProCon;
    
    private String partido;
    
    private String DNI;
    
    private Voto voto;
    
    private boolean seguro;
    
    private List<Partidos> listaPartidos;
    
    
    
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
        
        eleccion = elecProCon.getEleccion();
        
        listaPartidos = partidoEJB.encontrarPartidos(eleccion);
        System.out.println("TAM"+listaPartidos.size());
    
        
    }
    
    public String votar(){
        
        if(partido == null){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selecciona un Partido", null));
            return "votar.xhtml?faces-redirect=true";
        }else{
            if(!seguro){
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Confirme que esta seguro", null));
                return "votar.xhtml?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                if(!personaEJB.existe(DNI)){
                    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"El DNI no esta registrado.", null));
                }else{
                    Personas p =(Personas) personaEJB.getPersonaDNI(DNI);
                    if(haVotado(p)){
                        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Usted ya ha votado", null));
                        return "votar.xhtml?faces-redirect=true";
                    }
                    if(puedeVotar(p)){
                        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Usted no puede votar en estas elecciones", null));
                        return "votar.xhtml?faces-redirect=true";
                    }
                    p.addEleccion(eleccion);
                    personaEJB.edit(p);
                    voto.setElecciones_idElecciones(eleccion);
                    voto.setLocalidad_idLocalidad(p.getIdLocalidad());
                    voto.setVoto(partido);
                    try{
                        votoEJB.create(voto);
                    }catch(Exception e){
                        System.out.println("Error al votar:"+e.getMessage());
                    }
                    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Su voto ha sido registrado", null));

                }
            return "/index.xhtml?faces-redirect=true";
            }
        }
    }
    
    public boolean haVotado(Personas p){
        for(int i=0;i<p.getElecciones().size();i++){
            if(p.getElecciones().get(i).getIdElecciones()==eleccion.getIdElecciones())
                return true;
        }
        return false;        
    }
    public boolean puedeVotar(Personas p){
        if(eleccion.getTipo().equals("Generales")){
            if(eleccion.getLocalidad_idLocalidad().getPais().equals(p.getIdLocalidad().getPais()))
                return false;
        }
        if(eleccion.getTipo().equals("Autonomicas")){
            if(eleccion.getLocalidad_idLocalidad().getComunidad_Autonoma().equals(p.getIdLocalidad().getComunidad_Autonoma()))
                return false;
        }
        if(eleccion.getTipo().equals("Provinciales")){
            if(eleccion.getLocalidad_idLocalidad().getProvincia().equals(p.getIdLocalidad().getProvincia()))
                return false;
        }
        if(eleccion.getTipo().equals("Municipales")){
            if(eleccion.getLocalidad_idLocalidad().getMunicipio().equals(p.getIdLocalidad().getMunicipio()))
                return false;
        }
        
        return true;        
    }
    
    public void validar(){
     if(DNI != null){
         if(personaEJB.existe(DNI)){
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"El DNI esta registrado.", null));
         }else{
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"El DNI no esta registrado.", null));
         }
     }else{
         FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
         FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Introduzca un DNI", null));
     }
    }

    public eleccionesProgramadasController getElecProCon() {
        return elecProCon;
    }

    public void setElecProCon(eleccionesProgramadasController elecProCon) {
        this.elecProCon = elecProCon;
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

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public List<Partidos> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partidos> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public Elecciones getEleccion() {
        return eleccion;
    }

    public void setEleccion(Elecciones eleccion) {
        this.eleccion = eleccion;
    }

    public Partidos getPart() {
        return part;
    }

    public void setPart(Partidos part) {
        this.part = part;
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

}
