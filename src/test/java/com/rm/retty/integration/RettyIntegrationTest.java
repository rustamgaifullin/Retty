package com.rm.retty.integration;

import com.rm.retty.api.controller.MoneyController;
import com.rm.retty.server.Config;
import com.rm.retty.server.context.Context;
import com.rm.retty.server.RettyApplication;
import com.rm.retty.integration.client.rx.RxTestMoneyClient;
import com.rm.retty.integration.client.TransferRequest;
import com.rm.retty.integration.client.UserAccountRequest;
import com.rm.retty.integration.client.norx.TestMoneyClient;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.ResponseBody;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class RettyIntegrationTest {

    private Config config = new Config("127.0.0.1", 8080);
    private Context context = new Context()
            .addClass(MoneyController.class);
    private RettyApplication rettyApplication = new RettyApplication(config, context);
    private RxTestMoneyClient rxTestMoneyClient = new RxTestMoneyClient("http://" + config.getHost() + ":" + config.getPort());

    private TestSubscriber<ResponseBody> testSubscriber = new TestSubscriber<>();


    @Before
    public void startUp() throws Exception {
        rettyApplication.start();
    }

    @After
    public void shutdown() throws InterruptedException, IOException {
        rettyApplication.stop();
    }

    @Test
    public void should_receive_response_using_rx() {
        UserAccountRequest from = new UserAccountRequest("Yoda", "number0");
        UserAccountRequest to = new UserAccountRequest("Luke", "number1");
        TransferRequest transferRequest = new TransferRequest(from, to, BigDecimal.valueOf(50));

        rxTestMoneyClient.requestTransfer(transferRequest).subscribe(testSubscriber);

        testSubscriber.assertComplete();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(responseBody -> responseBody.string().equals("Success"));
    }

    @Test
    public void should_receive_response() throws Exception {
        UserAccountRequest from = new UserAccountRequest("Yoda", "number0");
        UserAccountRequest to = new UserAccountRequest("Luke", "number1");
        TransferRequest transferRequest = new TransferRequest(from, to, BigDecimal.valueOf(50));

        TestMoneyClient testMoneyClient = new TestMoneyClient("http://" + config.getHost() + ":" + config.getPort());

        ResponseBody responseBody = testMoneyClient.requestTransfer(transferRequest).execute().body();
        String result = responseBody.string();

        assertEquals("Success", result);
    }
}