package com.lbriceno.dragonsflight.model;

import android.util.Log;

import com.google.gson.JsonObject;
import com.lbriceno.dragonsflight.tools.Constants;
import com.squareup.okhttp.OkHttpClient;
import java.util.concurrent.TimeUnit;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;

public class RetrofitService {

    private final static String TAG = "RetrofitService";

    public static RetrofitEndPoints getInstance(String endpoint) {
        RetrofitEndPoints api = null;
        String url;

        try {
            url = endpoint;
            final OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(5, TimeUnit.MINUTES);
            okHttpClient.setConnectTimeout(5, TimeUnit.MINUTES);

            RestAdapter res = new RestAdapter.Builder()
                    .setEndpoint(url)
                    .setClient(new OkClient(okHttpClient))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(new RequestInterceptor() {

                        @Override
                        public void intercept(RequestFacade request) {
                            //always sending content-type
                            request.addHeader("Content-Type", "application/json");
                        }
                    }).build();

            api = res.create(RetrofitEndPoints.class);

        } catch (Exception e) {
            Log.e(TAG, "Error log message.", e);
        }

        return api;
    }

    public interface RetrofitEndPoints {

        @GET("/")
        void getFlights(Callback<JsonObject> cb);

        @GET("/currency")
        void getExchangeRate(@Query("from") String currencyFrom, @Query("to") String to, Callback<JsonObject> cb);
    }
}
