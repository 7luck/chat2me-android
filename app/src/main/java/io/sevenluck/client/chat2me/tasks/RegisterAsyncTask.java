package io.sevenluck.client.chat2me.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import io.sevenluck.client.chat2me.MainActivity;
import io.sevenluck.client.chat2me.common.AndroidErrorResponseHandler;
import io.sevenluck.client.chat2me.common.AppConstants;
import io.sevenluck.client.chat2me.common.RestUtil;
import io.sevenluck.client.chat2me.domain.HttpResult;
import io.sevenluck.client.chat2me.domain.Member;
import io.sevenluck.client.chat2me.domain.RestException;

/**
 * Created by loki on 6/5/16.
 */
public class RegisterAsyncTask extends AsyncTask<Member, Void, HttpResult<Member>> {

    public final static String REST_ENDPOINT = AppConstants.URL + "/chatmembers";

    private ObjectMapper objectMapper = new ObjectMapper();

    private Context context;
    MainActivity.RegistrationCallback callback;

    public RegisterAsyncTask(Context context, MainActivity.RegistrationCallback callback) {
        this.context = context.getApplicationContext();
        this.callback = callback;
    }


    @Override
    protected HttpResult<Member> doInBackground(Member... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Member member = params[0];

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<Member> request = new HttpEntity<>(member);

            restTemplate.setErrorHandler(new AndroidErrorResponseHandler());
            ResponseEntity<String> response = restTemplate.exchange(REST_ENDPOINT, HttpMethod.POST, request, String.class);

            String responseBody = response.getBody();
            Log.d("Response from server", responseBody);
            try {
                if (RestUtil.isError(response.getStatusCode())) {
                    RestException error = objectMapper.readValue(responseBody, RestException.class);
                    return new HttpResult<>(error.getMessage());
                } else {
                    Member result = objectMapper.readValue(responseBody, Member.class);
                    Log.d("POST", result.toString());
                    return new HttpResult<>(member);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            Log.e("RegisterAsyncTask", e.getMessage(), e);

        }
        return null;
    }

    @Override
    protected void onPostExecute(HttpResult<Member> result) {
        callback.onFishedRequest(result);
    }

}
