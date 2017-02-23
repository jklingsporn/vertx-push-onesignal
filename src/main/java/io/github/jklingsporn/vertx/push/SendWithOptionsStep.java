package io.github.jklingsporn.vertx.push;

/**
 * Created by jensklingsporn on 03.01.17.
 */
public interface SendWithOptionsStep extends SendStep{

    /**
     * Specify further options. Some common options are directly included in the <code>SendOptions</code> class others
     * can be manually added using the <code>SendOptions#addCustom</code>-method.<br>
     * <b>Example:</b> <code>new SendOptions().setPlatform(EnumSet.of(SendOptions.Platform.AMAZON, SendOptions.Platform.ANDROID)).setCustom("foo","bar")</code>
     * @param sendOptions
     * @return a SendStep
     * @see <a href="https://documentation.onesignal.com/reference#section-example-code-create-notification">OneSignal documentation</a>
     */
    SendStep addOptions(SendOptions sendOptions);

}
