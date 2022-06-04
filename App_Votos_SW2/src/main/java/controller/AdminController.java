package controller;

import EJB.AdministradorFacadeLocal;
import EJB.EleccionesFacadeLocal;
import EJB.LocalidadFacadeLocal;
import EJB.PartidosFacadeLocal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Elecciones;
import modelo.Localidad;
import modelo.Partidos;

/**
 *
 * @author dnarc
 */

@Named
@ViewScoped
public class AdminController implements Serializable{
    
    private String user;
    private String password;
    private Elecciones eleccion;
    private Date fecha;
    private Localidad localidad;
    private List<Localidad> listaLocalidades;
    private List<Elecciones> listaElecciones;
    private ArrayList<String> municipios;
    private Partidos partido;
    
    @EJB
    private AdministradorFacadeLocal adminEJB;
    @EJB
    private EleccionesFacadeLocal eleccionEJB;
    @EJB
    private LocalidadFacadeLocal localidadEJB;
    @EJB
    private PartidosFacadeLocal partidoEJB;
    
    @PostConstruct
    public void init(){
        localidad = new Localidad();
        eleccion = new Elecciones();
        listaLocalidades = localidadEJB.findAll();
        partido = new Partidos();
        listaElecciones = filtrarEleccionesPasadas(eleccionEJB.findAll());
        municipios = new ArrayList<String>();
    }
    //Metodo que lleva a la página del administrador
    public String administrar(){
        //LA SESION ESTA INICIADA
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin") != null){
            return "/privado/administrador/admin.xhtml?faces-redirect=true";
        }else{
            //ENTRA
            if(adminEJB.existe(user, password)){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin", adminEJB);
                return "/privado/administrador/admin.xhtml?faces-redirect=true";
            //VUELVE AL INICIO
            }else{
                FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Credenciales incorrectas", null));
                return "/index.xhtml?faces-redirect=true";
            }
        }
    }
    //Lleva a la pagina de crearEleccion
    public String irCrearEleccion(){
        return "/privado/administrador/elecciones/crearEleccion.xhtml?faces-redirect=true";
    }
    
    //Lleva a la pagina de crearEleccion
    public String irCrearPartido(){
        return "/privado/administrador/elecciones/crearPartido.xhtml?faces-redirect=true";
    }
    
    public String localidad(Elecciones eleccion){
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
    
    public String crearPartido(){
        partido.setElecciones_idElecciones(eleccion);
        try{
            partidoEJB.create(partido);
        }catch(Exception e){
            System.out.println("Error al crear el partido"+ e.getMessage());
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Partido Creado", null));
        return "/index.xhtml?faces-redirect=true";
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
    
    public String crearEleccion(){
        double fechaE = (fecha.getYear()+1900)*10000+(fecha.getMonth()+1)*100+fecha.getDate();
        double fechah;
        fechah = (LocalDateTime.now().getYear())*10000+(LocalDateTime.now().getMonthValue())*100+LocalDateTime.now().getDayOfMonth();
        if(Double.compare(fechah, fechaE)!=-1 || Double.compare(fechah, fechaE)==0){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"La fecha es anterior a la actual.", null));
            return "crearEleccion.xhtml?faces-redirect=true";
        }
        eleccion.setFecha(fecha.getDay()+"/"+fecha.getMonth()+"/"+(fecha.getYear()+1900));
        eleccion.setLocalidad_idLocalidad(localidad);
        try{
            eleccionEJB.create(eleccion);
        }catch(Exception e){
            System.out.println("Error al crear eleccion: "+e.getMessage());
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Eleccion Creada", null));
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String buscarEleccion(){
        return "/privado/administrador/elecciones/buscarEleccion.xhtml?faces-redirect=true";
    }
    
    //Borra la sesion del administrador
   public String destruirSesion(){
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       return "/index.xhtml?faces-redirect=true";
   }
    
    public void verificarYMostrar(){
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin") == null){
            String url = "/index.xhtml?faces-redirect=true";
            try{
                FacesContext.getCurrentInstance().getExternalContext().redirect(url);
            }catch(Exception e){
                System.out.println("Error verificarYMostrar: "+e);
            }
        }
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public List<Localidad> getListaLocalidades() {
        return listaLocalidades;
    }

    public void setListaLocalidades(List<Localidad> listaLocalidades) {
        this.listaLocalidades = listaLocalidades;
    }


    public LocalidadFacadeLocal getLocalidadEJB() {
        return localidadEJB;
    }

    public void setLocalidadEJB(LocalidadFacadeLocal localidadEJB) {
        this.localidadEJB = localidadEJB;
    }

    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdministradorFacadeLocal getAdminEJB() {
        return adminEJB;
    }

    public void setAdminEJB(AdministradorFacadeLocal adminEJB) {
        this.adminEJB = adminEJB;
    }

    public Elecciones getEleccion() {
        return eleccion;
    }

    public void setEleccion(Elecciones eleccion) {
        this.eleccion = eleccion;
    }

    public EleccionesFacadeLocal getEleccionEJB() {
        return eleccionEJB;
    }

    public void setEleccionEJB(EleccionesFacadeLocal eleccionEJB) {
        this.eleccionEJB = eleccionEJB;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }   

    public ArrayList<String> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(ArrayList<String> municipios) {
        this.municipios = municipios;
    }

    public Partidos getPartido() {
        return partido;
    }

    public void setPartido(Partidos partido) {
        this.partido = partido;
    }

    public PartidosFacadeLocal getPartidoEJB() {
        return partidoEJB;
    }

    public void setPartidoEJB(PartidosFacadeLocal partidoEJB) {
        this.partidoEJB = partidoEJB;
    }

    public List<Elecciones> getListaElecciones() {
        return listaElecciones;
    }

    public void setListaElecciones(List<Elecciones> listaElecciones) {
        this.listaElecciones = listaElecciones;
    }
}