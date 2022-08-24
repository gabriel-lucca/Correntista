/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.util;

/**
 *
 * @author silvio.junior
 */
public class GeradorUtil {
    
    public static String gerarNome(){
        String[] nomes = {"José", "Maria", "João", "Silvio",
            "Leonardo", "Pedro", "Rafael", "Sabrina", "Otavio",
        "Alexsandro", "Alison", "Bruno", "Gabriel", "Gustavo",
        "André", "Helena", "Douglas"};
        int indice = (int) (Math.random() * nomes.length);
        return nomes[indice];
    }
    
    public static String gerarNumero(int qtd) {
        String numeroAleatorio = "";
        for (int i = 0; i < qtd; i++) {
            numeroAleatorio += (int)(Math.random() * 10);
        }
        return numeroAleatorio;
    }
    
    //99.999.999/9999-99
    public static String gerarCnpj(){
        return gerarNumero(2) + "." + gerarNumero(3) + "."
             + gerarNumero(3) + "/" + gerarNumero(4) + "-"
             + gerarNumero(2);
    }
    
    
    public static String gerarSenha() {
         String[] letras = { "0", "1", "b", "2", "4", "5", "6", "7", "8",
	                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
	                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
	                "x", "y", "z"};
         int indice;
         String senha = "";
         for (int i = 0; i < 5; i++) {
            indice = (int) (Math.random() * letras.length);
            senha += letras[indice];
        }
         
        return senha;
    }
    
}
