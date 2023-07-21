package kz.akello.project.superapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.akello.project.superapp.json.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class WeatherRestController {
  private final ObjectMapper objectMapper;

  @GetMapping(value = "/get-weather")
  public WeatherData getWeatherAPI() {
    RestTemplate restTemplate = new RestTemplate();

    String apiUrl = "http://api.weatherapi.com/v1/current.json?key=62f28c27428f4252883193758230307&q=Almaty&aqi=no";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

    String responseBody = response.getBody();

    try {
      WeatherData weatherData = objectMapper.readValue(responseBody, WeatherData.class);

      return weatherData;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
