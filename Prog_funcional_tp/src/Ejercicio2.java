import java.util.*;
import java.util.stream.*;

class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    public Producto(String nombre, String categoria, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
}

public class    Ejercicio2 {
    public static void main(String[] args) {
        List<Producto> productos = Arrays.asList(
                new Producto("Laptop", "Electrónicos", 1500.0, 10),
                new Producto("Mouse", "Electrónicos", 50.0, 25),
                new Producto("Silla", "Oficina", 300.0, 15),
                new Producto("Monitor", "Electrónicos", 800.0, 8),
                new Producto("Escritorio", "Oficina", 450.0, 12),
                new Producto("Tablet", "Electrónicos", 600.0, 20)
        );

        List<Producto> productosCaros = productos.stream()
                .filter(p -> p.getPrecio() > 100)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .collect(Collectors.toList());
        System.out.println("Productos con precio mayor a cien:");
        productosCaros.forEach(p -> System.out.println("  " + p.getNombre() + " - $" + p.getPrecio()));

        Map<String, Integer> stockPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)
                ));
        System.out.println("Stock por categoría: " + stockPorCategoria);

        String productosString = productos.stream()
                .map(p -> p.getNombre() + " - $" + p.getPrecio())
                .collect(Collectors.joining("; "));
        System.out.println("Productos: " + productosString);

        double promedioGeneral = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0.0);
        System.out.println("Precio promedio general: $" + promedioGeneral);

        Map<String, Double> promedioPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
        System.out.println("Precio promedio por categoría: " + promedioPorCategoria);
    }
}