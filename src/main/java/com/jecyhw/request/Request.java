package com.jecyhw.request;

import com.jecyhw.request.exception.RequestFailedException;
import com.jecyhw.request.params.Get;
import com.jecyhw.request.params.Post;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by jecyhw on 16-8-19.
 */
final public class Request {
    static public String post(String url, Post postParams) throws RequestFailedException{
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(postParams.requestBody()).build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RequestFailedException(url, postParams.toString(), response.toString());
            }
        } catch (IOException e) {
            throw new RequestFailedException(url, postParams.toString(), e);
        }
    }

    static public String get(String url) throws RequestFailedException{
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RequestFailedException(url, response.toString());
            }
        } catch (IOException e) {
            throw new RequestFailedException(url, e);
        }
    }

    static public String get(String url, Get getParams) throws RequestFailedException {
        return get(url + "?" + getParams.toString());
    }
}
