package com.rm.retty.integration;

import com.rm.retty.api.controller.MoneyController;
import com.rm.retty.container.Config;
import com.rm.retty.container.Context;
import com.rm.retty.container.RettyApplication;
import com.rm.retty.integration.client.TestClient;
import com.rm.retty.integration.client.TransferRequest;
import com.rm.retty.integration.client.UserAccountRequest;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;

public class RettyIntegrationTest {

    private Config config = new Config("127.0.0.1", 8080);
    private Context context = new Context()
            .addClass(MoneyController.class);
    private RettyApplication rettyApplication = new RettyApplication(config, context);
    private TestClient testClient = new TestClient("http://" + config.getHost() + ":" + config.getPort());

    private TestSubscriber<ResponseBody> testSubscriber = new TestSubscriber<>();


    @Before
    public void startUp() {
        rettyApplication.start();
    }

    @After
    public void shutdown() throws InterruptedException {
        rettyApplication.stop();
    }

    @Test
    @Ignore
    public void should_receive_response() {
        UserAccountRequest from = new UserAccountRequest("Yoda", "AccountIsYodaNumber123");
        UserAccountRequest to = new UserAccountRequest("Luke", "theforce123");
        TransferRequest transferRequest = new TransferRequest(from, to, BigDecimal.valueOf(50));

        testClient.requestTransfer(transferRequest).subscribe(testSubscriber);

        testSubscriber.assertResult(ResponseBody.create(MediaType.parse("text"), "Success"));
    }
}
