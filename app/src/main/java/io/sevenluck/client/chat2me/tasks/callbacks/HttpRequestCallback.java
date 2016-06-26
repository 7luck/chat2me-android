package io.sevenluck.client.chat2me.tasks.callbacks;

/**
 * Created by loki on 6/5/16.
 */
public abstract class HttpRequestCallback<T> {

    public abstract void onFishedRequest(T result);
}
