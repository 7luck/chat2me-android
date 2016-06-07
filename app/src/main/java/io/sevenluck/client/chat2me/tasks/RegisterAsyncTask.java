package io.sevenluck.client.chat2me.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.HttpMethod;

import io.sevenluck.client.chat2me.client.HttpResult;
import io.sevenluck.client.chat2me.client.RestClient;
import io.sevenluck.client.chat2me.common.AppConstants;
import io.sevenluck.client.chat2me.domain.Member;
import io.sevenluck.client.chat2me.tasks.callbacks.HttpRequestCallback;

/**
 * Created by loki on 6/5/16.
 */
public class RegisterAsyncTask extends AsyncTask<Member, Void, HttpResult<Member>> {

    public final static String REST_ENDPOINT = AppConstants.URL + "/chatmembers";

    private Context context;
    private HttpRequestCallback callback;

    public RegisterAsyncTask(Context context, HttpRequestCallback callback) {
        this.context = context.getApplicationContext();
        this.callback = callback;
    }

    @Override
    protected HttpResult<Member> doInBackground(Member... params) {
        try {
            Member member = params[0];
            RestClient<Member> client = new RestClient<>();
            return client.send(REST_ENDPOINT, HttpMethod.POST, member);
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
