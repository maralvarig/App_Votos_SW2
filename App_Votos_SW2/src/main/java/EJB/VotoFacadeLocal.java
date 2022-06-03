
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Elecciones;
import modelo.Voto;

/**
 *
 * @author maral
 */
@Local
public interface VotoFacadeLocal {

    void create(Voto voto);

    void edit(Voto voto);

    void remove(Voto voto);

    Voto find(Object id);

    List<Voto> findAll();

    List<Voto> findRange(int[] range);

    int count();
    
    List<Voto> buscarVotos(Elecciones eleccion);
}
