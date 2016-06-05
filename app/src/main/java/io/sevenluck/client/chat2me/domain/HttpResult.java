package io.sevenluck.client.chat2me.domain;

import org.springframework.web.client.RestTemplate;

/**
 * Created by loki on 6/5/16.
 */
public class HttpResult<T> {

    public static enum ResultState {
        OK, FAILURE
    }

    public ResultState resultState;
    public String message;
    public T data;



    public HttpResult(T data) {
        resultState = ResultState.OK;
        this.data = data;
    }

    public HttpResult(String message) {
        this.resultState = ResultState.FAILURE;
        this.message = message;
    }

    public HttpResult() {
    }

    public ResultState getResultState() {
        return resultState;
    }

    public void setResultState(ResultState resultState) {
        this.resultState = resultState;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuceeded() {
        return resultState.equals(ResultState.OK);
    }
}
