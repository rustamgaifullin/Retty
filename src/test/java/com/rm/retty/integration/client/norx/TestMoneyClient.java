package com.rm.retty.integration.client.norx;

import com.rm.retty.integration.client.TransferRequest;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestMoneyClient {
    private final MoneyApi moneyApi;

    public TestMoneyClient(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        moneyApi = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build()
                .create(MoneyApi.class);
    }

    public Call<ResponseBody> requestTransfer(TransferRequest request) {
        return moneyApi.transfer(request);
    }
}