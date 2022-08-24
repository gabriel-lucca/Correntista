/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaJuridica;
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
public class PessoaJuridicaDaoImplTest {

    private PessoaJuridica juridica;
    private PessoaJuridicaDao juridicaDao;
    private Session sessao;

    public PessoaJuridicaDaoImplTest() {
        juridicaDao = new PessoaJuridicaDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        juridica = new PessoaJuridica(
                gerarNome(),
                gerarNome() + "@email.com",
                gerarCnpj(),
                gerarNumero(8)
        );
        Endereco endereco = gerarEndereco();
        juridica.setEndereco(endereco);
        endereco.setCliente(juridica);

        sessao = HibernateUtil.abrirSessao();
        juridicaDao.salvarOuAlterar(juridica, sessao);
        sessao.close();
        assertNotNull(juridica.getId());
        assertNotNull(endereco.getCliente().getId());
    }

//    @Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarPessoaJuridicaBD();
        juridica.setNome(gerarNome());
        juridica.setCnpj(gerarCnpj());
        juridica.getEndereco().setBairro("Florinda");
        sessao = HibernateUtil.abrirSessao();
        juridicaDao.salvarOuAlterar(juridica, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirSessao();
        PessoaJuridica pj = juridicaDao
                .pesquisarPorID(juridica.getId(), sessao);
        sessao.close();
        assertEquals(pj.getNome(), juridica.getNome());
        assertEquals(pj.getCnpj(), juridica.getCnpj());
        assertEquals(pj.getEndereco().getBairro(),
                juridica.getEndereco().getBairro());
    }
    
//    @Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarPessoaJuridicaBD();
        sessao = HibernateUtil.abrirSessao();
        juridicaDao.excluir(juridica, sessao);
        
        PessoaJuridica pj = juridicaDao
                .pesquisarPorID(juridica.getId(), sessao);
        sessao.close();
        assertNull(pj);
    }
//    @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
        buscarPessoaJuridicaBD();
        sessao = HibernateUtil.abrirSessao();
        PessoaJuridica pj = juridicaDao
                .pesquisarPorID(juridica.getId(), sessao);
        sessao.close();
        assertNotNull(pj);
    }

//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarPessoaJuridicaBD();
        sessao = HibernateUtil.abrirSessao();
        List<PessoaJuridica> pessoas = juridicaDao
                .pesquisarPorNome(juridica.getNome(), sessao);
        sessao.close();
        assertTrue(!pessoas.isEmpty());
    }

    private Endereco gerarEndereco() {
        Endereco endereco = new Endereco(
                "Rua " + gerarNome(),
                gerarNumero(3),
                "Centro",
                gerarNumero(5) + "-" + gerarNumero(3),//88888-888
                "SC",
                "Florida",
                "casa"
        );
        return endereco;
    }
    
    public PessoaJuridica buscarPessoaJuridicaBD() {
        sessao = HibernateUtil.abrirSessao();
        Query consulta = sessao.createQuery("from PessoaJuridica");
        List<PessoaJuridica> pessoas = consulta.list();
        sessao.close();
        if (pessoas.isEmpty()) {
            testSalvar();
        } else {
            juridica = pessoas.get(0);
        }
        return juridica;
    }
    

}
