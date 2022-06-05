/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Elecciones;
import modelo.Localidad;

/**
 *
 * @author maral
 */
@Local
public interface EleccionesFacadeLocal {

    void create(Elecciones elecciones);

    void edit(Elecciones elecciones);

    void remove(Elecciones elecciones);

    Elecciones find(Object id);

    List<Elecciones> findAll();

    List<Elecciones> findRange(int[] range);

    int count();

    public Elecciones getElecction(int i);
    
    List<Elecciones> buscarElecciones(Elecciones eleccion);
    
    boolean existeEleccion(Localidad l,String tipo, String fecha);
    
}
