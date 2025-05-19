import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Esta clase es para realizar la petición a la API y obtener los datos de las monedas.
 */
public class ConsultaMoneda {
    public Moneda consultaMoneda(){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/3ba3ff44e90fcda2f6a6de8b/latest/USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);

        }catch(Exception e){
            throw new RuntimeException("No encontré esa moneda.");
        }
    }
}
