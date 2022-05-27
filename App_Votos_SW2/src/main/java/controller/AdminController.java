package controller;

import EJB.AdministradorFacadeLocal;
import EJB.EleccionesFacadeLocal;
import EJB.LocalidadFacadeLocal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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
    private ArrayList<String> municipios;
    
    @EJB
    private AdministradorFacadeLocal adminEJB;
    @EJB
    private EleccionesFacadeLocal eleccionEJB;
    @EJB
    private LocalidadFacadeLocal localidadEJB;
    
    @PostConstruct
    public void init(){
        localidad = new Localidad();
        eleccion = new Elecciones();
        listaLocalidades = localidadEJB.findAll();
        municipios = new ArrayList<String>();
        for(int i=0;i<listaLocalidades.size();i++){
            System.out.print("==========================================================\n");
            System.out.println(listaLocalidades.get(i).getMunicipio());
            System.out.print("============================================================");
        }
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
        return "/privado/administrador/crearEleccion.xhtml?faces-redirect=true";
    }
    
    public String crearEleccion(){
        double fechaE = (fecha.getYear()+1900)*10000+(fecha.getMonth()+1)*100+fecha.getDate();
        double fechah;
        fechah = (LocalDateTime.now().getYear())*10000+(LocalDateTime.now().getMonthValue())*100+LocalDateTime.now().getDayOfMonth();
        if(Double.compare(fechah, fechaE)!=-1){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"La fecha es anterior a la actual.", null));
            return "/privado/administrador/crearEleccion.xhtml?faces-redirect=true";
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
        return "/privado/administrador/buscarEleccion.xhtml?faces-redirect=true";
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
    
}