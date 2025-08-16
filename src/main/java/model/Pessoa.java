package main.java.model;

import java.time.LocalDate;

public abstract class Pessoa {
	String nome;
	LocalDate dataNascimento;
	
	public String getNome() {
		return nome;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
}
