package io.github.jklingsporn.vertx.push.filters;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public class FilterImpl implements Filter{

    private static final JsonObject OR = new JsonObject().put("operator", "OR");
    private final JsonArray contents = new JsonArray();
    private final JsonObject content = new JsonObject();

    public FilterImpl(String field) {
        content().put("field",field);
        contents.add(content);
    }

    @Override
    public Filter and(Filter other) {
        contents.add(other.content());
        return this;
    }

    @Override
    public Filter or(Filter other) {
        contents.add(OR).add(other.content());
        return this;
    }

    @Override
    public JsonArray asJsonArray() {
        return contents;
    }

    @Override
    public JsonObject content() {
        return content;
    }
}
