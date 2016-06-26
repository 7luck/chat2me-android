package io.sevenluck.client.chat2me.tasks;

/**
 * Created by loki on 6/12/16.
 */

import android.content.Context;
import android.util.Log;
import org.springframework.http.HttpMethod;
import io.sevenluck.client.chat2me.client.HttpResult;
import io.sevenluck.client.chat2me.client.RestClient;
import io.sevenluck.client.chat2me.common.AppConstants;
import io.sevenluck.client.chat2me.domain.ChatTo;
import io.sevenluck.client.chat2me.tasks.callbacks.BasicAsyncTask;
import io.sevenluck.client.chat2me.tasks.callbacks.HttpRequestCallback;

public class AddChatAsyncTask extends BasicAsyncTask<ChatTo, Void, HttpResult<ChatTo>> {

    public final static String REST_ENDPOINT = AppConstants.URL + "/chatrooms";

    public AddChatAsyncTask(Context context, HttpRequestCallback callback) {
        super(context, callback);
    }

    @Override
    protected HttpResult<ChatTo> doInBackground(ChatTo... params) {
        try {
            ChatTo chatTo = params[0];
            RestClient<ChatTo> client = new RestClient<ChatTo>() {
                @Override
                public Class<ChatTo> getTClass() {
                    return ChatTo.class;
                }
            };

            return client.send(REST_ENDPOINT, HttpMethod.POST, chatTo);
        } catch (Exception e) {
            Log.e("AddChatAsyncTask", e.getMessage(), e);
        }
        return null;
    }

}
