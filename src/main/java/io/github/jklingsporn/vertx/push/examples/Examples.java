package io.github.jklingsporn.vertx.push.examples;

import io.github.jklingsporn.vertx.push.PushClient;
import io.github.jklingsporn.vertx.push.Segments;
import io.github.jklingsporn.vertx.push.SendOptions;
import io.github.jklingsporn.vertx.push.filters.Filter;
import io.github.jklingsporn.vertx.push.filters.Filters;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;

/**
 * Created by jensklingsporn on 03.01.17.
 */
public class Examples {

    public static void main(String[] args) {
        exampleOne();
    }

    public static void exampleOne(){
        PushClient.create(Vertx.vertx(), "YOUR_APP_ID", "YOUR_API_KEY").
                //setup a template on the OneSignal-Dashboard and use it here
                withTemplate("someTemplateId").
                //all users should receive this
                targetBySegments(Segments.ALL).
                sendNow(
                        h -> {
                            if (h.succeeded()) {
                                System.err.println(h.result().encodePrettily());
                            } else {
                                h.cause().printStackTrace();
                            }
                            System.exit(0);
                        });
    }

    public static void exampleTwo(){
        PushClient.create(Vertx.vertx(), "YOUR_APP_ID", "YOUR_API_KEY").
                //setup the content of the message on the serverside
                withContent(new JsonObject().put("en", "English Content.").put("de","Deutscher Inhalt.")).
                    //add a heading
                    withHeadings(new JsonObject().put("en","Awesome App").put("de","Super App")).
                //target the audience using filters (e.g. people having used your app only once or haven't logged in since 24 hours)
                targetByFilter(Filters.lastSession().greater(24f).or(Filters.sessionCount().equal(1))).
                //but only ios users!
                addOptions(new SendOptions().setPlatform(EnumSet.of(SendOptions.Platform.IOS))).
                //and wait another minute until they receive it
                sendAfter(
                        ZonedDateTime.ofInstant(Instant.now().plus(1, ChronoUnit.MINUTES), ZoneId.systemDefault()),
                        h -> {
                            if (h.succeeded()) {
                                System.err.println(h.result().encodePrettily());
                            } else {
                                h.cause().printStackTrace();
                            }
                            System.exit(0);
                        });
    }

    public static void exampleThree(){
        PushClient.create(Vertx.vertx(), "YOUR_APP_ID", "YOUR_API_KEY").
                //Leave me alone, I want to set all parameters on my own
                raw().
                //use the JsonObject-constructor or setCustom-method to add arbitrary parameters
                addOptions(new SendOptions(new JsonObject().put("customKey1","customValue1")).setCustom("customKey2", "customValue2")).
                sendNow(
                        h -> {
                            if (h.succeeded()) {
                                System.err.println(h.result().encodePrettily());
                            } else {
                                h.cause().printStackTrace();
                            }
                            System.exit(0);
                        });
    }

    public static void exampleFour(){
        PushClient.create(Vertx.vertx(), "YOUR_APP_ID", "YOUR_API_KEY").
                        //setup the content of the message on the serverside
                        withContent(new JsonObject().put("en", "English Content.").put("de","Deutscher Inhalt.")).
                        //add a heading
                        withHeadings(new JsonObject().put("en","Awesome App").put("de","Super App")).
                        //target the audience using OneSignal playerID(s)
                        targetByPlayerIds(new JsonArray().add("1dd608f2-c6a1-11e3-851d-000c2940e62c")).
                        //but only ios users!
                        addOptions(new SendOptions().setPlatform(EnumSet.of(SendOptions.Platform.IOS))).
                        //and wait another minute until they receive it
                        sendAfter(
                                ZonedDateTime.ofInstant(Instant.now().plus(1, ChronoUnit.MINUTES), ZoneId.systemDefault()),
                                h -> {
                                    if (h.succeeded()) {
                                        System.err.println(h.result().encodePrettily());
                                    } else {
                                        h.cause().printStackTrace();
                                    }
                                    System.exit(0);
                                });
    }

    public static void exampleFive(){
        //chain tag-filters using noop
        Collection<String> tagValues = Arrays.asList("foo","bar","baz");
        Filter filter = Filters.noop();
        for (String tagValue : tagValues) {
            filter = filter.or(Filters.tag("MY_USER_TAG").equal(tagValue));
        }
        PushClient.create(Vertx.vertx(), "YOUR_APP_ID", "YOUR_API_KEY").
                        //setup the content of the message on the serverside
                        withContent(new JsonObject().put("en", "English Content.").put("de", "Deutscher Inhalt.")).
                        //add a heading
                        withHeadings(new JsonObject().put("en", "Awesome App").put("de", "Super App")).

                        targetByFilter(filter).
                        //but only ios users!
                        addOptions(new SendOptions().setPlatform(EnumSet.of(SendOptions.Platform.IOS))).
                        //and wait another minute until they receive it
                        sendAfter(
                        ZonedDateTime.ofInstant(Instant.now().plus(1, ChronoUnit.MINUTES), ZoneId.systemDefault()),
                        h -> {
                            if (h.succeeded()) {
                                System.err.println(h.result().encodePrettily());
                            } else {
                                h.cause().printStackTrace();
                            }
                            System.exit(0);
                        });
    }

    public static void exampleHttpClientProxy(){
        String proxyHost = "yourProxyHost";
        int proxyPort = 123;
        HttpClientOptions options = new HttpClientOptions().setSsl(true).setTrustAll(true);
        HttpClient client = Vertx.vertx().createHttpClient(options.setProxyOptions(new ProxyOptions().setHost(proxyHost).setPort(proxyPort).setType(ProxyType.HTTP)));
        PushClient.create(client, "YOUR_APP_ID", "YOUR_API_KEY").
                //setup the content of the message on the serverside
                        withContent(new JsonObject().put("en", "English Content.").put("de","Deutscher Inhalt.")).
                //target the audience using OneSignal playerID(s)
                        targetByPlayerIds(new JsonArray().add("1dd608f2-c6a1-11e3-851d-000c2940e62c")).
                //and wait another minute until they receive it
                        sendNow(
                        h -> {
                            if (h.succeeded()) {
                                System.err.println(h.result().encodePrettily());
                            } else {
                                h.cause().printStackTrace();
                            }
                            System.exit(0);
                        });
    }


}
