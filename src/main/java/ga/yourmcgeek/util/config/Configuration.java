package ga.yourmcgeek.util.config;

import ga.yourmcgeek.util.Util;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.IOException;

public final class Configuration {

    public final Util plugin;

    private ConfigurationLoader loader;
    private ConfigurationNode config;

    // Commands

    public boolean forumEnabled;
    public boolean linkingEnabled;
    public boolean versionsEnabled;
    public boolean wikiEnabled;

    // Formatting

    public String utilPadding;
    public String utilPrefix;
    public String utilBody;

    // Links

    public String linksForum;
    public String linksLinking;
    public String linksVersions;
    public String linksWiki;

    public Configuration(Util plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        try {
            loader = HoconConfigurationLoader.builder().setFile(plugin.defaultConfig).build();
            saveDefaultConfig();
            config = loader.load();


            // Commands

            forumEnabled = config.getNode("Forum", "Enabled").getBoolean();
            linkingEnabled = config.getNode("Linking", "Enabled").getBoolean();
            versionsEnabled = config.getNode("Versions", "Enabled").getBoolean();
            wikiEnabled = config.getNode("Wiki", "Enabled").getBoolean();

            // Formatting

            utilPadding = config.getNode("Message", "Padding").getString();
            utilPrefix = config.getNode("Message", "Prefix").getString();
            utilBody = config.getNode("Message", "Body").getString();

            // Links

            linksForum = config.getNode("Links", "Forum").getString();
            linksLinking = config.getNode("Links", "Linking").getString();
            linksVersions = config.getNode("Links", "Versions").getString();
            linksWiki = config.getNode("Links", "Wiki").getString();



        } catch (IOException e) {
            plugin.getLogger().error("Could not create a default config. Please report this.");
        }
    }

    public void saveDefaultConfig() {
        if (!plugin.defaultConfig.exists()) {
            try {
                plugin.defaultConfig.createNewFile();
                config = loader.load();

                // Configuration for Commands

                config.getNode("Forum", "Enabled").setValue(true);
                config.getNode("Linking", "Enabled").setValue(true);
                config.getNode("Versions", "Enabled").setValue(true);
                config.getNode("Wiki", "Enabled").setValue(true);

                // Formatting Configuration

                config.getNode("Message", "Padding").setValue("&7=");
                config.getNode("Message", "Prefix").setValue("&8[&5ShadowNode&8]");
                config.getNode("Message", "Body").setValue("&5&l                               Click this link");


                // Links Configuration

                config.getNode("Links", "Forum").setValue("&6                         https://shadownode.ca/");
                config.getNode("Links", "Linking").setValue("&6                          https://goo.gl/CtrQ6o");
                config.getNode("Links", "Versions").setValue("&6                  https://shadownode.ca/servers/");
                config.getNode("Links", "Wiki").setValue("&6                   https://shadownode.ca/wiki/");


                loader.save(config);


            } catch (IOException e) {
                plugin.getLogger().error("Could not create a default config. Please report this.");
            }
        }
    }


}
