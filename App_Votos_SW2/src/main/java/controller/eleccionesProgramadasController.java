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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
    
    private Partidos part;
    
    @EJB
    private PersonasFacadeLocal personaEJB;
    
    @EJB
    private EleccionesFacadeLocal eleccionesEJB;
    
    @EJB
    private VotoFacadeLocal votoEJB;
    
    @EJB
    private PartidosFacadeLocal partidoEJB;
    
    
    
    //Metodo que lleva a la pagina de elecciones anteriores
    public String irVotar(){
        return "votar.xhtml?faces-redirect=true";
    }
    @PostConstruct
    public void init(){
        part = new Partidos();
        voto = new Voto();
        listaPartidos = partidoEJB.findAll();
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
                    p.addEleccion(eleccionesEJB.getElecction(1));
                    personaEJB.edit(p);
                    voto.setElecciones_idElecciones(eleccionesEJB.getElecction(1));
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
    
    public boolean generarVoto(){
        return false;
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
    
    
    
}
