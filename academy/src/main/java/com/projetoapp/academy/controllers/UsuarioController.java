package com.projetoapp.academy.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.projetoapp.academy.dao.UsuarioDao;
import com.projetoapp.academy.exceptions.ServicesException;
import com.projetoapp.academy.model.Aluno;
import com.projetoapp.academy.model.Usuario;
import com.projetoapp.academy.service.ServiceUsuario;
import com.projetoapp.academy.util.Util;

@Controller
public class UsuarioController {
	
	
	@Autowired
	private ServiceUsuario serviceUsuario;
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/loginAluno");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/index");
		mv.addObject("aluno", new Aluno());
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastrar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		mv.setViewName("login/loginCadastro");
		return mv;
	}
	
	@PostMapping("salvarUsuario")
	public ModelAndView cadastrar(Usuario usuario) throws Exception {
		ModelAndView mv = new ModelAndView();
		serviceUsuario.salvarUsuario(usuario);
		mv.setViewName("login/loginAluno");
		return mv;
	}
	
	
	@PostMapping("/login")
	public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServicesException {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("usuario", new Usuario());
		
		if(br.hasErrors()) {
			mv.setViewName("login/loginAluno");
		}
		
		
		Usuario usuarioLogin = serviceUsuario.loginUsuario(usuario.getNome(), Util.md5(usuario.getSenha()));
		
			if(usuarioLogin == null || !usuarioLogin.getNome().equals(usuario.getNome())) {
				mv.addObject("msg", "Usuário não encontrado. Tente novamente.");
			} else {
				session.setAttribute("usuarioLogado", usuarioLogin);
				return index();
			}
		
		return mv;
	}
	
	
	
	
	
	
	
	
}
