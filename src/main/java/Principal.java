package main.java;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.model.Funcionario;

public class Principal {
	static List<Funcionario> funcionarios = new ArrayList<>();
	
	public static void main(String[] args) {
		popularFuncionarios();
        
		removerFuncionario("João");
        
		listarFuncionarios(funcionarios);
        
        aumentarSalarioGeral(funcionarios);
        
        listarFuncionarios(funcionarios);
        
        agruparPorFuncao(funcionarios);
        
        filtrarAniversariantes(funcionarios);
        
        filtrarMaisVelho(funcionarios);
        
        agruparAlfabeticamente(funcionarios);
        
        salarioTotalFuncionarios(funcionarios);
        
		salarioMinimoFuncionarios(funcionarios);
	}
	
	private static void popularFuncionarios() {
		funcionarios.add(new Funcionario("Maria" ,  LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João"  ,  LocalDate.of(1990,  5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio"  ,  LocalDate.of(1961,  5,  2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice" ,  LocalDate.of(1995,  1,  5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur",  LocalDate.of(1993,  3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura" ,  LocalDate.of(1994,  7,  8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,  5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena",  LocalDate.of(1996,  9,  2), new BigDecimal("2799.93"), "Gerente"));
	}
	
	private static void removerFuncionario(String nome) {
		if(funcionarios.removeIf((funcionario) -> funcionario.getNome().equals(nome))) {
			System.out.println("!!!------!!!\n" + nome + " foi removido(a) da lista de funcionários.\n!!!------!!!\n");
		};
	}
	
	private static void listarFuncionarios(List<Funcionario> funcionarios) {
		System.out.println("----------- Lista de Funcionários -----------\n");
		funcionarios.forEach(System.out::println);
		System.out.println("---------------------------------------------\n\n");
    }
	
	private static void aumentarSalarioGeral(List<Funcionario> funcionarios) {
		funcionarios.forEach(funcionario -> funcionario.aumentarSalario());
		
		System.out.println("--- Todos os salários foram reajustados em 10% ---\n");
	}
	
	private static void agruparPorFuncao(List<Funcionario> funcionarios) {
		Map<String, List<Funcionario>> mapFuncionarios = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));

		System.out.println("----------- Funcionários agrupados por função -----------\n");
		mapFuncionarios.forEach((funcao, listaFuncionario) -> {
			System.out.println("----- " + funcao + " -----");
			listaFuncionario.forEach(System.out::println);
			System.out.println("-------------------\n");
		});
	}
	
	private static void filtrarAniversariantes(List<Funcionario> funcionarios) {
		List<Funcionario> aniversariantes = funcionarios.stream()
				.filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10
						|| funcionario.getDataNascimento().getMonthValue() == 12)
				.toList();

		System.out.println("--------- Lista de aniversariantes do mês 10 e 12 ---------\n");
		aniversariantes.forEach(System.out::println);
		System.out.println("-----------------------------------------------------------\n");
	}
	
	private static void filtrarMaisVelho(List<Funcionario> funcionarios2) {
		var wrapper = new Object() { 
			Funcionario maisVelho; 
			int aux = Integer.MAX_VALUE;
		};
		
		funcionarios.stream().forEach((funcionario) -> {
			
			if(funcionario.getDataNascimento().getYear() < wrapper.aux){
				wrapper.aux = funcionario.getDataNascimento().getYear();
				wrapper.maisVelho = funcionario;
			}
		});
		
		System.out.println("--------- Funcionário mais velho ---------\n");
		System.out.println(wrapper.maisVelho.getNome() + ", " + wrapper.maisVelho.getIdade());
		System.out.println("-------------------------------------------\n\n");
	}
	
	private static void agruparAlfabeticamente(List<Funcionario> funcionarios) {
		Collections.sort(funcionarios, new Comparator<Funcionario>() {
			public int compare(Funcionario f1, Funcionario f2) {
	            return f1.getNome().compareTo(f2.getNome());
	        }
		});
		
		System.out.println("----------- Lista Alfabética de Funcionários -----------\n");
		funcionarios.forEach(System.out::println);
		System.out.println("--------------------------------------------------------\n\n");
	}
	
	private static void salarioTotalFuncionarios(List<Funcionario> funcionarios2) {
		var wrapper = new Object() {
			BigDecimal salarioTotal = BigDecimal.ZERO;
		};

		funcionarios.forEach((funcionario) -> {
			wrapper.salarioTotal = wrapper.salarioTotal.add(funcionario.getSalario());
		});

		Locale localeBR = new Locale("pt", "BR");
		NumberFormat salarioFormater = NumberFormat.getCurrencyInstance(localeBR);
		String TOTAL_FORMATADO = salarioFormater.format(wrapper.salarioTotal);

		System.out.println("------ Salario total de todos os funcionarios ------\n");
		System.out.println(TOTAL_FORMATADO);
		System.out.println("----------------------------------------------------\n\n");
	}
	
	private static void salarioMinimoFuncionarios(List<Funcionario> funcionarios2) {
		funcionarios.forEach((funcionario) -> {
			funcionario.getSalarioMinimo();
			}
		);
	}
}
