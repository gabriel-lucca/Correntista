/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Profissao;
import br.com.senac.entidade.Telefone;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author silvio.junior
 */
public class TelefoneDaoImpl
        extends BaseDaoImpl<Telefone, Long>
        implements TelefoneDao, Serializable {

    @Override
    public Telefone pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Telefone) sessao.get(Telefone.class, id);
    }

    @Override
    public List<Telefone> pesquisarPorNumero(String numero, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Telefone where numero like :numero");
        consulta.setParameter("numero", "%" + numero + "%");
        return consulta.list();
    }

}
