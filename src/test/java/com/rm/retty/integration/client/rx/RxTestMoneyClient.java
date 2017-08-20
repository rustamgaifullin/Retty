package com.rm.retty.integration.client.rx;

import com.rm.retty.integration.client.TransferRequest;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxTestMoneyClient {

    private final MoneyApiRx moneyApiRx;

    public RxTestMoneyClient(String baseUrl) {
        moneyApiRx = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoneyApiRx.class);
    }

    public Flowable<ResponseBody> requestTransfer(TransferRequest transferRequest) {
        return moneyApiRx.transfer(transferRequest);
    }
}