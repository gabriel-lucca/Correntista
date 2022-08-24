/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Perfil;
import br.com.senac.entidade.Usuario;
import br.com.senac.util.GeradorUtil;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silvio.junior
 */
public class UsuarioDaoImplTest {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Session sessao;

    public UsuarioDaoImplTest() {
        usuarioDao = new UsuarioDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        PerfilDaoImplTest perfilTest = new PerfilDaoImplTest();
        //usuario = new Usuario(gerarNome(), gerarNome(),
               // gerarNumero(5));
        Perfil perfil = perfilTest.buscarPerfilBd();
        usuario.setPerfil(perfil);
        sessao = HibernateUtil.abrirSessao();
        usuarioDao.salvarOuAlterar(usuario, sessao);
        sessao.close();
        assertNotNull(usuario.getId());
//        assertNotNull(usuario.getId());
    }

//    @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
        buscarUsuarioBd();
        sessao = HibernateUtil.abrirSessao();
        Usuario usuarioNovo = usuarioDao.pesquisarPorID(usuario.getId(), sessao);
        sessao.close();
        assertNotNull(usuarioNovo);

    }

//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
    }

//    @Test
    public void testPesquisarPorPerfil() {
        System.out.println("pesquisarPorPerfil");
    }

    public Usuario buscarUsuarioBd() {
        sessao = HibernateUtil.abrirSessao();
        Query consulta = sessao.createQuery("from Usuario");
        List<Usuario> usuarios = consulta.list();
        sessao.close();
        if(usuarios.isEmpty()){
            testSalvar();
        }else{
            usuario = usuarios.get(0);
        }
        return usuario;
    }

}
