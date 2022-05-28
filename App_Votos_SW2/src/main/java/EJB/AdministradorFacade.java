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
import modelo.Administrador;

/**
 *
 * @author maral
 */
@Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> implements AdministradorFacadeLocal {

    @PersistenceContext(unitName = "PublicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
    @Override
    public boolean existe(String user, String password){

    String consulta = "FROM Administrador a WHERE a.Usuario=:param1 and a.Contrasenya=:param2";
    Query query = em.createQuery(consulta);
    query.setParameter("param1", user);
    query.setParameter("param2", password);

    List<Administrador> resultado = query.getResultList();

    return (resultado.size() != 0);
    }
}
