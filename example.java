package com.javaguides.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.javaguides.models.Integration;
import com.javaguides.models.LoanFullApproval;
import com.javaguides.repositories.IntegrationRepository;

@Controller
public class CreditDataController {
    @Autowired
    private IntegrationRepository integrationRepository;
    Logger logger = LoggerFactory.getLogger(CreditDataController.class);

    public String authorization() throws Exception {
        Optional<Integration> integ = integrationRepository.findById(1L);
        Integration integModel = integ.get();
        integModel.getConnectiondetails();

        String details = integModel.getConnectiondetails();
        String[] parts = details.split(";");
        String url = parts[0] + "/oauth2/v1/token";
        String username = parts[1];
        String password = parts[2];
        String client_id = parts[3];
        String client_secret = parts[4];

        // FOR TESTING
        String fakePass = "fakepassword";

        //logger.trace(url + " " + username + " " + password + " " + client_id + " " + client_secret);

        HttpRequest postRequest1 = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("client_id", client_id)
            .header("client_secret", client_secret)
            .header("content-type", "application/json")
            .POST(BodyPublishers.ofString("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}"))
            .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse1 = httpClient.send(postRequest1, BodyHandlers.ofString());
        return (postResponse1.body());
        
    }

    public String[] getCreditData() throws Exception {
        String jsonString = authorization();

        int startIndex = jsonString.indexOf("\"access_token\"") + "\"access_token\" : \"".length();
        int endIndex = jsonString.indexOf("\"", startIndex);

        // Extract the "access_token" value
        String accessToken = jsonString.substring(startIndex, endIndex);

        if (!jsonString.contains("error")) {
            logger.trace("Credit Data Token Generated: " + accessToken);

            String token = "Bearer " + accessToken;
            HttpRequest postRequest1 = HttpRequest.newBuilder()
                .uri(URI.create("https://uat-us-api.experian.com/consumerservices/prequal/v1/credit-report"))
                .header("authorization", token)
                .header("clientReferenceId", "SBMYSQL")
                .header("content-type", "application/json")
                .header("accept", "application/json")
                .POST(BodyPublishers.ofString("{\"consumerPii\":{\"primaryApplicant\":{\"name\":{\"lastName\":\"BIRKHEAD\",\"firstName\":\"NANCY\"},\"dob\":{\"dob\":\"10141938\"},\"ssn\":{\"ssn\":\"666701451\"},\"currentAddress\":{\"line1\":\"378EASTST\",\"line2\":\"\",\"city\":\"BLOOMSBURG\",\"state\":\"PA\",\"zipCode\":\"17815\"}}},\"requestor\":{\"subscriberCode\":\"2937537\"},\"permissiblePurpose\":{\"type\":\"3F\"},\"addOns\":{\"riskModels\":{\"modelIndicator\":[\"V4\"]},\"paymentHistory84\":\"Y\"}}"))
                .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse1 = httpClient.send(postRequest1, BodyHandlers.ofString());
            String[] goodCall = {"t", postResponse1.body()};
            return goodCall;
        }
        else {
            logger.error("Authorization Failed"); //indicates incorrect user/pass
            logger.trace("Authorization Failed: " + jsonString); //logs Authorization error JSON
            String[] badCall = {"f", jsonString};
            return badCall;
        }
    }
}
