package io.github.jklingsporn.vertx.push.filters;

import io.github.jklingsporn.vertx.push.filters.relations.EqualRelation;
import io.github.jklingsporn.vertx.push.filters.relations.NotEqualRelation;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public interface LanguageFilter extends EqualRelation<String>,NotEqualRelation<String> {

    default String getContext(){
        return "language";
    }
}
