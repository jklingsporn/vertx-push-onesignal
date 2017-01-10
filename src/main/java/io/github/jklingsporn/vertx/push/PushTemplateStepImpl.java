package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 03.01.17.
 */
class PushTemplateStepImpl implements PushMessageStep {

    private final JsonObject container;
    private final PushClient client;

    public PushTemplateStepImpl(JsonObject container, PushClient client, String templateId) {
        this.container = container;
        this.client = client;
        this.container.put("template_id", templateId);
    }

    @Override
    public JsonObject container() {
        return container;
    }

    @Override
    public PushClient client() {
        return client;
    }
}
