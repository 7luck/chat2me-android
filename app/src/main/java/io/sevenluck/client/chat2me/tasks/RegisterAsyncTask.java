package io.sevenluck.client.chat2me.tasks;

import android.content.Context;
import android.util.Log;

import org.springframework.http.HttpMethod;

import io.sevenluck.client.chat2me.client.HttpResult;
import io.sevenluck.client.chat2me.client.RestClient;
import io.sevenluck.client.chat2me.common.AppConstants;
import io.sevenluck.client.chat2me.domain.MemberTo;
import io.sevenluck.client.chat2me.tasks.callbacks.BasicAsyncTask;
import io.sevenluck.client.chat2me.tasks.callbacks.HttpRequestCallback;

/**
 * Created by loki on 6/5/16.
 */
public class RegisterAsyncTask extends BasicAsyncTask<MemberTo, Void, HttpResult<MemberTo>> {

    public final static String REST_ENDPOINT = AppConstants.URL + "/chatmembers";

    public RegisterAsyncTask(Context context, HttpRequestCallback callback) {
        super(context, callback);
    }

    @Override
    protected HttpResult<MemberTo> doInBackground(MemberTo... params) {
        try {
            MemberTo member = params[0];
            RestClient<MemberTo> client = new RestClient<MemberTo>() {
                @Override
                public Class<MemberTo> getTClass() {
                    return MemberTo.class;
                }
            };
            return client.send(REST_ENDPOINT, HttpMethod.POST, member);
        } catch (Exception e) {
            Log.e("RegisterAsyncTask", e.getMessage(), e);
        }
        return null;
    }

}
