package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Ele não consegue transformar title em titulo então preciso indicar que p title vem para o titulo.
 * Usarei então o Alias da biblioteca jackson.*/

/*
 * Ignora o que não for encontrado no mapeamento(aqui).
 * Por default, o ignoreUnknown vem como falso, então troco para null.
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode") Integer numero,
                            @JsonAlias("imdbRating") String avaliacao,
                            @JsonAlias("Released") String dataLancamento) {
}
