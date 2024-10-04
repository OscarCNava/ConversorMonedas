import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Map<String, Double> tasasDeCambio; // Map para almacenar las tasas de cambio

    // Constructor
    public Menu(Map<String, Double> tasasDeCambio) {
        this.tasasDeCambio = tasasDeCambio;
    }

    // Método para mostrar el menú
    public void mostrarMenu() {
        int opc = -1; // Inicializamos en -1 para que entre en el bucle.
        Scanner teclado = new Scanner(System.in);

        while (opc != 0) {
            System.out.println("*********************************");
            System.out.println("Bienvenido al Conversor de Monedas");
            System.out.println("0) Salir");
            System.out.println("1) Dólar - - - - - -> Peso Mexicano");
            System.out.println("2) Peso Mexicano - -> Dólar");
            System.out.println("3) Dólar - - - - - -> Peso Argentino");
            System.out.println("4) Peso Argentino --> Dólar");
            System.out.println("5) Dólar - - - - - -> Real Brasileño");
            System.out.println("6) Real Brasileño --> Dólar");
            System.out.println("7) Dólar - - - - - -> Euro");
            System.out.println("8) Euro - - - - - --> Dólar");
            System.out.println("Seleccione la opción deseada:");
            System.out.println("*********************************");

            try {
                opc = Integer.parseInt(teclado.nextLine());

                // Validamos que la opción esté en el rango permitido
                if (opc >= 0 && opc <= 8) {
                    switch (opc) {
                        case 0:
                            System.out.println("Gracias por usar el conversor de monedas...");
                            break;
                        case 1:
                            convertir("USD", "MXN");
                            break;
                        case 2:
                            convertir("MXN", "USD");
                            break;
                        case 3:
                            convertir("USD", "ARS");
                            break;
                        case 4:
                            convertir("ARS", "USD");
                            break;
                        case 5:
                            convertir("USD", "BRL");
                            break;
                        case 6:
                            convertir("BRL", "USD");
                            break;
                        case 7:
                            convertir("USD", "EUR");
                            break;
                        case 8:
                            convertir("EUR", "USD");
                            break;
                    }
                } else {
                    System.out.println("Opción no válida. Seleccione una opción válida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }

        teclado.close();
    }


    // Método para realizar la conversión
    private void convertir(String monedaOrigen, String monedaDestino) {
        if (tasasDeCambio.containsKey(monedaOrigen) || tasasDeCambio.containsKey(monedaDestino)) {
            Scanner teclado = new Scanner(System.in);
            double cantidad = 0;

            // Pedimos la cantidad al usuario
            while (true) {
                try {
                    System.out.println("Ingrese la cantidad en " + monedaOrigen + ":");
                    cantidad = Double.parseDouble(teclado.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Ingrese un número.");
                }
            }

            double resultado;

            // Si es de MXN, ARS, BRL, EUR a USD, invertimos la tasa
            if (monedaDestino.equals("USD")) {
                double tasa = tasasDeCambio.get(monedaOrigen);
                resultado = cantidad / tasa;
            } else if (monedaOrigen.equals("USD")) {
                double tasa = tasasDeCambio.get(monedaDestino);
                resultado = cantidad * tasa;
            } else {
                System.out.println("No se puede realizar la conversión entre " + monedaOrigen + " y " + monedaDestino);
                return;
            }

            // Mostramos el resultado de la conversión con dos decimales
            System.out.printf("%.2f %s equivale a %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);
        } else {
            System.out.println("Tasa de cambio no disponible para " + monedaOrigen + " o " + monedaDestino);
        }
    }
}
