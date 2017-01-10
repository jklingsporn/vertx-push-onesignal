package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 03.01.17.
 */
interface SendToSegmentStep extends SendWithOptionsStep {

    /**
     * Targeting the audience using segments (which can be set up in the OneSignal dashboard).
     * @param excludeSegments a list of segments you want to <b>exclude</b>
     * @return a SendWithOptionsStep
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-segments">Notifications using segments</a>
     */
    SendWithOptionsStep exclude(JsonArray excludeSegments);
}
