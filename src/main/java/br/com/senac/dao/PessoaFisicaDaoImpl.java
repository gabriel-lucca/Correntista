/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaFisica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author silvio.junior
 */
public class PessoaFisicaDaoImpl extends
        BaseDaoImpl<PessoaFisica, Long>
        implements PessoaFisicaDao, Serializable {

    @Override
    public PessoaFisica pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (PessoaFisica) sessao.get(PessoaFisica.class, id);
    }

    @Override
    public List<PessoaFisica> pesquisarPorNome(String nome,
            Session sessao) throws HibernateException {
        Query consulta = sessao
       .createQuery("from PessoaFisica where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

}
