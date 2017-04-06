package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.SendToSegmentStep;
import io.github.jklingsporn.vertx.push.SendWithOptionsStep;
import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 04.01.17.
 */
class NoopSendToSegmentStep extends NoopSendWithOptionsStep implements SendToSegmentStep {

    private static NoopSendToSegmentStep INSTANCE;
    public static NoopSendToSegmentStep getInstance() {
        return INSTANCE == null ? INSTANCE = new NoopSendToSegmentStep() : INSTANCE;
    }

    @Override
    public SendWithOptionsStep exclude(JsonArray excludeSegments) {
        return this;
    }
}
