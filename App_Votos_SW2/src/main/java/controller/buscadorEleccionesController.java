/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import EJB.EleccionesFacadeLocal;
import EJB.EscrutinioFacadeLocal;
import EJB.PartidosFacadeLocal;
import EJB.VotoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Escrutinio;
import modelo.Partidos;
import modelo.Voto;

/**
 *
 * @author dnarc
 */
@Named
@SessionScoped
public class buscadorEleccionesController implements Serializable{
    
    private Elecciones eleccion;
    private List<Elecciones> listaElecciones;
    private List<Partidos> listaPartidos;
    
    private Escrutinio escrutinio;
    
    private String stringPartidos[][];
    
    @EJB
    private EleccionesFacadeLocal eleccionEJB;
    
    @EJB
    private PartidosFacadeLocal partidosEJB;
    
    @EJB
    private EscrutinioFacadeLocal escrutinioEJB;
    
    @EJB
    private VotoFacadeLocal votoEJB;
    
    
    
    @PostConstruct
    public void init(){
        eleccion = new Elecciones();
        escrutinio = new Escrutinio();
    }
    
    public void buscarElecciones(){
        listaElecciones = eleccionEJB.buscarElecciones(eleccion);
    }
    
    public String visualizarPartidos(Elecciones eleccion){
        this.eleccion = eleccion;
        return "crearEleccion.xhtml?faces-redirect=true";
    }
    
    public String generarEscrutinio(Elecciones eleccion){
        this.eleccion = eleccion;  
        listaPartidos = partidosEJB.encontrarPartidos(this.eleccion);
        //Hay que calcular el Escrutinio
        if(!escrutinioEJB.existeEscrutinio(this.eleccion)){
            //Obtenemos los votos y partidos
            int tam = listaPartidos.size();
            stringPartidos = new String[tam][2];

            for (int i=0;i<tam; i++) {
                stringPartidos[i][0] = listaPartidos.get(i).getNombre();
            }
            List<Voto> listaVotos = new ArrayList();
            try{
                listaVotos = votoEJB.buscarVotos(this.eleccion);
            }catch(Exception e){
                System.out.println("Error al obtener los votos: "+e.getMessage());
            }
            
            int valor;
            
            //Hacemos las cuentas
            //Recorremos los votos y buscamos el partido
            for (int i = 0; i < stringPartidos.length; i++) {
                for (int j = 0; j < listaVotos.size(); j++) {
                    if(stringPartidos[i][0].equals(listaVotos.get(j).getVoto())){
                        if(stringPartidos[i][1] == null){
                            stringPartidos[i][1] = "1";
                        }else{
                            valor = Integer.parseInt(stringPartidos[i][1]);
                            valor++;
                            stringPartidos[i][1] = String.valueOf(valor);
                        }
                    }
                }
            }
            
            //Seteo el resultado con PARTIDO;VOTOS;PARTIDO;VOTOS;...
            String resultado ="";
            for (int i=0;i<stringPartidos.length; i++) {
                resultado += stringPartidos[i][0]+";"+stringPartidos[i][1]+";";
            }
             
           try{
               escrutinio.setElecciones_idElecciones(this.eleccion);
               escrutinio.setElecciones_Localidad_idLocalidad(this.eleccion.getLocalidad_idLocalidad());
               escrutinio.setResultados(resultado);
               escrutinioEJB.create(escrutinio);
           }catch(Exception e){
               System.out.println("Error al insertar el escrutinio en BBDD: "+e.getMessage());
           }
         
        }
        return "verEscrutinio.xhtml?faces-redirect=true";
    }

    public Elecciones getEleccion() {
        return eleccion;
    }

    public void setEleccion(Elecciones eleccion) {
        this.eleccion = eleccion;
    }

    public List<Elecciones> getListaElecciones() {
        return listaElecciones;
    }

    public void setListaElecciones(List<Elecciones> listaElecciones) {
        this.listaElecciones = listaElecciones;
    }

    public EleccionesFacadeLocal getEleccionEJB() {
        return eleccionEJB;
    }

    public void setEleccionEJB(EleccionesFacadeLocal eleccionEJB) {
        this.eleccionEJB = eleccionEJB;
    }

    public List<Partidos> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partidos> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public PartidosFacadeLocal getPartidosEJB() {
        return partidosEJB;
    }

    public void setPartidosEJB(PartidosFacadeLocal partidosEJB) {
        this.partidosEJB = partidosEJB;
    }

    public String[][] getStringPartidos() {
        return stringPartidos;
    }

    public void setStringPartidos(String[][] stringPartidos) {
        this.stringPartidos = stringPartidos;
    }

    public Escrutinio getEscrutinio() {
        return escrutinio;
    }

    public void setEscrutinio(Escrutinio escrutinio) {
        this.escrutinio = escrutinio;
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

}
