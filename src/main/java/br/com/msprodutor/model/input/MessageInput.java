package br.com.msprodutor.model.input;

import java.time.LocalDate;

public record MessageInput(String nome, int idade, char sexo, LocalDate dataNascimento, String path) {
}
