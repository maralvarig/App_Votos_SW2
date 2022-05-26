/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Personas;

/**
 *
 * @author maral
 */
@Local
public interface PersonasFacadeLocal {

    void create(Personas personas);

    void edit(Personas personas);

    void remove(Personas personas);

    Personas find(Object id);

    List<Personas> findAll();

    List<Personas> findRange(int[] range);

    int count();

    public boolean existe(String DNI);

    

    public Object getPersonaDNI(String DNI);
    
}
