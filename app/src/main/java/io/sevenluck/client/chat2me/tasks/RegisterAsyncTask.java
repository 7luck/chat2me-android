package io.sevenluck.client.chat2me.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import io.sevenluck.client.chat2me.common.AppConstants;
import io.sevenluck.client.chat2me.domain.HttpResult;
import io.sevenluck.client.chat2me.domain.Member;

/**
 * Created by loki on 6/5/16.
 */
public class RegisterAsyncTask extends AsyncTask<Member, Void, HttpResult<Member>> {

    public final static String REST_ENDPOINT = AppConstants.URL + "/chatmembers";

    private Context context;

    public RegisterAsyncTask(Context context) {
        this.context = context.getApplicationContext();
    }


    @Override
    protected HttpResult<Member> doInBackground(Member... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Member member = params[0];

            member = restTemplate.postForObject(REST_ENDPOINT, member, Member.class);
            Log.d("POST", member.toString());

            return new HttpResult<>(member);
        } catch (HttpServerErrorException e) {
            return new HttpResult<>("fehler");
        } catch (HttpClientErrorException e) {
            if (HttpStatus.CONFLICT.equals(e.getStatusCode())) {
                return new HttpResult<>("gibt es schon");
            }
        } catch (Exception e) {
            Log.e("RegisterAsyncTask", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(HttpResult<Member> result) {
        if (null == result) {
            return;
        }

        if (result.isSuceeded()) {
            Toast.makeText(context, "hat geklappt", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, result.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

}
