/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cartao;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silvio.junior
 */
public class CartaoDaoImplTest {
    
    private Cartao cartao;
    private CartaoDao cartaoDao;
    private Session sessao;
    
    public CartaoDaoImplTest() {
        cartaoDao = new CartaoDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("salvar");
        cartao = new Cartao("5856-9856-9869-9856",
                "Master", "2029");
        sessao = HibernateUtil.abrirSessao();
        PessoaJuridicaDaoImplTest testJuridico 
                = new PessoaJuridicaDaoImplTest();
        cartao.setCliente(testJuridico.buscarPessoaJuridicaBD());
        cartaoDao.salvarOuAlterar(cartao, sessao);
        sessao.close();
    }

//    @Test
    public void testPesquisarPorID() {
        System.out.println("pesquisarPorID");
    }

//    @Test
    public void testPesquisarPorNumero() {
        System.out.println("pesquisarPorNumero");
    }
    
}
