/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Partidos;
import modelo.Representantes;

/**
 *
 * @author dnarc
 */
@Local
public interface RepresentantesFacadeLocal {

    void create(Representantes representantes);

    void edit(Representantes representantes);

    void remove(Representantes representantes);

    Representantes find(Object id);

    List<Representantes> findAll();

    List<Representantes> findRange(int[] range);

    int count();
    
    List<Representantes> encontrarRepresentantes(Partidos partido);
}
