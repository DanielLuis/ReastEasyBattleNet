package br.com.reasteasy.battlenet.service;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reasteasy.battlenet.vo.WowCharacter;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class BattleNetService {

	private static final Logger LOGGER = LogManager.getLogger(BattleNetService.class);


	private static final String API_KEY = "4wjr8zgwj4ywztjuhhk225tyvt9rhm36";

	private static final String WOW_DOMAIN = "https://us.api.battle.net/wow";


	public WowCharacter WOWCharacterProfile(String  realm, String characterName){

		LOGGER.info(" -----------------Metodo: D3CharacterProfile---------------");

		WowCharacter character = new WowCharacter();

		try {

//			realm = "Emerald Dream";
//			characterName = "Torddinho";



			ClientRequest request = new ClientRequest(WOW_DOMAIN+"/character/{realm}/{name}?locale=en_US&apikey="+API_KEY);
			request.accept(MediaType.APPLICATION_JSON);


			request.pathParameter("name", characterName);
			request.pathParameter("realm", realm);


			ClientResponse<WowCharacter> response = request.get(WowCharacter.class);


			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			
			character = response.getEntity();
			 
			LOGGER.info(character.toString());

			System.out.println(character.toString());
		} catch (Exception e) {

			e.printStackTrace();

		}

		
		return character;
	}

}
