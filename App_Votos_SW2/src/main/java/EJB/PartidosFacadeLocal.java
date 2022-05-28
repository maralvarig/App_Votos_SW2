/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Partidos;

/**
 *
 * @author dnarc
 */
@Local
public interface PartidosFacadeLocal {

    void create(Partidos partidos);

    void edit(Partidos partidos);

    void remove(Partidos partidos);

    Partidos find(Object id);

    List<Partidos> findAll();

    List<Partidos> findRange(int[] range);

    int count();
    
}
