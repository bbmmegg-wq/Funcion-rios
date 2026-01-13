package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter full file path :");
		String path = sc.nextLine();
		
		System.out.println("Enter salary: ");
		Double salary= sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader (new FileReader(path))){
			String line= br.readLine();
			
			List<Employee> emp = new ArrayList<>();
			
			while(line!=null) {
				String[] vet = line.split(",");
				emp.add(new Employee(vet[0], vet[1], Double.parseDouble(vet[2])));
				line= br.readLine();
				
			}
			
			
			
			
			List<String>filter = emp.stream()
					.filter(x->x.getSalary()>salary)
					.map(x->x.getEmail())
					.sorted()
					.collect(Collectors.toList());
			
			System.out.printf("Email of people whose salary is more than "
					+ String.format("%.2f", salary)+ ": \n");
			filter.forEach(System.out::println);
			
			Double soma = emp.stream()
					.filter(e ->e.getName().startsWith("M"))
					.map(x -> x.getSalary())
					.reduce(0.0,(x, y ) ->x + y);
			
			System.out.printf("Sum of salary of people whose name starts with 'M': "+ String.format("%.2f", soma));	
		}
		catch(IOException i) {
			System.out.printf("Error "+ i.getMessage());
		}
		
		
		
		
		
		sc.close();
	}

}
