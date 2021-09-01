package com.logicals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EmployeeStreamDemo {
	public static List<Employee> employeeList = new ArrayList<Employee>();

	public static void main(String[] args) {
		employeeList.add(new Employee(111L, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122L, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133L, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144L, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155L, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166L, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 18508.0));
		employeeList.add(new Employee(177L, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188L, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199L, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200L, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211L, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222L, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233L, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244L, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255L, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266L, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277L, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		// Query 1 : How many male and female employees are there in the organization ?
		// method1();

		// Query 2 : Print the name of All departments in the organization
		// method2();

		// Query 3 : What is the average age of Male and Female Employees?
		// method3();

		// Query 4 : Get the details of Highest Paid employee in the organization?
		// method4();

		// Query 4.1 : Get the details of Highest Paid Female employee in the organization?
		// methodFourPointOne();

		// Query 5 : Get names of all employee who joined after 2015?
		// method5();
		
		// Query 6 : Count the number of employees in each department
		//method6();
		
		// Query 7 : What is the average salary of each department
		//method7();
		
		// Query 8 : Get the details of youngest male employee in the product development department
		//method8();
		
		//Query 8.1 : Get the details of youngest male employee in company
		//methodEightPointOne();
		
		
		//Query 9 : Who has the most working experience in the organization.
		//method9();
		
		//Query 10 : How may male and female employees are there in the sales and marketing team.
		//method10();
		
		//Query 11 : What is the average salary of male and female employees.
		//method11();
		
		//Query 12 : List down the name of all employees in Each department
		//method12();
		
		//Query 13 : What is the average salary and total salary of the whole organization.
		//method13();
		
		//Query 14 : Seperate the employees who are younger or equal to 25 years from those employees who are older than 25 years
		//method14();
		
		//Query 15 : Who is the oldest employee in organization? What is his age and which department he belongs to
		method15();
		
	}

	private static void method15() {
		System.out.println("Query 15 : Who is the oldest employee in organization? What is his age and which department he belongs to");
			Optional<Employee> emp = employeeList.stream()
				.max(Comparator.comparingInt(Employee :: getAge));
			
			System.out.println("The most Oldest Emp in Organizatino is \n" + emp.get().toString());
	}

	private static void method14() {
		
		//My Solution ===> Wrong one requiremently
		List<Employee> emp =	employeeList.stream()
				.filter(e -> e.getAge() <= 25)
				.collect(Collectors.toList());
		//emp.forEach(e -> System.out.println(e.getName()));
		
		//But we want 2 list one is for >=25 and others for > 25
		Map<Boolean, List<Employee>> partitionEmployeeByAge =  employeeList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 25));
		
		/*
		 		Here return map will be something like this
		 		1)  Key - True, value - ListOfEmployees having age greater than 25 //the Condition will be treated as true Key in Map
		 		2) Key - false, value - ListOfEmployees having age 25 or less than 25
		 */
		
		Set<Entry<Boolean, List<Employee>>> entrySet = partitionEmployeeByAge.entrySet();
		
		for(Entry<Boolean, List<Employee>> entry : entrySet) {
			if(entry.getKey()) {
				System.out.println("Employees older than 25 years");
			} else {
				System.out.println("Employees younger than or equal to 25 years");
			}
			System.out.println("-------------------------------------------------------");
			List<Employee> list = entry.getValue();
			
			for(Employee e : list) {
				System.out.println(e.getName());
			}
		
		}
	}

	private static void method13() {
		System.out.println("Query 13 : What is the average salary and total salary of the whole organization.");
		/* 
		 Double average = employeeList.stream()
			.collect(Collectors.averagingDouble(Employee :: getSalary));
			System.out.println(average);
		*/
		
		DoubleSummaryStatistics stats = employeeList.stream()
				.collect(Collectors.summarizingDouble(Employee :: getSalary));
		
		System.out.println("Average Salary: " + stats.getAverage());
		System.out.println("Min Salary: " + stats.getMin());
		System.out.println("Max Salary: " + stats.getMax());
		System.out.println("Total Salary: " + stats.getSum());
	}

	private static void method12() {
		System.out.println("Query 12 : List down the name of all employees in Each department");

		Map<String, List<Employee>> emp = employeeList.stream()
				.collect(Collectors.groupingBy(Employee :: getDepartment));
		
		Set<Entry<String, List<Employee>>> entrySet = emp.entrySet();
		
		for(Entry<String, List<Employee>> entry : entrySet) {
			System.out.println(entry.getKey() + " : ");
			System.out.println("---------------------");
			List<Employee> list = entry.getValue();
			list.forEach(i-> System.out.println(i.getName()));
			System.out.println();
		}
	}

	private static void method11() {
		System.out.println("Query 11 : What is the average salary of male and female employees.");
	    Map<String, Double> genderWiseSalary =employeeList.stream()
				.collect(Collectors.groupingBy(Employee :: getGender, Collectors.averagingDouble(Employee :: getSalary)));
	    
	    System.out.println(genderWiseSalary);
	}

	private static void method10() {
		System.out.println("Query 10 : How may male and female employees are there in the sales and marketing team.");
		Map<String, Long> empListGenderwise = employeeList.stream()
				.filter(e -> e.getDepartment().equals("Sales And Marketing"))
				.collect(Collectors.groupingBy(Employee :: getGender, Collectors.counting()));
		
		Set<Entry<String, Long>> entrySet = empListGenderwise.entrySet();
		
		for(Entry set : entrySet) {
			System.out.println(set.getKey() + " : " + set.getValue());
		}
	}
		
	private static void method9() {
		System.out.println("Query 9 : Who has the most working experience in the organization.");
		
		/*
		Optional<Employee> mostExperience = employeeList.stream()
				.min(Comparator.comparing(Employee :: getYearOfJoining));
		*/
		
		//Right way
		Optional<Employee> mostExperience =  employeeList.stream()
			.sorted(Comparator.comparingInt(Employee :: getYearOfJoining)).findFirst();
		System.out.println("Most working experience in the organization is : " + mostExperience.get().getName());
	}

	private static void methodEightPointOne() {
		System.out.println("Query 8.1 : Get the details of youngest employee in company");
		Optional<Employee> yongestEmp = employeeList.stream()
				.min(Comparator.comparing(Employee :: getAge));
		
		System.out.println("Yongest Emp in Company is " + yongestEmp.get().getName() + " of Age " + yongestEmp.get().getAge());
	}
	
	private static void method8() {
		System.out.println("Query 8 : Get the details of youngest male employee in the product development department");
		Optional<Employee> youngestEmp = employeeList.stream()
				.filter(e -> e.getDepartment().equals("Product Development"))
				.min(Comparator.comparing(Employee:: getAge));
		
		System.out.println("The Youngest Employee is in Product Developement Department" + youngestEmp.get().getName() + " of age " + youngestEmp.get().getAge());
		
	}

	private static void method7() {
		System.out.println("Query 7 : What is the average salary of each department");
		Map<String, Double> deptWiseAvgSal = employeeList.stream()
			.collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.averagingDouble(Employee :: getSalary)));
		
		Set<Entry<String, Double>> entrySet = deptWiseAvgSal.entrySet();
		for(Entry<String, Double> entry : entrySet) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
	}

	private static void method6() {
		System.out.println("Query 6 : Count the number of employees in each department");
		
		Map<String, Long> departmentWiseEmployeeCount = employeeList.stream()
			.collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.counting()));
		
		Set<Entry<String, Long>> entrySet = departmentWiseEmployeeCount.entrySet();
		for (Entry<String, Long> entry: entrySet) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
	}

	private static void method5() {
		System.out.println("Query 5 :  Get names of all employee who joined after 2015?");
		
		//Printing the names
		/*
		 * employeeList.stream() .filter(e -> e.getYearOfJoining() > 2015) .map(Employee
		 * :: getName).forEach(System.out :: println);
		 */
		
		//Getting names in list/array then printing
		List<String> s = employeeList.stream().
				filter(e -> e.getYearOfJoining() > 2015)
				.map(Employee::getName)
				.collect(Collectors.toList());
		
		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.get(i));
		}
	}

	private static void methodFourPointOne() {
		System.out.println("Query 4.1 : Get the details of Highest Paid Female employee in the organization?");
		Optional<Employee> emp = employeeList.stream().filter(e -> e.getGender().equals("Female"))
				.max(Comparator.comparingDouble(Employee::getSalary));

		System.out.println("Employee Name: " + emp.get().getName() + ", Gender: " + emp.get().getGender() + ", Salary: "
				+ emp.get().getSalary());
	}

	private static void method4() {
		System.out.println("Query 4 : Get the details of Highest Paid employee in the organization?");
		Optional<Employee> employee = employeeList.stream()
				.collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
		System.out.println("Employee Name: " + employee.get().getName() + " - Salary: " + employee.get().getSalary());
	}

	private static void method3() {
		System.out.println("Query 3 :  What is the average age of Male and Female Employees?");
		Map<String, Double> averageAgeOfMaleAndFemale = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println(averageAgeOfMaleAndFemale);
	}

	private static void method2() {
		System.out.println("Query 2 : Print the name of All departments in the organization");
		employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);
	}

	private static void method1() {
		System.out.println("Query1 : How many male and female employees are there in the organization?");
		Map<String, Long> numberOfMaleAndFemaleEmployees = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(numberOfMaleAndFemaleEmployees);
	}

}
