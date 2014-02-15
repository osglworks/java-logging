# OSGL Logging

OSGL Logging is yet another Java Logging facade.

## Why not [Slf4J](http://www.slf4j.org/)

I don't like the Slf4J Logger's API. Specifically the logging API with Throwable arguments, e.g.

```Java
void debug(String msg, Throwable t);
```

The better one in my mind should be

```Java
void debug(Throwable t, String format, Object... args);
```

And yes OSGL logging Logger use the better one ;-)

## Usage

1. Classic usage

```Java
import org.osgl.logging.*;

public class Foo {

    protected final Logger logger = LogManager.get(Foo.class);

    private void bar(int n) {
        logger.trace("enter bar with %s", n);
        try {
            // do something dangerous
        } catch (Exception e) {
            logger.error(e, "error executing bar with %s", n);
        }
    }
}
```

2. Use `LogManager`'s static log methods

```java
import org.osgl.logging.*;

public class Foo {
    private void bar(int n) {
        LogManager.trace("enter bar with %s", n);
        try {
            // do something dangerous
        } catch (Exception e) {
            LogManager.error(e, "error executing bar with %s", n);
        }
    }
}
```

3. Use `LogManager`'s alias `L` and the static log methods

```java
import org.osgl.logging.*;

public class Foo {
    private void bar(int n) {
        L.trace("enter bar with %s", n);
        try {
            // do something dangerous
        } catch (Exception e) {
            L.error(e, "error executing bar with %s", n);
        }
    }
}
```

## Configuration

The only configuration for OSGL logging is to configure the `LogServiceProvider`:

```java
import org.osgl.logging.*;
import org.osgl.logging.service.*;

public class Bootstrap {
    static {
        LogManager.registerLogServiceProvider(new CommonsLoggingServiceProvider());
    }
}
```

At the moment there are four predefined `LogServiceProvider`:

1. CommonsLoggingServiceProvider, bridge to [Apache Commons-Logger](http://commons.apache.org/proper/commons-logging/)
1. Log4jServiceProvider, bridge to [Apache Log4J](http://logging.apache.org/log4j/1.2/)
1. Slf4jServiceProvider, bridge to [Slf4j](http://www.slf4j.org/)
1. TinyLogServiceProvider, bridge to [TinyLog](http://www.tinylog.org/)

Please refer to relevant logging framework documents to check how to configure specific log service.

## Auto discover `LogService`

If user application does not explicitly register log service provider to `LogManager`, then `LogManager` will automatically discover the underline `LogService` by trying to check if a certain Java Class is in the class path. The discovery is proceeded with the following order:

1. Check if TinyLog is in the class path. If found then use `TinyLogServiceProvider`
1. Else then check if Slf4j is in the class path. If found then use `Slf4jServiceProvider`
1. Else then check if Commons-Logging is in the class path. If found then use `CommonsLoggingServiceProvider`
1. Else check if Log4j is in the class path. If found then use `Log4jServiceProvider`
1. Finally if none of the service provider is used, then by default use `JDKLogService`