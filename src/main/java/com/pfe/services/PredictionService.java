package com.pfe.services;

import com.pfe.entities.PatientData;
import com.pfe.entities.PredictionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PredictionService {
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(PredictionService.class); // Ajoutez cette ligne

    @Autowired
    public PredictionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Double> getPrediction(PatientData patientData) {
        String apiUrl = "http://localhost:5000/predict";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PatientData> requestEntity = new HttpEntity<>(patientData, headers);
        logger.info("Sending request to API: {}", apiUrl);
        logger.info("Request body: {}", patientData);

        try {
            ResponseEntity<PredictionResponse> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, PredictionResponse.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                PredictionResponse predictionResponse = responseEntity.getBody();
                if (predictionResponse != null) {
                    return predictionResponse.getPrediction();
                }
            }
        } catch (Exception e) {
            logger.error("Error while getting prediction from API", e);
        }

        return null;
    }
}
