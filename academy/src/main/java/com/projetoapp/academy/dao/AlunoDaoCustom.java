package com.projetoapp.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetoapp.academy.model.Aluno;

public interface AlunoDaoCustom extends JpaRepository<Aluno, Integer> {
	
	//@Query(value = "select a from aluno where a.status = 'ATIVO'")
	//public List<Aluno> findByAlunoControllerImpl();
	
}
