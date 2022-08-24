/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author silvio.junior
 */
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;
    //Singleton
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Cliente.class);
            cfg.addAnnotatedClass(PessoaJuridica.class);
            cfg.addAnnotatedClass(Endereco.class);
            cfg.addAnnotatedClass(Cartao.class);
            cfg.addAnnotatedClass(Profissao.class);
            cfg.addAnnotatedClass(Perfil.class);
            cfg.addAnnotatedClass(Usuario.class);
            cfg.addAnnotatedClass(PessoaFisica.class);
            cfg.addAnnotatedClass(Telefone.class);
            
            cfg.configure("/META-INF/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = 
                    new StandardServiceRegistryBuilder()
                            .applySettings(cfg.getProperties());
            SESSION_FACTORY = cfg
                            .buildSessionFactory(build.build());
        } catch (HibernateException e) {
            System.err.println("Erro ao abrir conexão"
                    + " HibernateUtil " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
 
    }
    
    public static Session abrirSessao(){
        return SESSION_FACTORY.openSession();
    }
    
}
