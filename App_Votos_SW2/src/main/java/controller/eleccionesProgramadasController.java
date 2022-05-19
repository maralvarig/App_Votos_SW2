/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


/**
 *
 * @author dnarc
 */

@Named
@ViewScoped
public class eleccionesProgramadasController implements Serializable{
    
    private String partido;
    
    private String DNI;
    
    //Metodo que lleva a la pagina de elecciones anteriores
    public String irVotar(){
        return "votar.xhtml?faces-redirect=true";
    }
    
    public String votar(){
        String sentencia = "Has votado con DNI: "+DNI;
        FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Has votado con DNI: "+DNI));
        return "/index.xhtml?faces-redirect=true";
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
