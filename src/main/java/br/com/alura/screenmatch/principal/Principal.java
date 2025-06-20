package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner resposta = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    /*
    * Como estamos lidando com constantes e que não iremos modificar ao longo do tempo, faremos:
    * 1. Adicionar 'final' nas variáveis, declarando que não iremos modificá-las no futuro. Seguindo o padrão
    * de constantes: letras maiúsculas.
    * */
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f7de918f";

    public void exibeMenu() {
        System.out.print("Digite o nome da série que deseja buscar: ");
        var nomeDaSerie = resposta.nextLine();
        /*
        * Sempre que tivermos um espaço, trocaremos por um (+)
        *
        * Estamos instanciando um ConsumoApi dentro do método. Como esse consumoapi será usado em outras vezes
        * que estivermos trocando o endereço, deixaremos essa classe como atributo da principal.
        * */
        var json = consumoApi.obterDados(        ENDERECO + nomeDaSerie.replace(" ", "+") + API_KEY);

        /*
         * Instancio o conversor e mando ele transformar para dadosSerie e imprimo os dados da série
         * */
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        /*
         * Convertendo os dados da temporada para classe.
         * Quero buscar os dados de todas as temporadas. Então meu for irá percorrer o número de temporadas que a série tem.
         * */

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i<= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados(ENDERECO + nomeDaSerie.replace(" ", "+")
                    + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
        temporadas.forEach(System.out::println);

        /*
        * Usando lambdas
        *
        * Digitamos o nome da nossa coleção e ela já terá um método embutido
        * Então para cada temporada (t) eu vou pegar seus episódios, e percorrer a lista de episódios.
        * E, depois, para cada episodio (e) eu vou imprimir o título do episódio.
        * */
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        /*
        * Dentro de cada temporada, pego cada um dos episódios e irei aglutniar.
        * Para essa aglutinação (usar uma lista dentro de outra lista), usarei o flatmap.
        * Depois, usando o collect, coleto esses dados para uma nova lista.
        *
        * Mudei o collect para um toList porque esse toList me gera uma lista imutável, então caso eu queira adicionar
        * mais dados nessa lista de episódios, não irei conseguir, vai gerar uma exceção. O que não acontece com o collect.
        * */
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                //.collect(Collectors.toList());
                .toList();

        /*
        * Pegando os 5 episódos mais bem avaliados
        * Removendo os episódios que possuem N/A como avaliação, o que pode acontecer visto que a avaliação é uma string
        * Comparando os episódios pela avaliação.
        * Colocar em ordem decrescente
        * */
        System.out.println("\nTop 5 episódios");
        dadosEpisodios
                .stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                                .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());//para cada dados episódio, quero criar um new episodio

        episodios.forEach(System.out::println);

        /*
        * Lidando com streams e funções lambdas em conjunto
        * A stream vai fazendo operações encadeadas.
        * 1. Ordeno os nomes
        * 2. Limito a quantidade que quero
        * 3. Filtro pegando um nome(n) onde o nome comece com "N"
        * 4. Fazemos uma transformação. Após pegar o nome do Nico, que começa com N. Uso o map (para transformação) e
        *   transformo o nome para letra maiúscula
        * 5. Imprimir os dados
        * */
        /*List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");
        nomes.stream()
                .sorted()
                .limit(3)
                .filter(n -> n.startsWith("N"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);
        */
    }
}
