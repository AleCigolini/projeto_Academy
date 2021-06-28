package com.projetoapp.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoapp.academy.dao.UsuarioDao;
import com.projetoapp.academy.model.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired(required = true)
	//private UsuarioDao usuarioDao;
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/loginAluno");
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/loginCadastro");
		return mv;
	}
	
	/*@PostMapping("salvarUsuario")
	public ModelAndView cadastrar(Usuario usuario) {
		ModelAndView mv = new ModelAndView();
		usuarioDao.save(usuario);
		mv.setViewName("redirect:/index");
		return mv;
	}*/
	
	
	
	
	
	
	
	
}
