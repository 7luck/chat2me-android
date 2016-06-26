package io.sevenluck.client.chat2me.client;

import org.springframework.http.HttpStatus;

/**
 * Created by loki on 6/5/16.
 */
public class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
