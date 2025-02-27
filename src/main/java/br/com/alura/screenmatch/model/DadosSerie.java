package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

/*
* Ele não consegue transformar title em titulo então preciso indicar que p title vem para o titulo.
* Usarei então o Alias da biblioteca jackson.*/
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {
}
