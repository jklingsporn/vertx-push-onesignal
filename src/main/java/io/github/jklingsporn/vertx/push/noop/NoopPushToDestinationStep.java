package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.PushToDestinationStep;
import io.github.jklingsporn.vertx.push.SendToSegmentStep;
import io.github.jklingsporn.vertx.push.SendWithOptionsStep;
import io.github.jklingsporn.vertx.push.filters.Filter;
import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 06.04.17.
 */
public class NoopPushToDestinationStep extends AbstractNoopPushStep implements PushToDestinationStep {

    private static final PushToDestinationStep INSTANCE = new NoopPushToDestinationStep();

    public static PushToDestinationStep getInstance() {
        return INSTANCE;
    }

    @Override
    public SendWithOptionsStep targetByFilter(Filter filter) {
        return NoopSendWithOptionsStep.getInstance();
    }

    @Override
    public SendToSegmentStep targetBySegments(JsonArray includeSegments) {
        return NoopSendToSegmentStep.getInstance();
    }

    @Override
    public SendWithOptionsStep targetByPlayerIds(JsonArray playerIds) {
        return NoopSendWithOptionsStep.getInstance();
    }
}
