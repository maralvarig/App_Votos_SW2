/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Elecciones;
import modelo.Partidos;

/**
 *
 * @author dnarc
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
    
        
    @Override
    public List<Partidos> encontrarPartidos(Elecciones eleccion){
        String consulta = "FROM Partidos p WHERE p.Elecciones_idElecciones=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", eleccion);
        
        List<Partidos> listaPartidos = query.getResultList();
        
        return listaPartidos;
    }
    
}
