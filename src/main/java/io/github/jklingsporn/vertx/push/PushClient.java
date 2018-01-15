package io.github.jklingsporn.vertx.push;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 02.01.17.
 */
public interface PushClient {

    /**
     * @param vertx your Vertx instance
     * @param appId your OneSignal AppId
     * @param restApiKey your OneSignal API-Key
     * @return a PushClient
     * @deprecated Consider using PushClient#create(Vertx,PushClientOptions) instead
     */
    static PushClient create(Vertx vertx, String appId, String restApiKey){
        return new OneSignalPushClient(vertx, new PushClientOptions().setAppId(appId).setRestApiKey(restApiKey));
    }

    /**
     * @param httpClient the HttpClient to use for calling the OneSignal-API.
     * @param appId your OneSignal AppId
     * @param restApiKey your OneSignal API-Key
     * @return a PushClient
     * @deprecated Consider using PushClient#create(HttpClient,PushClientOptions) instead
     */
    static PushClient create(HttpClient httpClient, String appId, String restApiKey){
        return new OneSignalPushClient(httpClient, new PushClientOptions().setAppId(appId).setRestApiKey(restApiKey));
    }

    /**
     * @param httpClient the HttpClient to use for calling the OneSignal-API.
     * @param pushClientOptions the options
     * @return a PushClient
     * @since 1.7
     */
    static PushClient create(HttpClient httpClient, PushClientOptions pushClientOptions){
        return new OneSignalPushClient(httpClient, pushClientOptions);
    }

    /**
     * @param vertx your Vertx instance
     * @param pushClientOptions the options
     * @return a PushClient
     * @since 1.7
     */
    static PushClient create(Vertx vertx, PushClientOptions pushClientOptions){
        return new OneSignalPushClient(vertx, pushClientOptions);
    }

    /**
     * Creating a push request using the <code>template_id</code>.
     * @param templateId the template that should be used.
     * @return a AddHeadersStep
     * @see <a href="https://documentation.onesignal.com/reference#section-content-language">Create notification</a>
     */
    AddHeadersStep withTemplate(String templateId);

    /**
     * Creating a push request using the <code>contents</code> option.
     * @param contents something like {"en": "English Message", "es": "Spanish Message"}
     * @return a AddHeadersStep
     * @see <a href="https://documentation.onesignal.com/reference#section-content-language">Create notification</a>
     */
    AddHeadersStep withContent(JsonObject contents);

    /**
     * Send a raw push request and assembling parameters on your own. Warning: no plausibility checks are made.
     * @return SendWithOptionsStep
     */
    SendWithOptionsStep raw();
}
