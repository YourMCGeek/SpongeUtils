package ga.yourmcgeek.util.config;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class Config {

        public final static TypeToken<Config> type = TypeToken.of(Config.class);
        @Setting
        public Link forum;
        @Setting public Link linking;
        @Setting public Link versions;
        @Setting public Link wiki;
        @Setting public Message message;

        @ConfigSerializable
        public static class Link {
            @Setting public boolean enabled;
            @Setting public String link;
        }

        @ConfigSerializable
        public static class Message {
            @Setting public String padding;
            @Setting public String body;
            @Setting public String prefix;
        }

}
