/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Partidos;

/**
 *
 * @author maral
 */
@Stateless
public class PartidosFacade extends AbstractFacade<Partidos> implements PartidosFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidosFacade() {
        super(Partidos.class);
    }
    
<<<<<<< Updated upstream
=======
    @Override
    public List<Partidos> encontrarPartidos(Elecciones eleccion){
        String consulta = "FROM Partidos p WHERE p.Elecciones_idElecciones=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", eleccion);
        
        List<Partidos> listaPartidos = query.getResultList();
        return listaPartidos;
    }
>>>>>>> Stashed changes
}
