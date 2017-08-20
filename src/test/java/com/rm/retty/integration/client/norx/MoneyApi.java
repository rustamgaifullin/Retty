package com.rm.retty.integration.client.norx;

import com.rm.retty.integration.client.TransferRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MoneyApi {
    @POST("/money/transfer")
    Call<ResponseBody> transfer(@Body TransferRequest requestBody);
}