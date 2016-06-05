package io.sevenluck.client.chat2me.common;

import android.util.Log;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;



/**
 * Created by loki on 6/5/16.
 */
public class AndroidErrorResponseHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}
