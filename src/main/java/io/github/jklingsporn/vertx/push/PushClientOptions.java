package io.github.jklingsporn.vertx.push;

/**
 * Created by jensklingsporn on 10.01.18.
 * @since 1.7
 */
public class PushClientOptions {

    private String appId;
    private String restApiKey;
    /**
     * When set to <code>true</code> it will silently ignore the "All included players are not subscribed"-error by OneSignal
     * instead of creating an expensive OneSignalException.
     */
    private boolean ignoreAllPlayersAreNotSubscribed=false;

    public String getAppId() {
        return appId;
    }

    public PushClientOptions setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getRestApiKey() {
        return restApiKey;
    }

    public PushClientOptions setRestApiKey(String restApiKey) {
        this.restApiKey = restApiKey;
        return this;
    }

    public boolean isIgnoreAllPlayersAreNotSubscribed() {
        return ignoreAllPlayersAreNotSubscribed;
    }

    public PushClientOptions setIgnoreAllPlayersAreNotSubscribed(boolean ignoreAllPlayersAreNotSubscribed) {
        this.ignoreAllPlayersAreNotSubscribed = ignoreAllPlayersAreNotSubscribed;
        return this;
    }
}
