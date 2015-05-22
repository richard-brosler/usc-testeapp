package br.usc.segusc.testeapp.impl;

import java.util.Scanner;

import br.usc.segusc.autenticacao.entity.TipoAutenticacao;
import br.usc.segusc.autenticacao.entity.Usuario;
import br.usc.segusc.autenticacao.exception.AutenticacaoInvalidaException;
import br.usc.segusc.autenticacao.exception.AutenticacaoUserBlockedException;
import br.usc.segusc.autenticacao.services.AutenticacaoFactory;

public class TesteAutenticacao {

	private static Scanner scn;

	public static void main(String[] args) throws AutenticacaoInvalidaException {
		scn = new Scanner(System.in);
		//testando a autenticacao por usuario e senha
		Usuario user = new Usuario();
		int opc;
		System.out.println("Digite a opção de testes que deseja:");
		System.out.println("1 - Autenticação por Usuário -  Senha");
		System.out.println("2 - Autenticação por Biometria");
		System.out.println("Escolha sua opção:");

		opc = scn.nextInt();
		
		switch (opc){
		case 1 : 
			while (true){
				System.out.println("Digite o Login:");
				user.setLogin(scn.next());
				System.out.println("Digite a Senha:");
				user.setSenha(scn.next());
				try {
					user = AutenticacaoFactory.getAutenticacaoServiceImpl().autenticar(user, TipoAutenticacao.SENHA);
					break;
				} catch (AutenticacaoUserBlockedException e) {
					System.out.println(e.getMessage());
					throw e;
				} catch (AutenticacaoInvalidaException e) {
					System.out.println(e.getMessage());
				}
			}
			break;
		case 2:
			while (true){
				System.out.println("Digite o Login:");
				user.setLogin(scn.next());
				System.out.println("Digite a matriz 4x4 representativa digital:");
				int [][] bio = new int[4][4];
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						bio[i][j] = scn.nextInt();
				user.setBiometria(bio);
				try {
					user = AutenticacaoFactory.getAutenticacaoServiceImpl().autenticar(user, TipoAutenticacao.BIOMETRIA);
					break;
				} catch (AutenticacaoUserBlockedException e) {
					System.out.println(e.getMessage());
					throw e;
				} catch (AutenticacaoInvalidaException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		if (opc==1 || opc==2){
			System.out.println("Perfil: "+user.getPerfil().getNome());
			System.out.println("Permissoes: "+user.getPermissao().getDescricao());
		}
	}

}
