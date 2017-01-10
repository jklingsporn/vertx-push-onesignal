package io.github.jklingsporn.vertx.push;

import io.github.jklingsporn.vertx.push.filters.Filter;
import io.vertx.core.json.JsonArray;

/**
 * Created by jensklingsporn on 03.01.17.
 */
interface PushToDestinationStep extends PushStep {



    /**
     * Targeting the audience of your push message using a <code>Filter</code>.
     * Use the <code>Filters</code> factory to create filters. <br>
     * <b>Example:</b><code>Filters.firstSession().less(4f).and(Filters.appVersion().greater("1.2.3"))</code>
     * @param filter
     * @return a SendWithOptionsStep
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notifications using filters</a>
     */
    SendWithOptionsStep targetByFilter(Filter filter);

    /**
     * Targeting the audience using segments (which can be set up in the OneSignal dashboard).
     * @param includeSegments a list of segments you want to include
     * @return a SendToSegmentStep which allows you to exclude segments.
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-segments">Notifications using segments</a>
     */
    SendToSegmentStep targetBySegments(JsonArray includeSegments);

    /**
     * Targeting the audience using OneSignal-playerIds.
     * @param playerIds a list of playerIds
     * @return a SendWithOptionsStep
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notifications using playerIds</a>
     */
    SendWithOptionsStep targetByPlayerIds(JsonArray playerIds);
}
