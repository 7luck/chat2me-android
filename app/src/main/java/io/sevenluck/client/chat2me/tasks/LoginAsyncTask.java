package io.sevenluck.client.chat2me.tasks;

import android.content.Context;
import android.util.Log;
import org.springframework.http.HttpMethod;

import io.sevenluck.client.chat2me.auth.TokenAuthentication;
import io.sevenluck.client.chat2me.client.HttpResult;
import io.sevenluck.client.chat2me.client.RestClient;
import io.sevenluck.client.chat2me.common.AppConstants;
import io.sevenluck.client.chat2me.domain.Member;
import io.sevenluck.client.chat2me.tasks.callbacks.BasicAsyncTask;
import io.sevenluck.client.chat2me.tasks.callbacks.HttpRequestCallback;

/**
 * Created by loki on 6/5/16.
 */
public class LoginAsyncTask extends BasicAsyncTask<Member, Void, HttpResult<Member>> {


    public final static String REST_ENDPOINT = AppConstants.URL + "/login";

    public LoginAsyncTask(Context context, HttpRequestCallback callback) {
        super(context, callback);
    }

    @Override
    protected HttpResult<Member> doInBackground(Member... params) {
        try {
            Member member = params[0];
            RestClient<Member> client = new RestClient<Member>() {
                @Override
                public Class<Member> getTClass() {
                    return Member.class;
                }
            };
            return client.send(REST_ENDPOINT, HttpMethod.POST, member);
        } catch (Exception e) {
            Log.e("LoginAsyncTask", e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(HttpResult<Member> result) {
        if (result.isSuceeded()) {
            TokenAuthentication.getInstance().setToken(result.getData().getAuthtoken());
            Log.d("onPostExecute", "Token " + result.getData().getAuthtoken() + " gesetzt.");
        }

        // invoke callback
        super.onPostExecute(result);
    }
}
