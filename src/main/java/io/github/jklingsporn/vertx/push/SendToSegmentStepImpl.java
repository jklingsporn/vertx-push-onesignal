package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 04.01.17.
 */
class SendToSegmentStepImpl extends SendWithOptionsStepImpl implements SendToSegmentStep {

    SendToSegmentStepImpl(PushStep parent) {
        super(parent);
    }

    @Override
    public SendWithOptionsStep exclude(JsonArray excludeSegments) {
        container().put("excluded_segments",excludeSegments);
        return this;
    }
}
