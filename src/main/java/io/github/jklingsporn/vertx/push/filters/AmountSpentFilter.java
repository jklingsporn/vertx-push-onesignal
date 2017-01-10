package io.github.jklingsporn.vertx.push.filters;

import io.github.jklingsporn.vertx.push.filters.relations.EqualRelation;
import io.github.jklingsporn.vertx.push.filters.relations.GreaterRelation;
import io.github.jklingsporn.vertx.push.filters.relations.LessRelation;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public interface AmountSpentFilter extends GreaterRelation<Float>,LessRelation<Float>,EqualRelation<Float> {

    default String getContext(){
        return "amount_spent";
    }
}
