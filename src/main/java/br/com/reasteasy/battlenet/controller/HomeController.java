package br.com.reasteasy.battlenet.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.reasteasy.battlenet.support.BaseController;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

	@RequestMapping("/form")
	public ModelAndView form(Model model) {
		return new ModelAndView("/home/form");
	}

}