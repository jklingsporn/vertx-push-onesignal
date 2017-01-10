package io.github.jklingsporn.vertx.push;

import io.github.jklingsporn.vertx.push.filters.Filter;
import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 03.01.17.
 */
class PushToDestinationImpl extends AbstractPushStep implements PushToDestinationStep {


    public PushToDestinationImpl(PushMessageStep parent) {
        super(parent);
    }

    @Override
    public SendWithOptionsStep targetByFilter(Filter filter) {
        container().put("filters",filter.asJsonArray());
        return new SendWithOptionsStepImpl(this);
    }

    @Override
    public SendToSegmentStep targetBySegments(JsonArray includeSegments) {
        container().put("included_segments", includeSegments);
        return new SendToSegmentStepImpl(this);
    }

    @Override
    public SendWithOptionsStep targetByPlayerIds(JsonArray playerIds) {
        container().put("include_player_ids",playerIds);
        return new SendWithOptionsStepImpl(this);
    }
}
