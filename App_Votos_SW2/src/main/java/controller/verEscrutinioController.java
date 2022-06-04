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
import java.util.ArrayList;
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
    
    private String[] resultado;
    private List<Escrutinio> resultados;
    private Elecciones eleccion;        
    private List<Partidos> listaPartidos;
    private Partidos partido;
    private List<Representantes> listaRepresentantes;
    private String fecha;
    private String tipoEleccion;
    private ArrayList<String> partidos;
    private ArrayList<String> votos;
    private int total=0;
    private ArrayList<Integer> escanyos;
    
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
        listaPartidos = partidosEJB.encontrarPartidos(eleccion);
        fecha = eleccion.getFecha();
        tipoEleccion = eleccion.getTipo();
        partidos = new ArrayList<String>();
        votos = new ArrayList<String>();
        escanyos = new ArrayList<Integer>();
        ArrayList<String[]> partidosVotos = new ArrayList<String[]>();
        if(resultados.get(0).getResultados().equals("Total: 0 votos;")){
             resultado = new String[1];
            resultado[0] = "Total: 0 votos;";
        //Hay votos
        }else{
            for (int i = 0; i < listaPartidos.size(); i++) {
                resultado = resultados.get(0).getResultados().split(";");
            }
            
            for (int i = 0; i < listaPartidos.size(); i++){
                    partidosVotos.add(resultado[i].split(":"));
                    partidos.add(partidosVotos.get(i)[0]);
                    votos.add(partidosVotos.get(i)[1]);
                    total+=Integer.parseInt(votos.get(i));
                
            }
            int aux=0;
            int aux2=0;
            int aux3=0;
            for(int i=0;i<partidos.size();i++){
                escanyos.add(Math.round((Integer.parseInt(votos.get(i))*100)/total));
                aux+=escanyos.get(i);
            }
            if(aux!=100){
                for(int i=0;i<partidos.size();i++){
                    if(escanyos.get(i)>aux2)
                        aux3=i;
                }
                escanyos.set(aux3,100-aux+escanyos.get(aux3));
            }
        }
        
    }
    
    public String partidos(){
        String out="[";
        for(int i=0;i<partidos.size();i++)
            out+="\""+partidos.get(i)+"\",";
        out+="];";
        return out;
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
    
    public String votos(){
        String out="[";
        for(int i=0;i<votos.size();i++)
            out+=""+escanyos.get(i)+",";
        out+="];";
        return out;
    }
    
    public String tabla(){
        String out="";
        int escaños;
        for(int i=0;i<partidos.size();i++){
            escaños= Math.round((Integer.parseInt(votos.get(i))*100)/total);
            out+="<tr>\n";
            out+="                                    <td>"+votos.get(i)+"</td>\n";
            out+="                                    <td>"+escanyos.get(i)+"</td>\n";
            out+="                                    <td>"+partidos.get(i)+"</td>\n";
            out+="                                </tr>";
        }
        return out;
    }
    public String pactometro(){
        String out="";
        for(int i=0;i<partidos.size();i++){
            out+="{\n";
            out+="                                                label: xValues["+i+"],\n";
            out+="                                               data: [yValues["+i+"]],\n";
            out+="                                                backgroundColor:  barColors["+i+"]\n";
            out+="                                            },\n";
        }
        return out;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoEleccion() {
        return tipoEleccion;
    }

    public void setTipoEleccion(String tipoEleccion) {
        this.tipoEleccion = tipoEleccion;
    }

    public ArrayList<String> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<String> partidos) {
        this.partidos = partidos;
    }

    public ArrayList<String> getVotos() {
        return votos;
    }

    public void setVotos(ArrayList<String> votos) {
        this.votos = votos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public String[] getResultado() {
        return resultado;
    }

    public void setResultado(String[] resultado) {
        this.resultado = resultado;
    }
}