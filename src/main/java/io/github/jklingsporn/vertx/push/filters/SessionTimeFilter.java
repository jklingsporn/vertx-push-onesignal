package io.github.jklingsporn.vertx.push.filters;

import io.github.jklingsporn.vertx.push.filters.relations.GreaterRelation;
import io.github.jklingsporn.vertx.push.filters.relations.LessRelation;

/**
 * Created by jensklingsporn on 04.01.17.
 */
interface SessionTimeFilter extends GreaterRelation<Integer>,LessRelation<Integer> {

    default String getContext(){
        return "session_time";
    }
}
