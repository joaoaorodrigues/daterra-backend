package upskill.daterra.services.map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate = new RestTemplate(); // para fazer o pedido http
    private final ObjectMapper objectMapper = new ObjectMapper(); // para fazer o parse do objeto json

    public Coordinates getCoordinates(String address, String city, String country, String postalcode) throws Exception {
        String freeFormAddress = address + ", " + city + ", " + postalcode + ", " + country;

        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("q", freeFormAddress)
                .queryParam("format", "json")
                .build()
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);

        System.out.println("Geocoding request: " + freeFormAddress);
        System.out.println("Nominatim API URL: " + url);
        System.out.println("Nominatim API response: " + response);

        if (root.isArray() && root.size() > 0) {
            JsonNode firstResult = root.get(0);
            double latitude = firstResult.get("lat").asDouble();
            double longitude = firstResult.get("lon").asDouble();
            return new Coordinates(latitude, longitude);
        } else {
            return null;
        }
    }

    public static class Coordinates {
        public double latitude;
        public double longitude;

        public Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}