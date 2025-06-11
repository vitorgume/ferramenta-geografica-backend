package com.gume.mapa_dinamico_motorlub;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapaDinamicoMotorlubApplication {

	public static void main(String[] args) {
		System.setProperty("URL_BD", System.getenv("URL_BD"));
		System.setProperty("USER_BD", System.getenv("USER_BD"));
		System.setProperty("PASSWORD_BD", System.getenv("PASSWORD_BD"));
		System.setProperty("GOOGLE_API_KEY", System.getenv("GOOGLE_API_KEY"));
		System.setProperty("SECRET_KEY", System.getenv("SECRET_KEY"));

		SpringApplication.run(MapaDinamicoMotorlubApplication.class, args);
	}

}
