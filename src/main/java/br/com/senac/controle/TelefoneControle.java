/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.controle;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.TelefoneDao;
import br.com.senac.dao.TelefoneDaoImpl;
import br.com.senac.entidade.Perfil;
import br.com.senac.entidade.Telefone;
import br.com.senac.util.GeradorUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author silvio.junior
 */
@ManagedBean(name = "telefoneC")
@ViewScoped
public class TelefoneControle {

    private Telefone telefone;
    private TelefoneDao telefoneDao;
    private DataModel<Telefone> telefones;
    private Session sessao;

    private int aba;

    public TelefoneControle() {
        telefoneDao = new TelefoneDaoImpl();
    }

    public void pesquisar() {
        try {
            sessao = HibernateUtil.abrirSessao();
            List<Telefone> tels = telefoneDao
                    .pesquisarPorNumero(telefone.getNumero(), sessao);
            telefones = new ListDataModel<>(tels);
            telefone.setNumero("");
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar "
                    + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    public void excluir() {
        telefone = telefones.getRowData();

        try {
            sessao = HibernateUtil.abrirSessao();
            telefoneDao.excluir(telefone, sessao);
            Mensagem.enviarSucesso("Telefone excluido com sucesso");
        } catch (HibernateException e) {
            Mensagem.enviarErro("Erro ao excluir telefone");
        } finally {
            sessao.close();
            telefones = null;
            telefone = null;
        }
    }

    public void salvar() {
        try {
            sessao = HibernateUtil.abrirSessao();
            //telefone.setNumero(GeradorUtil.gerarNumero(11));
            telefoneDao.salvarOuAlterar(telefone, sessao);

            Mensagem.enviarSucesso(telefone.getNumero()
                    + " salvo com sucesso!!");
        } catch (HibernateException e) {
            Mensagem.enviarErro(telefone.getNumero()
                    + " erro ao salvar!!");
            System.out.println("Erro ao salvar " + e.getMessage());
        } finally {
            sessao.close();
            telefone = null;
        }
    }

    //    getters and setters
    public void prepararAlterar() {
        telefone = telefones.getRowData();
        aba = 1;

    }

    public Telefone getTelefone() {
        if (telefone == null) {
            telefone = new Telefone();
        }
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public DataModel<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(DataModel<Telefone> telefones) {
        this.telefones = telefones;
    }


    public int getAba() {
        return aba;
    }

    public void setAba(int aba) {
        this.aba = aba;
    }

}
