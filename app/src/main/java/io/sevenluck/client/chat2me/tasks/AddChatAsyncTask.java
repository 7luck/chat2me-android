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
import io.sevenluck.client.chat2me.domain.Chat;
import io.sevenluck.client.chat2me.tasks.callbacks.BasicAsyncTask;
import io.sevenluck.client.chat2me.tasks.callbacks.HttpRequestCallback;

public class AddChatAsyncTask extends BasicAsyncTask<Chat, Void, HttpResult<Chat>> {

    public final static String REST_ENDPOINT = AppConstants.URL + "/chatrooms";

    public AddChatAsyncTask(Context context, HttpRequestCallback callback) {
        super(context, callback);
    }

    @Override
    protected HttpResult<Chat> doInBackground(Chat... params) {
        try {
            Chat chat = params[0];
            RestClient<Chat> client = new RestClient<Chat>() {
                @Override
                public Class<Chat> getTClass() {
                    return Chat.class;
                }
            };

            return client.send(REST_ENDPOINT, HttpMethod.POST, chat);
        } catch (Exception e) {
            Log.e("AddChatAsyncTask", e.getMessage(), e);
        }
        return null;
    }

}
