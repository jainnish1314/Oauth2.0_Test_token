package com.example.testDatabase.controller;

import com.example.testDatabase.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UserController {

    String clientId = "586568826272-9vtmtd3vtklnkd93pajijodbi53lqg53.apps.googleusercontent.com";
    String redirectUri = "http://localhost:8085/callback";
    String authorizationEndpoint = "https://accounts.google.com/o/oauth2/v2/auth";
    String tokenEndpoint = "https://oauth2.googleapis.com/token";
    String clientSecret = "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";
    String Token;
    String url ="https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=586568826272-9vtmtd3vtklnkd93pajijodbi53lqg53.apps.googleusercontent.com&redirect_uri=http://localhost:8085/callback&scope=email";



    private final ServiceImpl service;

    public UserController(ServiceImpl service) {
        this.service = service;
    }
@GetMapping("/home")
public RedirectView user(@AuthenticationPrincipal OAuth2User principal) {
    return new RedirectView(url);
}
@GetMapping("/callback")
public String callback(@RequestParam("code") String code, @AuthenticationPrincipal OAuth2User oAuth2User) throws JSONException, JsonProcessingException {
String isuserSaved =service.SaveUser(oAuth2User);
if(isuserSaved.equals("User Saved")){
    Token = exchangeCodeForToken(code);
        System.out.println(Token);
        org.json.JSONObject jsonObject = new JSONObject(Token);
        String IdToken = (String) jsonObject.get("id_token");
        System.out.println(IdToken);
        return IdToken;
}
else {
    return isuserSaved;
}
}
    public String exchangeCodeForToken(String code) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("code", code);
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("redirect_uri", redirectUri);
        requestBody.add("grant_type", "authorization_code");

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenEndpoint, requestEntity, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return "Error occurred";
        }
    }
}
