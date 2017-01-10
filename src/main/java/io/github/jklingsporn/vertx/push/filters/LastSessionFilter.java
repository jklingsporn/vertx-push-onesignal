package io.github.jklingsporn.vertx.push.filters;

import io.github.jklingsporn.vertx.push.filters.relations.GreaterRelation;
import io.github.jklingsporn.vertx.push.filters.relations.LessRelation;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public interface LastSessionFilter extends GreaterRelation<Integer>,LessRelation<Integer> {

    default String getContext(){
        return "last_session";
    }

    @Override
    default Filter greater(Integer value) {
        FilterImpl filter = new FilterImpl(getContext());
        filter.content().put("relation", ">").put("hours_ago", value.toString());
        return filter;
    }

    @Override
    default Filter less(Integer value) {
        FilterImpl filter = new FilterImpl(getContext());
        filter.content().put("relation", "<").put("hours_ago", value.toString());
        return filter;
    }
}
