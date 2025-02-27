package br.com.alura.screenmatch.service;

public interface IConverteDados {
    /*
    * Como não sei qual será o retorno do método, usarei o Generics.
    * Também passarei como parâmetro o tipo de dado que eu quero receber.
    * Então receberei um json e retornarei o dado de um tipo a ser especificado ao chamar o método.
    * */
    <T> T obterDados(String json, Class<T> classe);
}
