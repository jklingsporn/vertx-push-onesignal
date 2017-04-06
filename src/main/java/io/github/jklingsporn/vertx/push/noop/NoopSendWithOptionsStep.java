package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.SendOptions;
import io.github.jklingsporn.vertx.push.SendStep;
import io.github.jklingsporn.vertx.push.SendWithOptionsStep;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.time.ZonedDateTime;

/**
 * Created by jensklingsporn on 03.01.17.
 */
class NoopSendWithOptionsStep extends AbstractNoopPushStep implements SendWithOptionsStep{

    private static NoopSendWithOptionsStep INSTANCE;
    public static NoopSendWithOptionsStep getInstance() {
        return INSTANCE == null ? INSTANCE = new NoopSendWithOptionsStep() : INSTANCE;
    }

    @Override
    public SendStep addOptions(SendOptions sendOptions) {
        return this;
    }

    @Override
    public void sendNow(Handler<AsyncResult<JsonObject>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(container()));
    }

    @Override
    public void sendAfter(ZonedDateTime afterDate, Handler<AsyncResult<JsonObject>> resultHandler) {
        resultHandler.handle(Future.succeededFuture(container()));
    }
}
