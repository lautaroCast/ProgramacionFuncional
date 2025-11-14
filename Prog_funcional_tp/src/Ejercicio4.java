import java.util.*;
import java.util.stream.*;

class Empleado {
    private String nombre;
    private String departamento;
    private double salario;
    private int edad;

    public Empleado(String nombre, String departamento, double salario, int edad) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public double getSalario() { return salario; }
    public int getEdad() { return edad; }
}

public class Ejercicio4 {
    public static void main(String[] args) {
        List<Empleado> empleados = Arrays.asList(
                new Empleado("Ana", "Ventas", 2500.0, 28),
                new Empleado("Luis", "TI", 3500.0, 32),
                new Empleado("Maria", "Ventas", 1800.0, 25),
                new Empleado("Carlos", "TI", 4200.0, 35),
                new Empleado("Pedro", "RH", 2200.0, 29),
                new Empleado("Laura", "TI", 3800.0, 31)
        );

        List<Empleado> empleadosBienPagados = empleados.stream()
                .filter(e -> e.getSalario() > 2000)
                .sorted(Comparator.comparing(Empleado::getSalario).reversed())
                .collect(Collectors.toList());
        System.out.println("Empleados con salario > 2000:");
        empleadosBienPagados.forEach(e ->
                System.out.println("  " + e.getNombre() + " - $" + e.getSalario())
        );

        double salarioPromedio = empleados.stream()
                .mapToDouble(Empleado::getSalario)
                .average()
                .orElse(0.0);
        System.out.println("Salario promedio general: $" + salarioPromedio);

        Map<String, Double> salarioPorDepto = empleados.stream()
                .collect(Collectors.groupingBy(
                        Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                ));
        System.out.println("Suma de salarios por departamento: " + salarioPorDepto);

        List<String> empleadosJovenes = empleados.stream()
                .sorted(Comparator.comparing(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());
        System.out.println("2 empleados más jóvenes: " + empleadosJovenes);
    }
}