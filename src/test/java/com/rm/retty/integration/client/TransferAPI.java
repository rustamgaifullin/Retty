package com.rm.retty.integration.client;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransferAPI {

    @POST("/transfer")
    Flowable<ResponseBody> transfer(@Body TransferRequest transferRequest);

}
