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
public class AdminController implements Serializable{
    
    private String user;
    private String password;
    
    //Metodo que lleva a la página del administrador
    public String administrar(){
        return "/privado/administrador/admin.xhtml?faces-redirect=true";
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
    
}