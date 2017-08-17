package com.rm.retty.integration.client;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestClient {

    private final MoneyApi transferAPI;

    public TestClient(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        transferAPI = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(MoneyApi.class);
    }

    public Flowable<ResponseBody> requestTransfer(TransferRequest transferRequest) {
        return transferAPI.transfer(transferRequest);
    }
}
