package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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

		/*
		* Convertendo os dados da temporada para classe.
		* Quero buscar os dados de todas as temporadas. Então meu for irá percorrer o número de temporadas que a série tem.
		* */

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i<= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=f7de918f");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);
	}
}
