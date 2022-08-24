/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Telefone;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author gabriel.lucas
 */
public interface TelefoneDao extends BaseDao<Telefone, Long> {

    List<Telefone> pesquisarPorNumero(String numero,
            Session sessao) throws HibernateException;
}

