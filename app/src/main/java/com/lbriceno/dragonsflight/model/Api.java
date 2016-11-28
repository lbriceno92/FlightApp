package com.lbriceno.dragonsflight.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lbriceno.dragonsflight.R;
import com.lbriceno.dragonsflight.base.BaseApplication;
import com.lbriceno.dragonsflight.model.entities.Currency;
import com.lbriceno.dragonsflight.model.entities.Flight;
import com.lbriceno.dragonsflight.model.entities.FlightResult;
import com.lbriceno.dragonsflight.tools.Constants;

import junit.framework.Assert;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by luis_ on 10/1/2016.
 */
public class Api {
    private static Api mInstance = new Api();

    public static Api getInstance() {
        return mInstance;
    }

    private Api() {
    }

    public void getFlights(final OnModelUpdate onModelUpdate) {
        RetrofitService.getInstance(Constants.ENDPOINT).getFlights(new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, final Response response) {
                if (jsonObject != null) {
                    final FlightResult result = new Gson().fromJson(jsonObject, FlightResult.class);

                    if (onModelUpdate != null)
                        onModelUpdate.updateUI(BaseApplication.getContext().getString(R.string.initial_set_up), false);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Collections.sort(result.getResults(), new Comparator<Flight>() {
                                    @Override
                                    public int compare(Flight lhs, Flight rhs) {
                                        return lhs.getPrice() < rhs.getPrice() ? -1 : (lhs.getPrice() > rhs.getPrice()) ? 1 : 0;
                                    }
                                });

                                NumberFormat df = DecimalFormat.getInstance();
                                df.setMaximumFractionDigits(2);
                                df.setRoundingMode(RoundingMode.DOWN);

                                for (Flight flight : result.getResults()) {
                                    flight.setId(new Date().getTime());

                                    if (!flight.getCurrency().equals("EUR")) {
                                        Currency currency = Realm.getDefaultInstance().where(Currency.class).equalTo("currency", flight.getCurrency()).findFirst();
                                        if (currency != null) {
                                            flight.setPrice(flight.getPrice() * currency.getExchangeRate());
                                        }
                                    }
                                    flight.setCurrency("EUR");
                                    Realm.getDefaultInstance().beginTransaction();
                                    Realm.getDefaultInstance().copyToRealmOrUpdate(flight);
                                    Realm.getDefaultInstance().commitTransaction();
                                }

                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }).start();
                    if (onModelUpdate != null)
                        onModelUpdate.updateUI(BaseApplication.getContext().getString(R.string.flights_updated), true);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (onModelUpdate != null)
                    onModelUpdate.updateUI(BaseApplication.getContext().getString(R.string.error_updating), true);
            }
        });
    }

    public void getCurrencyTransformation(String currency) {
        RetrofitService.getInstance(Constants.CURRENCY_ENDPOINT).getExchangeRate("EUR", currency, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                Currency result = new Gson().fromJson(jsonObject, Currency.class);
                Realm.getDefaultInstance().beginTransaction();
                Realm.getDefaultInstance().copyToRealmOrUpdate(result);
                Realm.getDefaultInstance().commitTransaction();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public interface OnModelUpdate {
        public void updateUI(String message, boolean finish);

    }

}
