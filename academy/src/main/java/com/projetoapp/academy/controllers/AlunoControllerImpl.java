package com.projetoapp.academy.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoapp.academy.dao.AlunoDaoCustom;
import com.projetoapp.academy.model.Aluno;

@Controller
public class AlunoControllerImpl {
	
	@Autowired
	private AlunoDaoCustom alunoDaoCustom;
	
	@GetMapping("/inserirAluno")
	public ModelAndView insertAlunos(Aluno aluno) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/formAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
		
	}
	
	@PostMapping("InsertAluno")
	public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
		
		ModelAndView mv = new ModelAndView();
		if(br.hasErrors()) {
			mv.setViewName("Aluno/formAluno");
			mv.addObject("aluno");
		} else {
			
		
			mv.setViewName("redirect:/alunos-adicionados");
			alunoDaoCustom.save(aluno);
		}
		
		return mv;
	
	}
	
	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/listAlunos");
		mv.addObject("alunosList", alunoDaoCustom.findAll());
		return mv;
	}
	
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alterar");
		Aluno aluno = alunoDaoCustom.getOne(id);
		mv.addObject("aluno", aluno);
		return mv;
		
		
		
	}
	
	@PostMapping("alterar")
	public ModelAndView alterar(Aluno aluno) {
		
		ModelAndView mv = new ModelAndView();
		alunoDaoCustom.save(aluno);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) {
		
		alunoDaoCustom.deleteById(id);
		return "redirect:/alunos-adicionados";
		
	}
	
	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/filtroAlunos");
		return mv;
	}
	
	/*@GetMapping("alunos-ativos")
	public ModelAndView listaAlunosAtivos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/alunos-ativos");
		mv.addObject("listaAluno", alunoDaoCustom.findByAlunoControllerImpl());
		return mv;
	}*/
	
	
	
	
	
	
	
	
}
