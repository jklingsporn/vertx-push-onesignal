package io.github.jklingsporn.vertx.push.filters;

import io.github.jklingsporn.vertx.push.filters.relations.*;

/**
 * Created by jensklingsporn on 09.01.17.
 */
public interface BoughtSkuFilter extends GreaterRelation<Float>, LessRelation<Float>, EqualRelation<Float>{

    default String getContext(){
        return "bought_sku";
    }

}
