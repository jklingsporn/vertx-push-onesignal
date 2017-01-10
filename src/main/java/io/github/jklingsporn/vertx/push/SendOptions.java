package io.github.jklingsporn.vertx.push;

import io.vertx.core.json.JsonObject;

import java.util.Set;

/**
 * Created by jensklingsporn on 04.01.17.
 * Specify further options that are added to the JSON-data. Some common parameters are added as
 * setters others can be added using the <code>setCustom</code>-method. This class is backed by
 * a <code>JsonObject</code> which you can also pass in the constructor.
 * @see <a href="https://documentation.onesignal.com/reference#section-example-code-create-notification">OneSignal Documentation</a>
 */
public class SendOptions {

    public enum DelayedOption{
        TIMEZONE("timezone"),
        LAST_ACTIVE("last-active");

        private final String alias;

        DelayedOption(String alias) {
            this.alias = alias;
        }
    }

    public enum Platform{
        AMAZON("isAdm"),
        ANDROID("isAndroid"),
        CHROME("isChrome"),
        CHROME_APP("isChromeWeb"),
        FIREFOX("isFirefox"),
        IOS("isIos"),
        SAFARI("isSafari"),
        WEB("isAnyWeb"),
        WINDOWS_PHONE("isWP"),
        WINDOWS_PHONE_WNS("isWP_WNS"),
        ;
        private final String alias;

        Platform(String alias) {
            this.alias = alias;
        }
    }

    private final JsonObject content;

    public SendOptions() {
        this(new JsonObject());
    }

    public SendOptions(JsonObject content) {
        this.content = content;
    }

    public SendOptions setTTL(int ttl){
        return setCustom("ttl", ttl);
    }

    public SendOptions setPriority(int priority){
        return setCustom("priority", priority);
    }

    public SendOptions setDelayedOption(DelayedOption option){
        return setCustom("delayed_option",option.alias);
    }

    public SendOptions setPlatform(Set<Platform> platforms){
        platforms.stream().forEach(p->content.put(p.alias,true));
        return this;
    }

    public SendOptions setData(JsonObject data){
        return setCustom("data",data);
    }

    public SendOptions setUrl(String url){
        return setCustom("url",url);
    }

    public SendOptions setCustom(String key, Object value){
        content.put(key,value);
        return this;
    }

    JsonObject toJson(){
        return content;
    }

}
