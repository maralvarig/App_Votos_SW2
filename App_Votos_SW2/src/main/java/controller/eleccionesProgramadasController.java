/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EleccionesFacadeLocal;
import EJB.PersonasFacadeLocal;
import EJB.VotoFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Personas;
import modelo.Voto;


/**
 *
 * @author dnarc
 */

@Named
@ViewScoped
public class eleccionesProgramadasController implements Serializable{
    
    private String partido;
    
    private String DNI;
    
    private Voto voto;
    
    @EJB
    private PersonasFacadeLocal personaEJB;
    
    @EJB
    private EleccionesFacadeLocal eleccionesEJB;
    
    @EJB
    private VotoFacadeLocal votoEJB;
    
    
    
    //Metodo que lleva a la pagina de elecciones anteriores
    public String irVotar(){
        return "votar.xhtml?faces-redirect=true";
    }
    @PostConstruct
    public void init(){
        voto = new Voto();
<<<<<<< Updated upstream
    }
    public String votar(){
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
=======
        listaPartidos = partidoEJB.encontrarPartidos(eleccionesEJB.getElecction(1));
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
                    if(votado(p)){
                        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usted ya ha votado.", null));
                        return "/index.xhtml?faces-redirect=true";
                    }
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
>>>>>>> Stashed changes
            }
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Su voto ha sido registrado", null));
            
        }
<<<<<<< Updated upstream
        return "/index.xhtml?faces-redirect=true";
=======
    }
    public boolean votado(Personas p){
        for(int i=0;i<p.getElecciones().size();i++){
            if(eleccionesEJB.getElecction(1).getIdElecciones()==p.getElecciones().get(i).getIdElecciones())
                return true;
        }
        return false;
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
>>>>>>> Stashed changes
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
    
}
