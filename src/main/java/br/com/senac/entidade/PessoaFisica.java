/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.entidade;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author silvio.junior
 */
@Entity
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "idCliente")
public class PessoaFisica extends Cliente
        implements Serializable {

    @Column(nullable = false, unique = true)
    private String cnpj;
    @Column(nullable = false)
    private String inscricaoEstadual;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String rg;

    public PessoaFisica() {
    }

    public PessoaFisica(String cnpj, String inscricaoEstadual, String cpf, String rg) {
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.cpf = cpf;
        this.rg = rg;
    }

    

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
    
    
    

}
