/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author silvio.junior
 */
public class UsuarioDaoImpl
        extends BaseDaoImpl<Usuario, Long>
        implements UsuarioDao, Serializable {

    @Override
    public Usuario pesquisarPorID(Long id, Session sessao) throws HibernateException {
        return (Usuario) sessao.get(Usuario.class, id);
    }

    @Override
    public List<Usuario> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao
                .createQuery("from Usuario where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<Usuario> pesquisarPorPerfil(String perfil,
            Session sessao) throws HibernateException {
        Query consulta = sessao
           .createQuery("from Usuario where perfil.nome = :nome");
        consulta.setParameter("nome", perfil);
        return consulta.list();
    }

    
}
