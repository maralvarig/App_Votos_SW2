/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EleccionesFacadeLocal;
import EJB.EscrutinioFacadeLocal;
import EJB.VotoFacadeLocal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class eleccionesAnterioresController implements Serializable{
    
    private String partido;
    
    private List<Elecciones> listaElecciones;
    
    private Elecciones eleccion;
    
    private Partidos part;
    
    @EJB
    private EleccionesFacadeLocal eleccionesEJB;
    
    @EJB
    private VotoFacadeLocal votoEJB;
    
     @EJB
    private EscrutinioFacadeLocal escrutinioEJB;
    
    
    

    
    @PostConstruct
    public void init(){
        part = new Partidos();
        eleccion = new Elecciones();
        listaElecciones = filtrarEleccionesPasadas(eleccionesEJB.findAll());
        
    }
    
    //Obtiene las elecciones programadas (fecha anterior a hoy)
    public List<Elecciones> filtrarEleccionesPasadas(List<Elecciones> lista){
        List<Elecciones> filtrada = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            String[] fechaEleccion = lista.get(i).getFecha().split("/");
            //YEAR
            if(Integer.parseInt(fechaEleccion[2]) < LocalDateTime.now().getYear()){
                filtrada.add(lista.get(i));
            }else{
                if(LocalDateTime.now().getYear() == Integer.parseInt(fechaEleccion[2])){
                    //MONTH
                    if(Integer.parseInt(fechaEleccion[1]) < LocalDateTime.now().getMonthValue()){
                        filtrada.add(lista.get(i));
                    }else{
                       if(LocalDateTime.now().getMonthValue() == Integer.parseInt(fechaEleccion[1])){
                           //DAY
                           if(Integer.parseInt(fechaEleccion[0]) < LocalDateTime.now().getDayOfMonth()){
                               filtrada.add(lista.get(i));
                           }
                       }
                    }
                }
            }
        }
        return filtrada;
    }
    public String localidad(Elecciones eleccion){
        //eleccion.getLocalidad_idLocalidad();
        if(eleccion.getTipo().equals("Generales")){            
                return eleccion.getLocalidad_idLocalidad().getPais();
        }
        if(eleccion.getTipo().equals("Autonomicas")){           
                return eleccion.getLocalidad_idLocalidad().getComunidad_Autonoma();
        }
        if(eleccion.getTipo().equals("Provinciales")){
                return eleccion.getLocalidad_idLocalidad().getMunicipio();
        }
        if(eleccion.getTipo().equals("Municipales")){
                return eleccion.getLocalidad_idLocalidad().getProvincia();
        }
        return "DESCONOCIDO";
    } 
    public void visualizarPartidos(Elecciones eleccion) {
        this.eleccion = eleccion;
    }
    
    public String verEscrutinio(Elecciones eleccion){
        this.eleccion = eleccion;
        return "escrutinioVotante.xhtml?faces-redirect=true";
    }
    
    public boolean existeEscrutinio(Elecciones eleccion){
        return escrutinioEJB.existeEscrutinio(eleccion);
    }
   
    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
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
