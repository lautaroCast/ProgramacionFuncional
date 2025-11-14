import java.util.*;
import java.util.stream.*;


class Alumno {
    private String nombre;
    private double nota;
    private String curso;

    public Alumno(String nombre, double nota, String curso) {
        this.nombre = nombre;
        this.nota = nota;
        this.curso = curso;
    }

    public String getNombre() { return nombre; }
    public double getNota() { return nota; }
    public String getCurso() { return curso; }

    @Override
    public String toString() {
        return nombre + " (" + nota + ")";
    }
}

public class Ejercicio1 {
    public static void main(String[] args) {
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Lautaro", 9.2, "A"),
                new Alumno("Leandro", 6.0, "B"),
                new Alumno("Martina", 7.0, "A"),
                new Alumno("Carlos", 8.0, "B"),
                new Alumno("Franco", 5.5, "A"),
                new Alumno("Laura", 10.0, "C")
        );

        List<String> aprobados = alumnos.stream()
                .filter(a -> a.getNota() >= 7)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Alumnos aprobados: " + aprobados);

        double promedio = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio general: %.2f%n", promedio);

        Map<String, List<Alumno>> porCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));

        List<Double> mejoresNotas = alumnos.stream()
                .map(Alumno::getNota)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("3 mejores notas: " + mejoresNotas);

        System.out.println("\nAlumnos");
        porCurso.forEach((curso, listaAlumnos) -> {
            System.out.println("Curso " + curso + ": " + listaAlumnos);
        });
    }
}