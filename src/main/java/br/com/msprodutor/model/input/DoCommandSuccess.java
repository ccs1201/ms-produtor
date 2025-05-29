package br.com.msprodutor.model.input;

import java.time.LocalDate;

public record DoCommandSuccess(String nome, int idade, char sexo, LocalDate dataNascimento, String path) {
}
