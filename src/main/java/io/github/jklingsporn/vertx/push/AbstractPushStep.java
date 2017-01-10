package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 03.01.17.
 */
abstract class AbstractPushStep implements PushStep {

    private final PushStep parent;

    AbstractPushStep(PushStep parent) {
        this.parent = parent;
    }

    @Override
    public JsonObject container() {
        return parent.container();
    }

    @Override
    public PushClient client() {
        return parent.client();
    }
}
