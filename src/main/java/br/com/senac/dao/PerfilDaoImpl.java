/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Perfil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author silvio.junior
 */
public class PerfilDaoImpl
               extends BaseDaoImpl<Perfil, Long>
                          implements PerfilDao, Serializable {

    @Override
    public Perfil pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Perfil) sessao.get(Perfil.class, id);
    }

    @Override
    public List<Perfil> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao
                .createQuery("from Perfil where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<Perfil> pesquisarTodo(Session sessao) throws HibernateException {
         Query consulta = sessao
                .createQuery("from Perfil");
        return consulta.list();
               
    }

}
