package io.github.jklingsporn.vertx.push.filters.relations;

import io.github.jklingsporn.vertx.push.filters.Filter;
import io.github.jklingsporn.vertx.push.filters.FilterImpl;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public interface NotExistsRelation<X> extends Relation {

    /**
     * Checking if a value not exists.
     * The unit of the value depends on the chosen <code>Filter</code>.
     * @param value
     * @return a Filter
     */
    default Filter notExists(X value){
        FilterImpl filter = new FilterImpl(getContext());
        filter.content().put("relation", "not_exists").put("value", value.toString());
        return filter;
    }

}
