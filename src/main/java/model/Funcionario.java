package main.java.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {
	BigDecimal salario;
	String funcao;
	
	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	@Override
	public String toString() {
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String DATA_FORMATADA = dateFormater.format(dataNascimento);
		
		Locale localeBR = new Locale("pt","BR");
		NumberFormat salarioFormater = NumberFormat.getCurrencyInstance(localeBR);
		String SALARIO_FORMATADO = salarioFormater.format(salario);
		
		return "Nome: " + nome + ", Data de Nascimento: " + DATA_FORMATADA + ", Salário: " + SALARIO_FORMATADO + ", Função: " + funcao;
	}
	
	public void aumentarSalario() {
		BigDecimal aumento = new BigDecimal(1.1);
		
		this.salario = this.salario.multiply(aumento);
	}
	
	
}
