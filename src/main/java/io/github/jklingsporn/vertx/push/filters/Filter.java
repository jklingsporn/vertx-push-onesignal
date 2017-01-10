package io.github.jklingsporn.vertx.push.filters;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public interface Filter {

    Filter and(Filter other);

    Filter or(Filter other);

    JsonArray asJsonArray();

    JsonObject content();

}
