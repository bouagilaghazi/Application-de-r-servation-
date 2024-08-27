package fr.uphf.serveurregistre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer ;
@EnableEurekaServer
@SpringBootApplication
public class ServeurRegistresApplication {

	public static void main(String [] args) {
		SpringApplication.run(ServeurRegistresApplication.class, args);

	}

}
