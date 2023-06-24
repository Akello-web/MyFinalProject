package kz.akello.project.superapp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.akello.project.superapp.json.ExchangeRates;
import kz.akello.project.superapp.maper.CurrencyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyMapper currencyMapper;
    @GetMapping("/exchange-rate")
    public String getExchangeRate(Model model) throws JsonProcessingException {
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set the URL of the external API
        String apiUrl = "https://v6.exchangerate-api.com/v6/c9d48243dcc1bbe061817d0f/latest/USD";

        // Set the headers for the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create an HttpEntity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the GET request
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        // Get the response body
        String responseBody = response.getBody();

        // Add the response body as a model attribute
//        model.addAttribute("exchangeRateDetails", responseBody);

//        JSONPObject jsonObject = new JSONPObject(responseBody, exchangeRates);

        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRates exchangeRates = objectMapper.readValue(responseBody, ExchangeRates.class);
        System.out.println(exchangeRates.getConversion_rates().get("KZT"));

//        currencyMapper.mapJsonToJavaObject(jsonObject, exchangeRates);


        // Return the name of the HTML template file (without the extension)
        return "<h1>"+exchangeRates.getConversion_rates().get("KZT").toString()+"</h1>";
    }
}
