package br.com.reasteasy.battlenet.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import br.com.reasteasy.battlenet.service.BattleNetService;
import br.com.reasteasy.battlenet.support.BaseController;
import br.com.reasteasy.battlenet.vo.WowCharacter;

@Controller
@RequestMapping("/wow")
public class WowController extends BaseController{
	
//	private static final Logger LOGGER = LogManager.getLogger(WowController.class);
	
	private static final String JSP_PROFILE = "/wow/profile";
	
	@Autowired
	private BattleNetService battleNetService;
	
	@GET
	@Consumes("application/json")
	@RequestMapping("/character")
	public ModelAndView form(Model model,String realm,String characterName) {
		 
		
		List<WowCharacter> chars = Lists.newArrayList();
		
		chars.add(battleNetService.WOWCharacterProfile("Emerald Dream", "Torddinho"));
		chars.add(battleNetService.WOWCharacterProfile("Emerald Dream", "MestreAbacat"));
		chars.add(battleNetService.WOWCharacterProfile("Emerald Dream", "CaraDeBoi"));
		
		
		
		 model.addAttribute("chars",chars);

		 
		 
		 return new ModelAndView(JSP_PROFILE);
	}


}
