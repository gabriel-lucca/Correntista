/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.controle;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.PessoaFisicaDao;
import br.com.senac.dao.PessoaFisicaDaoImpl;
import br.com.senac.entidade.PessoaFisica;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author silvio.junior
 */
@ManagedBean(name = "fisicaC")
@ViewScoped
public class PessoaFisicaControle {

    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    private DataModel<PessoaFisica> pessoas;
    private Session sessao;

    private int aba;

    public PessoaFisicaControle() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }

    public void pesquisar() {
        try {
            sessao = HibernateUtil.abrirSessao();
            List<PessoaFisica> fisc = pessoaFisicaDao
                    .pesquisarPorNome(pessoaFisica.getNome(), sessao);
            pessoas = new ListDataModel<>(fisc);
            pessoaFisica.setNome("");
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar "
                    + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void excluir() {
        pessoaFisica = pessoas.getRowData();
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensagem = null;

        try {
            sessao = HibernateUtil.abrirSessao();
            pessoaFisicaDao.excluir(pessoaFisica, sessao);
            pessoas = null;
            pessoaFisica = null;
            mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Título", "excluído com Sucesso!");
        } catch (HibernateException e) {
            mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "", "Erro ao excluir!");
        } finally {
            sessao.close();
            contexto.addMessage("", mensagem);
        }
    }

    public void salvar() {
        try {
            sessao = HibernateUtil.abrirSessao();
            pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
            
            Mensagem.enviarSucesso(pessoaFisica.getNome()
                    + " salvo com sucesso!!");
        } catch (HibernateException e) {
            Mensagem.enviarErro(pessoaFisica.getNome()
                    + " erro ao salvar!!");
            System.out.println("Erro ao salvar " + e.getMessage());
        } finally {
            sessao.close();
            pessoaFisica = null;
        }
    }

    public void prepararAlterar() {
        pessoaFisica = pessoas.getRowData();
        aba = 1;
    }

//    getters and setters
    public PessoaFisica getPessoaFisica() {
        if (pessoaFisica == null) {
            pessoaFisica = new PessoaFisica();
        }
        return pessoaFisica;
    }

    public PessoaFisicaDao getPessoaFisicaDao() {
        return pessoaFisicaDao;
    }

    public void setPessoaFisicaDao(PessoaFisicaDao pessoaFisicaDao) {
        this.pessoaFisicaDao = pessoaFisicaDao;
    }

    public DataModel<PessoaFisica> getPessoas() {
        return pessoas;
    }

    public void setPessoas(DataModel<PessoaFisica> pessoas) {
        this.pessoas = pessoas;
    }

    public Session getSessao() {
        return sessao;
    }

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

    
}
