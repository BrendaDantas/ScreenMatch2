package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*
 * Ele não consegue transformar season em temporada então preciso indicar que a season vem para a temporada.
 * Usarei então o Alias da biblioteca jackson.*/

/*
 * Ignora o que não for encontrado no mapeamento(aqui).
 * Por default, o ignoreUnknown vem como falso, então troco para null.
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") Integer numero,
                             @JsonAlias("Episodes") List<DadosEpisodio> episodios) {
}
