package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 03.01.17.
 */
interface PushStep {

    JsonObject container();

    PushClient client();
}
