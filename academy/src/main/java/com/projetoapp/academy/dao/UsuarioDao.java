package com.projetoapp.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoapp.academy.model.Usuario;


public interface UsuarioDao extends JpaRepository<Usuario, Long> {

}
