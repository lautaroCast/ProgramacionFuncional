import java.util.*;
import java.util.stream.*;

class Libro {
    private String titulo;
    private String autor;
    private int paginas;
    private double precio;

    public Libro(String titulo, String autor, int paginas, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.precio = precio;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getPaginas() { return paginas; }
    public double getPrecio() { return precio; }
}

public class Ejercicio3 {
    public static void main(String[] args) {
        List<Libro> libros = Arrays.asList(
                new Libro("Cien años de soledad", "Gabriel García Márquez", 417, 25.0),
                new Libro("1984", "George Orwell", 328, 18.0),
                new Libro("El Quijote", "Miguel de Cervantes", 863, 35.0),
                new Libro("Crimen y castigo", "Fiódor Dostoyevski", 551, 22.0),
                new Libro("Orgullo y prejuicio", "Jane Austen", 432, 20.0),
                new Libro("Rayuela", "Julio Cortázar", 736, 28.0)
        );

        List<String> titulosLargos = libros.stream()
                .filter(l -> l.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Libros largos: " + titulosLargos);

        double promedioPaginas = libros.stream()
                .mapToInt(Libro::getPaginas)
                .average()
                .orElse(0.0);
        System.out.println("Promedio de páginas: " + promedioPaginas);

        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(
                        Libro::getAutor,
                        Collectors.counting()
                ));
        System.out.println("Libros por autor: " + librosPorAutor);

        Optional<Libro> libroMasCaro = libros.stream()
                .max(Comparator.comparing(Libro::getPrecio));
        libroMasCaro.ifPresent(l ->
                System.out.println("Libro más caro: " + l.getTitulo() + " - $" + l.getPrecio())
        );
    }
}