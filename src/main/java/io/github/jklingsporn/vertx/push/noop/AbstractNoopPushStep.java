package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.PushClient;
import io.github.jklingsporn.vertx.push.PushStep;
import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 06.04.17.
 */
abstract class AbstractNoopPushStep implements PushStep{

    private static final JsonObject NOOP_CONTAINER = new JsonObject();

    @Override
    public JsonObject container() {
        return NOOP_CONTAINER;
    }

    @Override
    public PushClient client() {
        return NoopPushClient.getInstance();
    }
}
