/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import model.Usuario;

/**
 *
 * @author tiago
 */
public class UsuarioDAO {
    private final EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }
    
    public Usuario insert(Usuario usuario){
        em.persist(usuario);
        return usuario;
    }
    
    public Usuario update(Usuario usuario){
        em.merge(usuario);
        em.persist(usuario);
        return usuario;
    }
    
    public Usuario insertOrUpdate(Usuario usuario){
       if(usuario.getId() > 0){
           return update(usuario);
       }    
       return insert(usuario);
    }
    
    public void delete(Usuario usuario){
       em.merge(usuario);
       em.remove(usuario);
    }
    
    public List<Usuario> selectAll(){
        String jpql = "select obj from "+ Usuario.class.getSimpleName() +" as obj";
        Query query = this.em.createQuery(jpql);
        return retornarListaComBaseNaConsulta(query);
    }

    public Usuario selectPorId(Usuario usuario){
        return em.find(usuario.getClass(), usuario.getId());
    }
    

    public boolean existeNoBancoPorUsuarioESenha(Usuario usuarioNovo){
        
        String jpql = "select u from "+ Usuario.class.getSimpleName() +" as u "
                + "where u.nome = :pUsuario and u.senha = :pSenha";
        Query query = this.em.createQuery(jpql);
        query.setParameter("pUsuario", usuarioNovo.getUsuario());
        query.setParameter("pSenha", usuarioNovo.getSenha());
        
        return !retornarListaComBaseNaConsulta(query).isEmpty();
            
        
    }
    
    private List<Usuario> retornarListaComBaseNaConsulta(Query query) {
        List<Usuario> usuarios;
        try {
            usuarios = (List<Usuario>) query.getResultList();
        }catch (NoResultException e) {usuarios = new ArrayList<Usuario>();}
        return usuarios;
    }
}
