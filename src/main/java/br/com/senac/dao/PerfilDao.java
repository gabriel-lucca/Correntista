/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Perfil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author silvio.junior
 */
public interface PerfilDao extends BaseDao<Perfil, Long> {

    List<Perfil> pesquisarPorNome(String nome,Session sessao)
                                       throws HibernateException;
    
    List<Perfil> pesquisarTodo(Session sessao) throws HibernateException;
}
