/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author dnarc
 */
@Named
@ViewScoped
public class IndexController implements Serializable{
    
    //Metodo que lleva al inicio de la página
    public String irInicio(){
        return "/index.xhtml?faces-redirect=true";
    }
    
    //Metodo que lleva a la pagina de elecciones programadas
    public String irElecProgramadas(){
        return "/privado/eleccionesProgramadas.xhtml?faces-redirect=true";
    }
    
    //Metodo que lleva a la pagina de elecciones anteriores
    public String irElecAnteriores(){
        return "/privado/altaCategoria.xhtml?faces-redirect=true";
    }
}
