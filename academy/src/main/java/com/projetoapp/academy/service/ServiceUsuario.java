package com.projetoapp.academy.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoapp.academy.dao.UsuarioDao;
import com.projetoapp.academy.exceptions.CriptoExistsException;
import com.projetoapp.academy.exceptions.EmailExistsException;
import com.projetoapp.academy.exceptions.ServicesException;
import com.projetoapp.academy.model.Usuario;
import com.projetoapp.academy.util.Util;

@Service
public class ServiceUsuario {
	
	@Autowired(required = true)
	private UsuarioDao usuarioDao;
	
	public void salvarUsuario(Usuario usuario) throws Exception {
		
		try {
			
			if(usuarioDao.findByEmail(usuario.getEmail()) != null ) {
				
				throw new EmailExistsException("Já existe um e-mail cadastrado para: " + usuario.getEmail());
				
			}
			
			usuario.setSenha(Util.md5(usuario.getSenha()));
			
			
			
		} catch (NoSuchAlgorithmException e) {
			
			throw new CriptoExistsException("Erro na criptografia da senha");
		
		}
		
		usuarioDao.save(usuario);
		
		
	}
	
	
	
	public Usuario loginUsuario(String usuario, String senha) throws ServicesException {
		
		
		Usuario usuarioLogin = usuarioDao.buscarLogin(usuario, senha);
		return usuarioLogin;
		
		
		
		
	}
	
}
