package br.com.LeonardoPSouzaPortfolio.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.LeonardoPSouzaPortfolio.brewer.model.Cerveja;
import br.com.LeonardoPSouzaPortfolio.brewer.model.Origem;
import br.com.LeonardoPSouzaPortfolio.brewer.model.Sabor;
import br.com.LeonardoPSouzaPortfolio.brewer.repository.Estilos;
import br.com.LeonardoPSouzaPortfolio.brewer.service.CadastroCervejaService;
/**
 * 
 * @author leonardops
 *
 * @Controller - Aponta que a classe é um controller
 */
@Controller
public class CervejasController {
	
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;

	/**
	 * @RequestMapping - Mapeia o URL numa requisição GET
	 * @return - Retorna a pagina HTML
	 */
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	/**
	 * @RequestMapping - Mapeia o URL numa requisição POST
	 * @Valid - Valida um objeto
	 * BindingResult result - Mante os resultados
	 * Model - Para forward
	 * RedirectAttributes - Para Redirect
	 * @return - Retorna a pagina HTML
	 */
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cerveja);
		}
		
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
}