package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 09.01.17.
 */
public class AddHeadersStepImpl extends PushToDestinationImpl implements AddHeadersStep {

    public AddHeadersStepImpl(PushMessageStep parent) {
        super(parent);
    }

    @Override
    public PushToDestinationStep withHeadings(JsonObject headings) {
        container().put("headings",headings);
        return this;
    }
}
