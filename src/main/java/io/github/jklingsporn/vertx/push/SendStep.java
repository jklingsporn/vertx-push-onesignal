package io.github.jklingsporn.vertx.push;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.time.ZonedDateTime;

/**
 * Created by jensklingsporn on 03.01.17.
 */
public interface SendStep extends PushStep{

    /**
     *
     * @param resultHandler
     */
    void sendNow(Handler<AsyncResult<JsonObject>> resultHandler);

    /**
     *
     * @param afterDate
     * @param resultHandler
     */
    void sendAfter(ZonedDateTime afterDate, Handler<AsyncResult<JsonObject>> resultHandler);

}
