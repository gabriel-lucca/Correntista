/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Profissao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author silvio.junior
 */
public class ProfissaoDaoImpl 
        extends BaseDaoImpl<Profissao, Long>
              implements ProfissaoDao, Serializable{

    @Override
    public Profissao pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Profissao) sessao.get(Profissao.class, id);
    }

    @Override
    public List<Profissao> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao
        .createQuery("from Profissao where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }
    
}
