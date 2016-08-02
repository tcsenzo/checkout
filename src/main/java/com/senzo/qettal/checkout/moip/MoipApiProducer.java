package com.senzo.qettal.checkout.moip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;

@Configuration
public class MoipApiProducer {
	
	@Bean(name="moipApi")
	public API getBean() {
		Authentication auth = new BasicAuth("VXOMXSU28LXTVDG1V1L8ZLFAJX10PQYT", "ABEFG291RV8LR49QQAEAYFRYHFAY61CEFFZZGHBD");
		Client client = new Client(Client.SANDBOX, auth);
		return new API(client);
	}

}
