package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.PushMessageStep;

/**
 * Created by jensklingsporn on 03.01.17.
 */
class NoopTemplateStep extends  AbstractNoopPushStep implements PushMessageStep {

    private static NoopTemplateStep INSTANCE;
    public static NoopTemplateStep getInstance() {
        return INSTANCE == null ? INSTANCE = new NoopTemplateStep() : INSTANCE;
    }
}
