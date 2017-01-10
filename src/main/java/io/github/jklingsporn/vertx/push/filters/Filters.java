package io.github.jklingsporn.vertx.push.filters;

/**
 * Created by jensklingsporn on 04.01.17.
 */
public class Filters {
    private Filters() {}

    /**
     * A filter based on the number of hours before or after the users last session.
     * @return a LastSessionFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static LastSessionFilter lastSession(){
        return new LastSessionFilter(){};
    }

    /**
     * A filter based on the number of hours before or after the users first session.
     * @return a FirstSessionFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static FirstSessionFilter firstSession(){
        return new FirstSessionFilter(){};
    }

    /**
     * A filter based on the number of user sessions.
     * @return a SessionCountFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static SessionCountFilter sessionCount(){
        return new SessionCountFilter(){};
    }

    /**
     * A filter based on time in seconds the user has been in your app.
     * @return a SessionTimeFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static SessionTimeFilter sessionTime(){
        return new SessionTimeFilter(){};
    }

    /**
     * A filter based on the amount in USD a user has spent on IAP (In App Purchases).
     * @return a AmountSpentFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static AmountSpentFilter amountSpent(){return new AmountSpentFilter(){};}

    /**
     * A filter based on a SKU purchased in your app as an IAP (In App Purchases).
     * @param key Example: "com.domain.100coinpack"
     * @return a BoughtSkuFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static BoughtSkuFilter boughtSku(String key){
        return new BoughtSkuFilterImpl(key);
    }

    /**
     * A filter based on a tag.
     * @param key a tag or <code>null</code> when you use the <code>ExistsRelation</code> or <code>NotExistsRelation</code>
     * @return a TagFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static TagFilter tag(String key){
        return new TagFilterImpl(key);
    }

    /**
     * A filter based on a 2 character language code.
     * @return a LanguageFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     * @see <a href="https://documentation.onesignal.com/docs/language-localization">Allowed languages</a>
     */
    public static LanguageFilter language(){return new LanguageFilter(){};}

    /**
     * A filter based on the amount in USD a user has spent on IAP (In App Purchases).
     * @return a AppVersionFilter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static AppVersionFilter appVersion(){return new AppVersionFilter() {};}

    /**
     * A filter based on a user's location.
     * @param radiusInMeters
     * @param latitude
     * @param longitude
     * @return a Filter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static Filter location(Integer radiusInMeters, String latitude, String longitude){
        FilterImpl filter = new FilterImpl("location");
        filter.content().put("radius",radiusInMeters).put("lat",latitude).put("long",longitude);
        return filter;
    }

    /**
     * A filter based on an email address.
     * @param email
     * @return a Filter
     * @see <a href="https://documentation.onesignal.com/reference#section-send-to-users-based-on-filters">Notification filters</a>
     */
    public static Filter email(String email){
        FilterImpl filter = new FilterImpl("email");
        filter.content().put("value",email);
        return filter;
    }

    private static class TagFilterImpl implements TagFilter{

        private final String key;

        private TagFilterImpl(String key) {
            this.key = key;
        }

        @Override
        public Filter notExists(String value) {
            Filter filter = TagFilter.super.notExists(value);
            return addKey(filter);
        }

        @Override
        public Filter notEqual(String value) {
            Filter filter = TagFilter.super.notEqual(value);
            return addKey(filter);
        }

        @Override
        public Filter less(String value) {
            Filter filter = TagFilter.super.less(value);
            return addKey(filter);
        }

        @Override
        public Filter greater(String value) {
            Filter filter = TagFilter.super.greater(value);
            return addKey(filter);
        }

        @Override
        public Filter exists(String value) {
            Filter filter = TagFilter.super.exists(value);
            return addKey(filter);
        }

        @Override
        public Filter equal(String value) {
            Filter filter = TagFilter.super.equal(value);
            return addKey(filter);
        }

        private Filter addKey(Filter filter){
            filter.content().put("key",key);
            return filter;
        }
    }

    private static class BoughtSkuFilterImpl implements BoughtSkuFilter{
        private final String key;

        private BoughtSkuFilterImpl(String key) {
            this.key = key;
        }

        @Override
        public Filter less(Float value) {
            Filter filter = BoughtSkuFilter.super.less(value);
            return addKey(filter);
        }

        @Override
        public Filter greater(Float value) {
            Filter filter = BoughtSkuFilter.super.greater(value);
            return addKey(filter);
        }

        @Override
        public Filter equal(Float value) {
            Filter filter = BoughtSkuFilter.super.equal(value);
            return addKey(filter);
        }

        private Filter addKey(Filter filter){
            filter.content().put("key",key);
            return filter;
        }
    }

}
