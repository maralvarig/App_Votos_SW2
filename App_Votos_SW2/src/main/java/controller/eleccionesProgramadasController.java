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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Partidos;
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
    
    private List<Elecciones> listaElecciones;
    
    private Elecciones eleccion;
    
    private Partidos part;
    
    @EJB
    private PersonasFacadeLocal personaEJB;
    
    @EJB
    private EleccionesFacadeLocal eleccionesEJB;
    
    @EJB
    private VotoFacadeLocal votoEJB;
    
    
    

    
    @PostConstruct
    public void init(){
        part = new Partidos();
        voto = new Voto();
        eleccion = new Elecciones();
        listaElecciones = filtrarElecciones(eleccionesEJB.findAll());
        
    }
    
    //Metodo que lleva a la pagina de elecciones anteriores
    public String irVotar(Elecciones eleccion){
        this.eleccion=eleccion;
        return "votar.xhtml?faces-redirect=true";
    }
    
    //Obtiene las elecciones programadas (fecha hoy o superior)
    public List<Elecciones> filtrarElecciones(List<Elecciones> lista){
        List<Elecciones> filtrada = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            String[] fechaEleccion = lista.get(i).getFecha().split("/");
            //YEAR
            if(Integer.parseInt(fechaEleccion[2]) > LocalDateTime.now().getYear()){
                filtrada.add(lista.get(i));
            }else{
                if(LocalDateTime.now().getYear() == Integer.parseInt(fechaEleccion[2])){
                    //MONTH
                    if(Integer.parseInt(fechaEleccion[1]) > LocalDateTime.now().getMonthValue()){
                        filtrada.add(lista.get(i));
                    }else{
                       if(LocalDateTime.now().getMonthValue() == Integer.parseInt(fechaEleccion[1])){
                           //DAY
                           if(Integer.parseInt(fechaEleccion[0]) >= LocalDateTime.now().getDayOfMonth()){
                               filtrada.add(lista.get(i));
                           }
                       }
                    }
                }
            }
        }
        return filtrada;
    }
    
    public String localidad(){
        eleccion.getLocalidad_idLocalidad();
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
        
    public void visualizarPartidos(Elecciones eleccion) {
        this.eleccion = eleccion;
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
