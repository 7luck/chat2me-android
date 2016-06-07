package io.sevenluck.client.chat2me.client;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import io.sevenluck.client.chat2me.auth.TokenAuthentication;
import io.sevenluck.client.chat2me.common.AppConstants;

/**
 * Created by loki on 6/7/16.
 */
public class RestClient<T> {

    private ObjectMapper objectMapper = new ObjectMapper();


    public HttpResult<T> send(String url, HttpMethod method, T data) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(AppConstants.ACCEPT_HEADER, MediaType.APPLICATION_JSON_VALUE);
        if (!StringUtils.isEmpty(TokenAuthentication.getInstance().getToken())) {
            headers.add(AppConstants.XTOKEN_HEADER, TokenAuthentication.getInstance().getToken());
        }

        HttpEntity<T> request = new HttpEntity<>(data, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        restTemplate.setErrorHandler(new AndroidErrorResponseHandler());

        Log.d("REQUEST", "Send data " + data + " to url  " + url + " with method " + method + " to server");
        ResponseEntity<String> response = restTemplate.exchange(url, method, request, String.class);
        String responseBody = response.getBody();
        Log.d("RESPONSE", responseBody);
        try {
            if (RestUtil.isError(response.getStatusCode())) {
                RestException error = objectMapper.readValue(responseBody, RestException.class);
                return new HttpResult<>(error.getMessage());
            } else {
                T result = objectMapper.readValue(responseBody, new TypeReference<T>() {});
                Log.d("POST", result.toString());
                return new HttpResult<>(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
