package io.github.jklingsporn.vertx.push.noop;

import io.github.jklingsporn.vertx.push.PushMessageStep;

/**
 * Created by jensklingsporn on 03.01.17.
 */
class NoopPushContentsStep extends AbstractNoopPushStep implements PushMessageStep {
    private static NoopPushContentsStep INSTANCE;
    public static NoopPushContentsStep getInstance() {
        return INSTANCE == null ? INSTANCE = new NoopPushContentsStep() : INSTANCE;
    }
}
