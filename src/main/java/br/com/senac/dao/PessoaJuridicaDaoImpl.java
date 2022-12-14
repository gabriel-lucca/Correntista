/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author silvio.junior
 */
public class PessoaJuridicaDaoImpl extends
        BaseDaoImpl<PessoaJuridica, Long>
        implements PessoaJuridicaDao, Serializable {

    @Override
    public PessoaJuridica pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (PessoaJuridica) sessao.get(PessoaJuridica.class, id);
    }

    @Override
    public List<PessoaJuridica> pesquisarPorNome(String nome,
            Session sessao) throws HibernateException {
        Query consulta = sessao
       .createQuery("from PessoaJuridica where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

}
