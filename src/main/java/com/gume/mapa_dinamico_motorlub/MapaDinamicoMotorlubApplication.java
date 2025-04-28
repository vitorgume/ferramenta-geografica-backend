package com.gume.mapa_dinamico_motorlub;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapaDinamicoMotorlubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapaDinamicoMotorlubApplication.class, args);

//		Dotenv dotenv = Dotenv.load();
//		System.setProperty("URL_BD", dotenv.get("BANCO_DADOS_URL"));
//		System.setProperty("USER_BD", dotenv.get("USER_BD"));
//		System.setProperty("PASSWORD_BD", dotenv.get("PASSWORD_BD"));
	}

}
