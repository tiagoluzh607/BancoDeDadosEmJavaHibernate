/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.JPAUtil;
import dao.UsuarioDAO;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.Usuario;
import view.FormCadastroView;

/**
 *
 * @author tiago
 */
public class FormCadastroController {
    
    private FormCadastroView view;

    public FormCadastroController(FormCadastroView view) {
        this.view = view;
    }
    
    
    public void salvaUsuario(){
        
        String usuario =  view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        
        Usuario usuarioXandi = new Usuario(usuario, senha);
         
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
            
            UsuarioDAO usuarioDao = new UsuarioDAO(em);
            usuarioDao.insert(usuarioXandi);
            JOptionPane.showMessageDialog(null, "Usuario Salvo Com Sucesso!");
        
        em.getTransaction().commit();
        em.close();
        
        
    }
    
    
}
