package io.github.jklingsporn.vertx.push.filters;

import io.github.jklingsporn.vertx.push.filters.relations.*;

/**
 * Created by jensklingsporn on 09.01.17.
 */
public interface TagFilter extends GreaterRelation<String>, LessRelation<String>, EqualRelation<String>, NotEqualRelation<String>, ExistsRelation<String>, NotExistsRelation<String> {

    default String getContext(){
        return "tag";
    }

}
