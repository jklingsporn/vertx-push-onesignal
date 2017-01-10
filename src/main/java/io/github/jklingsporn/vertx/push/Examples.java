package io.github.jklingsporn.vertx.push;

import io.github.jklingsporn.vertx.push.filters.Filters;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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
                withContent(new JsonObject().put("en", "English Content.").put("de","Deutscher Titel.")).
                    //add a heading
                    withHeadings(new JsonObject().put("en","Awesome App").put("de","Super App")).
                //target the audience using filters (e.g. people having used your app only once and haven't logged in since 24 hours)
                targetByFilter(Filters.lastSession().greater(24).or(Filters.sessionCount().equal(1))).
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
}
