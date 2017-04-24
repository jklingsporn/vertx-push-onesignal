package io.github.jklingsporn.vertx.push;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jensklingsporn on 03.01.17.
 */
class SendWithOptionsStepImpl extends AbstractPushStep implements SendWithOptionsStep{

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss O");

    SendWithOptionsStepImpl(PushStep parent) {
        super(parent);
    }

    @Override
    public SendStep addOptions(SendOptions sendOptions) {
        container().mergeIn(sendOptions.toJson());
        return this;
    }

    @Override
    public void sendNow(Handler<AsyncResult<JsonObject>> resultHandler) {
        ((OneSignalPushClient) client()).sendRequest(container(),resultHandler);
    }

    @Override
    public void sendAfter(ZonedDateTime afterDate, Handler<AsyncResult<JsonObject>> resultHandler) {
        ((OneSignalPushClient) client()).sendRequest(container().put("send_after", afterDate.format(FORMATTER)),resultHandler);
    }
}
