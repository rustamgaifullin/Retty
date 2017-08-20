package com.rm.retty.integration.client.rx;

import com.rm.retty.integration.client.TransferRequest;
import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MoneyApiRx {
    @POST("/money/transfer")
    Flowable<ResponseBody> transfer(@Body TransferRequest transferRequest);

}