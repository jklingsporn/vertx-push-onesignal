# vertx-push-onesignal
Send push notifications asynchronously in your [vertx](http://vertx.io/) application with [OneSignal](https://onesignal.com/).

#### Example
```
//Create a client. You get the APP_ID and API_KEY from the OneSignal-dashboard
PushClient.create(Vertx.vertx(), "YOUR_APP_ID", "YOUR_API_KEY").
                //setup the content of the message on the serverside
                withContent(new JsonObject().put("en", "English Content.").put("de","Deutscher Inhalt.")).
                //all users should receive this
                targetBySegments(Segments.ALL).
                sendNow(
                        h -> {
                            if (h.succeeded()) {
                                System.err.println(h.result().encodePrettily());
                            } else {
                                h.cause().printStackTrace();
                            }
                        });
```

More examples can be found in the [``Examples``](https://github.com/jklingsporn/vertx-push/blob/master/src/main/java/io/github/jklingsporn/vertx/push/examples/Examples.java)-class.

#### Why would I use this library over writing my own CURL?
Sure you can do this. What this library gives you on top is validation. When writing your own request it is absolutely
fine to both target users by segments and filters. This library makes sure that you only use one targeting parameter.
Also the content of a message can either be defined by using templates _or_ setting the content like in the example above, this
library lets you set exactly one way to deliver messages.

**Filters**

Targeting users using filters can be tricky when writing your own CURL call. You have to remember which relation can be used
for which filter. This library only lets you use the relations that are allowed for a given filter:

```
//using a filter to target only users that haven't used the app for 24 hours
Filters.lastSession().greater(24);  //OK
Filters.lastSession().less(24);     //OK
Filters.lastSession().exists(24);   //Compiler-error

//Combining filters using OR
Filters.lastSession().greater(24).or(Filters.sessionCount().equal(1));
...
```

# requirements
To use this library you need to create a [OneSignal](https://onesignal.com/)-account and configure your clients (web or mobile) accordingly.

# maven
```
<dependency>
  <groupId>io.github.jklingsporn</groupId>
  <artifactId>vertx-push-onesignal</artifactId>
  <version>1.9</version>
</dependency>
```

# disclaimer
The author has no connection to the companies behind OneSignal or Vertx. This library also comes without any warranty - just take
it or leave it.
