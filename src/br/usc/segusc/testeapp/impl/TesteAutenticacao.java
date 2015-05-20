package br.usc.segusc.testeapp.impl;

import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;
import br.usc.segusc.autenticacao.services.AutenticacaoFactory;

public class TesteAutenticacao {

	public static void main(String[] args) {
		//testando a autenticacao por usuario e senha
		Usuario user = new Usuario();
		user.setLogin("richard");
		user.setSenha("senha");
		try {
			user = AutenticacaoFactory.getAutenticacaoServiceImpl().autenticar(user, TipoAutenticacao.SENHA);
		} catch (AutenticacaoInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
