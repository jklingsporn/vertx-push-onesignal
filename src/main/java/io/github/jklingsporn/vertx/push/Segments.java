package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 03.01.17.
 */
public class Segments {

    public static final JsonArray ALL = new JsonArray();
    static{
        ALL.add("ALL");
    }

    private Segments() {
    }
}
