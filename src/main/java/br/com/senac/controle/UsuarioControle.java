/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.controle;

import br.com.senac.dao.HibernateUtil;
import br.com.senac.dao.PerfilDao;
import br.com.senac.dao.PerfilDaoImpl;
import br.com.senac.dao.UsuarioDao;
import br.com.senac.dao.UsuarioDaoImpl;
import br.com.senac.entidade.Perfil;
import br.com.senac.entidade.Usuario;
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
@ManagedBean(name = "usuarioC")
@ViewScoped
public class UsuarioControle {

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private DataModel<Usuario> usuarios;
    private Session sessao;
    private List<SelectItem> perfis;
    private Perfil perfil;

    private int aba;

    public UsuarioControle() {
        usuarioDao = new UsuarioDaoImpl();
        pesquisarPerfilComboBox();
    }

    private void pesquisarPerfilComboBox() {
        try {
            sessao = HibernateUtil.abrirSessao();
            PerfilDao perfilDao = new PerfilDaoImpl();
            List<Perfil> pers = perfilDao.pesquisarTodo(sessao);
            perfis = new ArrayList<>();
            for (Perfil per : pers) {
                perfis.add(new SelectItem(per.getId(), per.getNome()));
            }
            
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar " + " comboBox perfil" + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void pesquisar() {
        try {
            sessao = HibernateUtil.abrirSessao();
            List<Usuario> profs = usuarioDao
                    .pesquisarPorNome(usuario.getNome(), sessao);
            usuarios = new ListDataModel<>(profs);
            usuario.setNome("");
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar "
                    + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void excluir() {
        usuario = usuarios.getRowData();

        try {
            sessao = HibernateUtil.abrirSessao();
            usuarioDao.excluir(usuario, sessao);
            Mensagem.enviarSucesso("Usuario excluido com sucesso");
        } catch (HibernateException e) {
            Mensagem.enviarErro("Erro ao excluir usuario");
        } finally {
            sessao.close();
            usuarios = null;
            usuario = null;
        }
    }

    public void salvar() {
        try {
            sessao = HibernateUtil.abrirSessao();
            usuario.setSenha(GeradorUtil.gerarSenha());
            usuarioDao.salvarOuAlterar(usuario, sessao);

            Mensagem.enviarSucesso(usuario.getNome()
                    + " salvo com sucesso!!");
        } catch (HibernateException e) {
            Mensagem.enviarErro(usuario.getNome()
                    + " erro ao salvar!!");
            System.out.println("Erro ao salvar " + e.getMessage());
        } finally {
            sessao.close();
            usuario = null;
        }
    }

    //    getters and setters
    public void prepararAlterar() {
        usuario = usuarios.getRowData();
        perfil = usuario.getPerfil();
        aba = 1;
        
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public DataModel<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(DataModel<Usuario> usuarios) {
        this.usuarios = usuarios;
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

    public List<SelectItem> getPerfis() {
        return perfis;
    }

    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
