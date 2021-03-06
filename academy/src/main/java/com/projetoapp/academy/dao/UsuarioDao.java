package com.projetoapp.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetoapp.academy.model.Usuario;


public interface UsuarioDao extends JpaRepository<Usuario, Long> {
	
	@Query("select e from Usuario e where e.email = :email ")
	public Usuario findByEmail(String email);
	
	@Query("select u from Usuario u where u.nome = :usuario and u.senha = :senha ")
	public Usuario buscarLogin(String usuario, String senha);
}
