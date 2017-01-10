package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 02.01.17.
 */
public class OneSignalResponseException extends Exception {

    public OneSignalResponseException(String cause) {
        super(cause);
    }
}
