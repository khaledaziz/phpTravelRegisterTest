package helper;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:app.properties" // mention the property file name
})
public interface AppConfig extends Config {
    String baseUrl();

}
