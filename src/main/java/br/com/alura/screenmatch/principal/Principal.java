package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.service.ConsumoApi;

import java.util.Scanner;

public class Principal {

    private Scanner resposta = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();

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
                //https://www.omdbapi.com/?t=gilmore+girls&apikey=f7de918f

    }
}
