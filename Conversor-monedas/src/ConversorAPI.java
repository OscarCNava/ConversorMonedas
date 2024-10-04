import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConversorAPI {

    // Método para obtener las tasas de cambio desde la API
    public static Map<String, Double> obtenerTasasDeCambio() {
        Map<String, Double> tasasDeCambio = new HashMap<>();

        try {
            String url_str = "https://v6.exchangerate-api.com/v6/e2f9975e07156262b19eaf21/latest/USD";

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Verificamos si la solicitud fue exitosa
            String req_result = jsonobj.get("result").getAsString();
            if (req_result.equals("success")) {
                JsonObject conversionRates = jsonobj.getAsJsonObject("conversion_rates");
                // Agregar las tasas de cambio al map
                tasasDeCambio.put("MXN", conversionRates.get("MXN").getAsDouble());
                tasasDeCambio.put("ARS", conversionRates.get("ARS").getAsDouble());
                tasasDeCambio.put("BRL", conversionRates.get("BRL").getAsDouble());
                tasasDeCambio.put("EUR", conversionRates.get("EUR").getAsDouble());
            } else {
                System.out.println("Error al obtener las tasas de cambio.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasasDeCambio;
    }
}