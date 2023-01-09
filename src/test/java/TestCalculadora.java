import com.iteram.Aplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Aplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCalculadora {
    @LocalServerPort
    int randomServerPort;
    private ResponseEntity<Double> calcula(double primero, double segundo, String operacion) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort +
                "/api/calcula?primero=" + primero +
                "&segundo=" + segundo + "&operacion=" + operacion;
        URI uri = new URI(baseUrl);

        ResponseEntity<Double> resultado = restTemplate.getForEntity(uri, Double.class);
        return resultado;
    }

    @Test
    public void testSumaConÉxito() throws URISyntaxException {

        ResponseEntity<Double> resultado = calcula(4, 6, "suma");

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(10.0d, resultado.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testRestaConÉxito() throws URISyntaxException {

        ResponseEntity<Double> resultado = calcula(4, 6, "resta");

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals(-2.0d, resultado.getBody().doubleValue(), 0.001d);
    }
}