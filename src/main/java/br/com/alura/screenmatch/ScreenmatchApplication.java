package br.com.alura.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* A interface vai permitir que eu crie, dentro do meu método principal, algumas chamadas.
* */
@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	/*
	* O método run funciona como um método main convencional.
	* */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello, World.");
	}
}
