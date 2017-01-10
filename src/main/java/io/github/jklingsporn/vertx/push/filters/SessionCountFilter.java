package io.github.jklingsporn.vertx.push.filters;

import io.github.jklingsporn.vertx.push.filters.relations.EqualRelation;
import io.github.jklingsporn.vertx.push.filters.relations.GreaterRelation;
import io.github.jklingsporn.vertx.push.filters.relations.LessRelation;
import io.github.jklingsporn.vertx.push.filters.relations.NotEqualRelation;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public interface SessionCountFilter extends GreaterRelation<Integer>,LessRelation<Integer>,EqualRelation<Integer>,NotEqualRelation<Integer> {

    default String getContext(){
        return "session_count";
    }
}
