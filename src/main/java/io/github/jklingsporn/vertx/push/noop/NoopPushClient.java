package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.AddHeadersStep;
import io.github.jklingsporn.vertx.push.PushClient;
import io.github.jklingsporn.vertx.push.SendWithOptionsStep;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 06.04.17.
 */
public class NoopPushClient implements PushClient{

    private static final PushClient INSTANCE = new NoopPushClient();

    public static PushClient getInstance() {
        return INSTANCE;
    }

    @Override
    public AddHeadersStep withTemplate(String templateId) {
        return NoopAddHeadersStep.getInstance();
    }

    @Override
    public AddHeadersStep withContent(JsonObject contents) {
        return NoopAddHeadersStep.getInstance();
    }

    @Override
    public SendWithOptionsStep raw() {
        return NoopSendWithOptionsStep.getInstance();
    }

    @Override
    public void cancel(String notificationId, Handler<AsyncResult<JsonObject>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(new JsonObject()));
    }
}
