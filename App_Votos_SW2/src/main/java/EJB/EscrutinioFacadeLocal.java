/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Elecciones;
import modelo.Escrutinio;

/**
 *
 * @author maral
 */
@Local
public interface EscrutinioFacadeLocal {

    void create(Escrutinio escrutinio);

    void edit(Escrutinio escrutinio);

    void remove(Escrutinio escrutinio);

    Escrutinio find(Object id);

    List<Escrutinio> findAll();

    List<Escrutinio> findRange(int[] range);

    int count();
    
    boolean existeEscrutinio(Elecciones eleccion);
            
    List<Escrutinio> obtenerResultado(Elecciones eleccion);
    
}
