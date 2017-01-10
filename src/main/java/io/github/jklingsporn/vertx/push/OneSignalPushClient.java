package io.github.jklingsporn.vertx.push;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;

/**
 * Created by jensklingsporn on 02.01.17.
 */
class OneSignalPushClient implements PushClient{

    private static final int PORT = 443;
    private static final String DEFAULT_HOST = "onesignal.com";

    private final HttpClient httpClient;
    private final String appId;
    private final String restApiKey;
    private String host = DEFAULT_HOST;


    OneSignalPushClient(Vertx vertx, String appId, String restApiKey) {
        this.appId = appId;
        this.restApiKey = restApiKey;
        this.httpClient = vertx.createHttpClient(new HttpClientOptions().setSsl(true).setVerifyHost(false));
    }

    void sendRequest(String requestURI, JsonObject content,Handler<AsyncResult<JsonObject>> resultHandler) {
        this.httpClient.post(PORT, host, requestURI, response -> {
            response.bodyHandler(body -> {
                try {
                    JsonObject responseBody = body.toJsonObject();
                    if (responseBody.getJsonArray("errors")!=null) {
                        resultHandler.handle(Future.failedFuture(new OneSignalResponseException(responseBody.getJsonArray("errors").encodePrettily())));
                    }else if (response.statusCode() == HttpResponseStatus.OK.code()) {
                        resultHandler.handle(Future.succeededFuture(responseBody));
                    } else {
                        resultHandler.handle(Future.failedFuture(new OneSignalResponseException(responseBody.encodePrettily())));
                    }
                }catch(DecodeException e){
                    resultHandler.handle(Future.failedFuture(e));
                }
            });
        }).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").putHeader(HttpHeaders.AUTHORIZATION, "Basic " + restApiKey).end(content.encode());
    }

    @Override
    public AddHeadersStep withTemplate(String templateId) {
        return new AddHeadersStepImpl(new PushTemplateStepImpl(new JsonObject().put("app_id", appId),this, templateId));
    }

    @Override
    public AddHeadersStep withContent(JsonObject contents) {
        return new AddHeadersStepImpl(new PushContentsStepImpl(new JsonObject().put("app_id", appId),this,contents));
    }

    @Override
    public SendWithOptionsStep raw() {
        return new SendWithOptionsStepImpl(new PushStep() {
            @Override
            public JsonObject container() {
                return new JsonObject();
            }

            @Override
            public PushClient client() {
                return OneSignalPushClient.this;
            }
        });
    }
}
