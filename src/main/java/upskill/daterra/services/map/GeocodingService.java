package upskill.daterra.services.map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.Normalizer;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }

    public Coordinates getCoordinates(String address, String city, String country, String postalcode) throws Exception {
        String sanitizedAddress = removeAccents(address);
        String freeFormAddress = sanitizedAddress + " " + postalcode + " " + country;
        Coordinates coordinates = getCoordinatesFromNominatim(freeFormAddress);

        if (coordinates == null) {
            String cityCountryAddress = city + ", " + country;
            coordinates = getCoordinatesFromNominatim(cityCountryAddress);
        }

        return coordinates;
    }

    private Coordinates getCoordinatesFromNominatim(String queryAddress) throws Exception {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("q", queryAddress)
                .queryParam("format", "json")
                .queryParam("addressdetails", 1)
                .build()
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);

        System.out.println("Geocoding request: " + queryAddress);
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







