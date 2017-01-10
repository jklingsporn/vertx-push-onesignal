package io.github.jklingsporn.vertx.push.filters.relations;

import io.github.jklingsporn.vertx.push.filters.Filter;
import io.github.jklingsporn.vertx.push.filters.FilterImpl;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public interface EqualRelation<X> extends Relation {

    /**
     * Compare the user's value against this value using a equal-relation.
     * The unit of the value depends on the chosen <code>Filter</code>.
     * @param value
     * @return a Filter
     */
    default Filter equal(X value){
        FilterImpl filter = new FilterImpl(getContext());
        filter.content().put("relation", "=").put("value", value.toString());
        return filter;
    }

}
