package io.github.jklingsporn.vertx.push;

import io.vertx.core.Vertx;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jensklingsporn on 15.01.18.
 */
public class PushClientCreateTest {

    @Test(expected = NullPointerException.class)
    public void emptyPushClientOptionsShouldFail(){
        PushClient.create(Vertx.vertx(), new PushClientOptions());
    }

    @Test(expected = NullPointerException.class)
    public void noRestAPIKeyInPushClientOptionsShouldFail(){
        PushClient.create(Vertx.vertx(), new PushClientOptions().setAppId("foo"));
    }

    @Test(expected = NullPointerException.class)
    public void noAppIdInPushClientOptionsShouldFail(){
        PushClient.create(Vertx.vertx(), new PushClientOptions().setRestApiKey("bar"));
    }

    @Test
    public void createPushClientWithRegularOptionsShouldSucceed(){
        PushClient pushClient = PushClient.create(Vertx.vertx(), new PushClientOptions().setAppId("foo").setRestApiKey("bar"));
        Assert.assertNotNull(pushClient);
    }

}
