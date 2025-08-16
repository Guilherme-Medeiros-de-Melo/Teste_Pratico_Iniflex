package main.java.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {
	BigDecimal salario;
	String funcao;
	
	Locale localeBR = new Locale("pt","BR");
	NumberFormat salarioFormater = NumberFormat.getCurrencyInstance(localeBR);
	
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
	
	public String getIdade() {
		int idade = Integer.parseInt(Year.now().toString()) - this.dataNascimento.getYear();
		
		return idade + " anos";
	}
	
	public String getSalarioMinimo() {
		BigDecimal[] salarioResto = this.salario.divideAndRemainder(BigDecimal.valueOf(1212));
		
		int SALARIO_MINIMO_INTEIRO = salarioResto[0].intValue();
		String SALARIO_MINIMO_RESTO = salarioFormater.format(salarioResto[1]);
		
		System.out.println(this.nome + " ganha " + SALARIO_MINIMO_INTEIRO + " salário(s) mínimo com um resto de " + SALARIO_MINIMO_RESTO);
		
		return "";
	}
	
	public void aumentarSalario() {
		BigDecimal aumento = new BigDecimal(1.1);
		
		this.salario = this.salario.multiply(aumento);
	}

	@Override
	public String toString() {
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String DATA_FORMATADA = dateFormater.format(dataNascimento);
		
		String SALARIO_FORMATADO = salarioFormater.format(salario);
		
		return "Nome: " + nome + ", Data de Nascimento: " + DATA_FORMATADA + ", Salário: " + SALARIO_FORMATADO + ", Função: " + funcao;
	}
}
