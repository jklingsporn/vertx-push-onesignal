package io.github.jklingsporn.vertx.push.filters;

import io.vertx.core.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jensklingsporn on 05.01.18.
 */
public class FilterTest {

    private void assertRelation(JsonObject content, String expected){
        assertJsonKey(content, expected, "relation");
    }

    private void assertField(JsonObject content, String expected){
        assertJsonKey(content, expected, "field");
    }

    private void assertJsonKey(JsonObject content, String expected, String key){
        Assert.assertEquals(expected,content.getString(key));
    }

    private JsonObject assertFilter(Filter filter, String expectedRelation, String expectedField){
        JsonObject content = filter.content();
        Assert.assertNotNull(content);
        assertRelation(content, expectedRelation);
        assertField(content, expectedField);
        return content;
    }

    private JsonObject assertFilter(Filter filter, String expectedRelation, String expectedField, Object value){
        JsonObject content = assertFilter(filter, expectedRelation, expectedField);
        assertJsonKey(content, value.toString() , "value");
        return content;
    }


    @Test
    public void testLastSessionGreater(){
        JsonObject lastSession = assertFilter(Filters.lastSession().greater(1f), ">", "last_session");
        assertJsonKey(lastSession, "1.0", "hours_ago");
    }

    @Test
    public void testLastSessionLess(){
        JsonObject lastSession = assertFilter(Filters.lastSession().less(1f), "<", "last_session");
        assertJsonKey(lastSession, "1.0", "hours_ago");
    }

    @Test
    public void testFirstSessionGreater(){
        JsonObject lastSession = assertFilter(Filters.firstSession().greater(1f), ">", "first_session");
        assertJsonKey(lastSession, "1.0", "hours_ago");
    }

    @Test
    public void testFirstSessionLess(){
        JsonObject lastSession = assertFilter(Filters.firstSession().less(1f), "<", "first_session");
        assertJsonKey(lastSession, "1.0", "hours_ago");
    }

    @Test
    public void testSessionCountGreater(){
        assertFilter(Filters.sessionCount().greater(1), ">", "session_count", 1);
    }

    @Test
    public void testSessionCountEqual(){
        assertFilter(Filters.sessionCount().equal(1), "=", "session_count", 1);
    }

    @Test
    public void testSessionCountNotEqual(){
        assertFilter(Filters.sessionCount().notEqual(1), "!=", "session_count", 1);
    }

    @Test
    public void testSessionCountLess(){
        assertFilter(Filters.sessionCount().less(1), "<", "session_count", 1);
    }

    @Test
    public void testSessionTimeGreater(){
        assertFilter(Filters.sessionTime().greater(1), ">", "session_time", 1);
    }

    @Test
    public void testSessionTimeLess(){
        assertFilter(Filters.sessionTime().less(1), "<", "session_time", 1);
    }

    @Test
    public void testAmountSpentGreater(){
        assertFilter(Filters.amountSpent().greater(1f), ">", "amount_spent", 1.0);
    }

    @Test
    public void testAmountSpentEqual(){
        assertFilter(Filters.amountSpent().equal(1f), "=", "amount_spent", 1.0);
    }

    @Test
    public void testAmountSpentLess(){
        assertFilter(Filters.amountSpent().less(1f), "<", "amount_spent", 1.0);
    }

    @Test
    public void testBoughtSkuGreater(){
        JsonObject content = assertFilter(Filters.boughtSku("foo").greater(1f), ">", "bought_sku", 1.0);
        assertJsonKey(content, "foo", "key");
    }

    @Test
    public void testBoughtSkuEqual(){
        JsonObject content = assertFilter(Filters.boughtSku("bar").equal(1f), "=", "bought_sku", 1.0);
        assertJsonKey(content, "bar", "key");
    }

    @Test
    public void testBoughtSkuLess(){
        JsonObject content = assertFilter(Filters.boughtSku("baz").less(1f), "<", "bought_sku", 1.0);
        assertJsonKey(content, "baz", "key");
    }

    @Test
    public void testLanguageEqual(){
        assertFilter(Filters.language().equal("fr"), "=", "language");
    }

    @Test
    public void testLanguageNotEqual(){
        assertFilter(Filters.language().notEqual("fr"), "!=", "language");
    }

    @Test
    public void testAppVersionGreater(){
        assertFilter(Filters.appVersion().greater("v123"), ">", "app_version", "v123");
    }

    @Test
    public void testAppVersionEqual(){
        assertFilter(Filters.appVersion().equal("v123"), "=", "app_version", "v123");
    }

    @Test
    public void testAppVersionNotEqual(){
        assertFilter(Filters.appVersion().notEqual("v123"), "!=", "app_version", "v123");
    }

    @Test
    public void testAppVersionLess(){
        assertFilter(Filters.appVersion().less("v123"), "<", "app_version", "v123");
    }

    @Test
    public void testLocation(){
        JsonObject content = Filters.location(1, "0.999", "21.123").content();
        assertJsonKey(content, "1", "radius");
        assertJsonKey(content, "0.999", "lat");
        assertJsonKey(content, "21.123", "long");
        assertField(content, "location");
    }

    @Test
    public void testEmail(){
        assertFilter(Filters.email("foo@bar.baz"), null, "email", "foo@bar.baz");
    }

    @Test
    public void testCountryEqual(){
        assertFilter(Filters.country("de"), "=", "country", "de");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountryInvalidCountryCode(){
        Filters.country("abc");
        Assert.fail();
    }

    @Test
    public void assertSameNoopAndOther(){
        Filter session = Filters.sessionCount().equal(1);
        Filter and = Filters.noop().and(session);
        Assert.assertSame(session,and);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void assertNoopCannotBeAddedViaAnd(){
        Filter session = Filters.sessionCount().equal(1);
        session.and(Filters.noop());
        Assert.fail();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void assertNoopCannotBeAddedViaOr(){
        Filter session = Filters.sessionCount().equal(1);
        session.or(Filters.noop());
        Assert.fail();
    }


}
