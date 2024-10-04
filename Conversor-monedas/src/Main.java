import java.util.Map;

public class Main {

    public static void main(String[] args) {
            Map<String, Double> tasasDeCambio = ConversorAPI.obtenerTasasDeCambio();

            Menu menu = new Menu(tasasDeCambio);
            menu.mostrarMenu();
    }
}
