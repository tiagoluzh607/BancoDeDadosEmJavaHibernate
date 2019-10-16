/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.JPAUtil;
import dao.UsuarioDAO;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import model.Usuario;
import view.LoginView;
import view.MenuView;

/**
 *
 * @author tiago
 */
public class LoginController {
    
    private final LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }
   
    public void autenticar() throws SQLException{
        
        //Pegar usuario da tela
        String usuario =  view.getjTextFieldUsuario().getText();
        String senha = view.getjPasswordFieldSenha().getText();
        Usuario usuarioAutenticar = new Usuario(usuario, senha);
        
        //Ver se existe no banco de dados
        EntityManager em = new JPAUtil().getEntityManager();
        
            UsuarioDAO usuarioDao = new UsuarioDAO(em);

             boolean existe  =  usuarioDao.existeNoBancoPorUsuarioESenha(usuarioAutenticar);
            //Se existir no banco de dados passa para o menu

             if(existe){
                MenuView telaDeMenu = new MenuView();
                telaDeMenu.setVisible(true);
             }
             else{
                 JOptionPane.showMessageDialog(view, "Usuário ou Senha inválidos");
             }
        
         em.close();
    }
    
    
}
