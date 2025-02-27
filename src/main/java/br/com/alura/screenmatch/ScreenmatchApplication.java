package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
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
		ConsumoApi consumoApi = new ConsumoApi();

		/*
		* Consumo API, obtenho os dados da série e atribuo para a variável json
		* */
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=f7de918f");
		System.out.println(json);

		/*
		* Instancio o conversor e mando ele transformar para dadosSerie e imprimo os dados da série
		* */
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=f7de918f");

		/*
		* Usando a classe genérica para converter também os dados dos episódios.
		* */
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
	}
}
