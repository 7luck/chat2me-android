package io.sevenluck.client.chat2me.tasks.callbacks;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by loki on 6/7/16.
 */
public abstract  class BasicAsyncTask<M, V, H> extends AsyncTask<M, V, H> {

    protected final Context context;
    protected final HttpRequestCallback callback;

    public BasicAsyncTask(Context context, HttpRequestCallback callback) {
        this.context = context.getApplicationContext();
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(H result) {
        callback.onFishedRequest(result);
    }
}
