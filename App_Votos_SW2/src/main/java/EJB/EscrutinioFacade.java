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
import modelo.Escrutinio;

/**
 *
 * @author maral
 */
@Stateless
public class EscrutinioFacade extends AbstractFacade<Escrutinio> implements EscrutinioFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EscrutinioFacade() {
        super(Escrutinio.class);
    }
    
    public boolean existeEscrutinio(Elecciones eleccion){
        String consulta = "FROM Escrutinio e WHERE e.Elecciones_idElecciones=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", eleccion);
        
        List<Escrutinio> resultado = query.getResultList();
        
        return (resultado.size() != 0);
    }
    
    public List<Escrutinio> obtenerResultado(Elecciones eleccion){
        String consulta = "FROM Escrutinio e WHERE e.Elecciones_idElecciones=:param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", eleccion);
        
        List<Escrutinio> resultado = query.getResultList();
        
        return resultado;
    }
    
}
