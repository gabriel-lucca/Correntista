/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.controle;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.ProfissaoDao;
import br.com.senac.dao.ProfissaoDaoImpl;
import br.com.senac.entidade.Profissao;
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
@ManagedBean(name = "profissaoC")
@ViewScoped
public class ProfissaoControle {

    private Profissao profissao;
    private ProfissaoDao profissaoDao;
    private DataModel<Profissao> profissoes;
    private Session sessao;

    private int aba;

    public ProfissaoControle() {
        profissaoDao = new ProfissaoDaoImpl();
    }

    public void pesquisar() {
        try {
            sessao = HibernateUtil.abrirSessao();
            List<Profissao> profs = profissaoDao
                    .pesquisarPorNome(profissao.getNome(), sessao);
            profissoes = new ListDataModel<>(profs);
            profissao.setNome("");
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar "
                    + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void excluir() {
        profissao = profissoes.getRowData();
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensagem = null;

        try {
            sessao = HibernateUtil.abrirSessao();
            profissaoDao.excluir(profissao, sessao);
            profissoes = null;
            profissao = null;
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
            profissaoDao.salvarOuAlterar(profissao, sessao);
            
            Mensagem.enviarSucesso(profissao.getNome()
                    + " salvo com sucesso!!");
        } catch (HibernateException e) {
            Mensagem.enviarErro(profissao.getNome()
                    + " erro ao salvar!!");
            System.out.println("Erro ao salvar " + e.getMessage());
        } finally {
            sessao.close();
            profissao = null;
        }
    }

    public void prepararAlterar() {
        profissao = profissoes.getRowData();
        aba = 1;
    }

//    getters and setters
    public Profissao getProfissao() {
        if (profissao == null) {
            profissao = new Profissao();
        }
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public DataModel<Profissao> getProfissoes() {
        return profissoes;
    }

    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
