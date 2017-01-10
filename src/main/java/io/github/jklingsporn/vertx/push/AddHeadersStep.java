package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 09.01.17.
 */
public interface AddHeadersStep extends PushToDestinationStep{

    /**
     * Add <code>headings</code> option.
     * @param headings something like {"en": "English Title", "es": "Spanish Title"}
     * @return a PushToDestinationStep
     * @see <a href="https://documentation.onesignal.com/reference#section-content-language">Create notification</a>
     */
    PushToDestinationStep withHeadings(JsonObject headings);

}
