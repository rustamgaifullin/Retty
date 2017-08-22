package com.rm.retty.integration.client.rx;

import com.rm.retty.integration.client.TransferRequest;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MoneyApiRx {
    @POST("/money/transfer")
    Flowable<ResponseBody> transfer(@Body TransferRequest transferRequest);

    @GET("/user/balance")
    Flowable<ResponseBody> getUserBalance(@Query("name") String name, @Query("accountNumber") String accountNumber);
}