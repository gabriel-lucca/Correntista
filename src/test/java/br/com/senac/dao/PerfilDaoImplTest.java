/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Perfil;
import br.com.senac.util.GeradorUtil;
import static br.com.senac.util.GeradorUtil.gerarNumero;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silvio.junior
 */
public class PerfilDaoImplTest {

    private Perfil perfil;
    private PerfilDao perfilDao;
    private Session sessao;

    public PerfilDaoImplTest() {
        perfilDao = new PerfilDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("Salvar");
        
        perfil = new Perfil(null, "Adm " + gerarNumero(5),
                "bla, bla");
        sessao = HibernateUtil.abrirSessao();
        perfilDao.salvarOuAlterar(perfil, sessao);
        sessao.close();
        assertNotNull(perfil.getId());
    }

//    @Test
    public void testPesquisarPorID() {
        System.out.println("PesquisarPorID");
    }

//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
    }
    
    public Perfil buscarPerfilBd(){
        sessao = HibernateUtil.abrirSessao();
        Query consulta = sessao.createQuery("from Perfil");
        List<Perfil> perfis = consulta.list();
        sessao.close();
        if(perfis.isEmpty()){
            testSalvar();
        }else{
            perfil = perfis.get(0);
        }
        return perfil;
    }

}
