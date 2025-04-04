package com.gimini.AI.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AiService {

    // API URL and Key from properties
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public AiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAns(String question) {
        // Construct the request payload
        Map<String, Object> requestBody = Map.of(
            "contents", List.of(
                Map.of("parts", List.of(
                    Map.of("text", question)
                ))
            )
        );

        // Construct the full API URL
        String fullApiUrl = geminiApiUrl + "?key=" + geminiApiKey;

        // Make API call to Gemini API
        String response = webClient.post()
            .uri(fullApiUrl) // Use full URL
            .header("Content-Type", "application/json")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        // Return response
        return response;
    }
}
