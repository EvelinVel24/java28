import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject; // Asegúrate de tener la dependencia de JSON

public class GeoLocalizacion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Paso 1: Captura de datos básicos del usuario
        System.out.println("Ingrese el nombre del usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese el correo del usuario: ");
        String correo = sc.nextLine();

        // Paso 2: Consumir la API de geolocalización
        String apiUrl = "https://freegeoip.app/json/";

        try {
            // Crear un URI y convertirlo a URL para evitar el uso del constructor obsoleto
            URI uri = URI.create(apiUrl);
            URL url = uri.toURL(); // Convertir URI a URL

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();

            // Convertir la respuesta en un objeto JSON
            JSONObject jsonObject = new JSONObject(content.toString());

            // Paso 3: Obtener los datos de la ubicación
            String ip = jsonObject.getString("ip");
            String country = jsonObject.getString("country_name");
            String region = jsonObject.getString("region_name");
            String city = jsonObject.getString("city");
            String zipCode = jsonObject.getString("zip_code");
            String latitude = jsonObject.getString("latitude");
            String longitude = jsonObject.getString("longitude");

            // Mostrar los datos del usuario junto con la información de la ubicación
            System.out.println("\nInformación del usuario:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Correo: " + correo);
            System.out.println("\nInformación geográfica:");
            System.out.println("IP: " + ip);
            System.out.println("País: " + country);
            System.out.println("Región: " + region);
            System.out.println("Ciudad: " + city);
            System.out.println("Código postal: " + zipCode);
            System.out.println("Latitud: " + latitude);
            System.out.println("Longitud: " + longitude);

        } catch (Exception e) {
            System.out.println("Error al consumir la API: " + e.getMessage());
        }

        sc.close();
    }
}
