package upskill.daterra.services.map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Coordinates getCoordinatesStructured(
            String street,
            String city,
            String county,
            String state,
            String country,
            String postalcode) throws Exception {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search");

        if (street != null && !street.isEmpty()) {
            builder.queryParam("street", street);
        }
        if (city != null && !city.isEmpty()) {
            builder.queryParam("city", city);
        }
        if (county != null && !county.isEmpty()) {
            builder.queryParam("county", county);
        }
        if (state != null && !state.isEmpty()) {
            builder.queryParam("state", state);
        }
        if (country != null && !country.isEmpty()) {
            builder.queryParam("country", country);
        }
        if (postalcode != null && !postalcode.isEmpty()) {
            builder.queryParam("postalcode", postalcode);
        }

        builder.queryParam("format", "json");

        String url = builder.toUriString();

        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);

        if (root.isArray() && root.size() > 0) {
            JsonNode firstResult = root.get(0);
            double latitude = firstResult.get("lat").asDouble();
            double longitude = firstResult.get("lon").asDouble();
            return new Coordinates(latitude, longitude);
        } else {
            return null; // Address not found
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