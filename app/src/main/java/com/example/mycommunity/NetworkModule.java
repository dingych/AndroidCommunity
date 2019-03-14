package com.example.mycommunity;

import com.example.mycommunity.JsonEntity.UserInformation;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkModule {

    public static void get(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    public static void post(String url, String json, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

    public static void postForm(String url, UserInformation userInformation, Callback callback) {
         OkHttpClient client = new OkHttpClient();

//         RequestBody requestBody = FormBody.create(MediaType.parse("application/form-data; charset=utf-8"),"");/
//         Request request = new Request.Builder().url(url).("username", userInformation.getUsername()).header("password",userInformation.getPassword()).post(requestBody).build();
//         client.newCall(request).enqueue(callback);
        //FormBody formBody = new FormBody.Builder().add("username","12345").add("password","12345678901").build();
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("username", userInformation.getUsername())
                .addFormDataPart("password",userInformation.getPassword()).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

}
