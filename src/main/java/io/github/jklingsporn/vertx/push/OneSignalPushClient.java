package io.github.jklingsporn.vertx.push;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import java.util.Objects;

/**
 * Created by jensklingsporn on 02.01.17.
 */
class OneSignalPushClient implements PushClient{

    private static final int PORT = 443;
    private static final String DEFAULT_HOST = "onesignal.com";

    private final WebClient webClient;
    private final PushClientOptions pushClientOptions;

    OneSignalPushClient(Vertx vertx, PushClientOptions pushClientOptions) {
        this(WebClient.create(vertx, new WebClientOptions().setDefaultHost(DEFAULT_HOST).setDefaultPort(PORT).setSsl(true).setVerifyHost(false)), pushClientOptions);
    }

    OneSignalPushClient(HttpClient httpClient, PushClientOptions pushClientOptions) {
        this(WebClient.wrap(httpClient), pushClientOptions);
    }

    OneSignalPushClient(WebClient webClient, PushClientOptions pushClientOptions) {
        Objects.requireNonNull(pushClientOptions.getAppId());
        Objects.requireNonNull(pushClientOptions.getRestApiKey());
        this.webClient = webClient;
        this.pushClientOptions = pushClientOptions;
    }

    void sendRequest(JsonObject content,Handler<AsyncResult<JsonObject>> resultHandler) {
        this.webClient
                .post(Endpoints.PUSH)
                .putHeader(HttpHeaders.CONTENT_TYPE.toString(), "application/json")
                .putHeader(HttpHeaders.AUTHORIZATION.toString(), "Basic " + pushClientOptions.getRestApiKey())
                .sendJsonObject(content,response -> {
                    if(response.succeeded()){
                        try {
                            JsonObject responseBody = response.result().bodyAsJsonObject();
                            JsonArray errors = responseBody.getJsonArray("errors");
                            if (errors != null && !isIgnoreAllPlayersAreNotSubscribed(responseBody)) {
                                resultHandler.handle(Future.failedFuture(new OneSignalResponseException(errors.encodePrettily())));
                            } else if (response.result().statusCode() == HttpResponseStatus.OK.code()) {
                                resultHandler.handle(Future.succeededFuture(responseBody));
                            } else {
                                resultHandler.handle(Future.failedFuture(new OneSignalResponseException(responseBody.encodePrettily())));
                            }
                        } catch (DecodeException e) {
                            resultHandler.handle(Future.failedFuture(e));
                        }
                    }else{
                        resultHandler.handle(Future.failedFuture(response.cause()));
                    }
                });
    }

    private boolean isIgnoreAllPlayersAreNotSubscribed(JsonObject responseBody){
        return pushClientOptions.isIgnoreAllPlayersAreNotSubscribed() && responseBody.getInteger("recipients")==0;
    }

    @Override
    public AddHeadersStep withTemplate(String templateId) {
        return new AddHeadersStepImpl(new PushTemplateStepImpl(new JsonObject().put("app_id", pushClientOptions.getAppId()),this, templateId));
    }

    @Override
    public AddHeadersStep withContent(JsonObject contents) {
        return new AddHeadersStepImpl(new PushContentsStepImpl(new JsonObject().put("app_id", pushClientOptions.getAppId()),this,contents));
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
