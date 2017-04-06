package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.*;
import io.github.jklingsporn.vertx.push.filters.Filter;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 06.04.17.
 */
class NoopAddHeadersStep extends AbstractNoopPushStep implements AddHeadersStep{

    private static AddHeadersStep INSTANCE;
    public static AddHeadersStep getInstance() {
        return INSTANCE == null ? INSTANCE = new NoopAddHeadersStep() : INSTANCE;
    }

    @Override
    public PushToDestinationStep withHeadings(JsonObject headings) {
        return NoopPushToDestinationStep.getInstance();
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
