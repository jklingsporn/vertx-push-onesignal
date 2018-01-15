package io.github.jklingsporn.vertx.push;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.RequestOptions;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

/**
 * Created by jensklingsporn on 02.01.17.
 */
class OneSignalPushClient implements PushClient{

    private static final int PORT = 443;
    private static final String DEFAULT_HOST = "onesignal.com";

    private final HttpClient httpClient;
    private final PushClientOptions pushClientOptions;
    private final String host = DEFAULT_HOST;
    private final RequestOptions requestOptions;


    OneSignalPushClient(Vertx vertx, PushClientOptions pushClientOptions) {
        this(vertx.createHttpClient(new HttpClientOptions().setSsl(true).setVerifyHost(false)), pushClientOptions);
    }

    OneSignalPushClient(HttpClient httpClient, PushClientOptions pushClientOptions) {
        Objects.requireNonNull(pushClientOptions.getAppId());
        Objects.requireNonNull(pushClientOptions.getRestApiKey());
        this.httpClient = httpClient;
        this.pushClientOptions = pushClientOptions;
        this.requestOptions = new RequestOptions().setHost(host).setPort(PORT).setSsl(true).setURI(Endpoints.PUSH);
    }

    void sendRequest(JsonObject content,Handler<AsyncResult<JsonObject>> resultHandler) {
        this.httpClient.post(requestOptions, response -> {
            response.bodyHandler(body -> {
                try {
                    JsonObject responseBody = body.toJsonObject();
                    JsonArray errors = responseBody.getJsonArray("errors");
                    if (errors !=null && !isIgnoreAllPlayersAreNotSubscribed(responseBody)) {
                        resultHandler.handle(Future.failedFuture(new OneSignalResponseException(errors.encodePrettily())));
                    }else if (response.statusCode() == HttpResponseStatus.OK.code()) {
                        resultHandler.handle(Future.succeededFuture(responseBody));
                    } else {
                        resultHandler.handle(Future.failedFuture(new OneSignalResponseException(responseBody.encodePrettily())));
                    }
                }catch(DecodeException e){
                    resultHandler.handle(Future.failedFuture(e));
                }
            });
        }).putHeader(HttpHeaders.CONTENT_TYPE, "application/json").putHeader(HttpHeaders.AUTHORIZATION, "Basic " + pushClientOptions.getRestApiKey()).end(content.encode());
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
