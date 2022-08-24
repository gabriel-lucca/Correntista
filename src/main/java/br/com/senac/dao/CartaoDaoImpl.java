/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cartao;
import br.com.senac.entidade.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author silvio.junior
 */
public class CartaoDaoImpl extends BaseDaoImpl<Cartao, Long>
        implements CartaoDao, Serializable {

    @Override
    public Cartao pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Cartao) sessao.get(Cartao.class, id);
    }

    @Override
    public Cartao pesquisarPorNumero(String numero, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Cartao"
                + " where numero = :numero");
        consulta.setParameter("numero", numero);
        return (Cartao) consulta.uniqueResult();
    }

}
