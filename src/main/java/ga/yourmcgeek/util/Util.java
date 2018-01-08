package ga.yourmcgeek.util;

import com.google.inject.Inject;
import ga.yourmcgeek.util.commands.ForumCommand;
import ga.yourmcgeek.util.commands.LinkingCommand;
import ga.yourmcgeek.util.commands.VersionsCommand;
import ga.yourmcgeek.util.commands.WikiCommand;
import ga.yourmcgeek.util.config.Configuration;
import ga.yourmcgeek.util.init.Resource;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.io.File;

@Plugin(id = Resource.ID, name = Resource.NAME,
        description = Resource.DESCRIPTION, version = Resource.VERSION)
public class Util {
    private static Util instance;
    private static PluginContainer plugin;

    // Sponge provides a default Logger instance to all plugins
    // as long as @Inject is used.
    @Inject
    private Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    public File defaultConfig;

    public Configuration config;

    public static Util getInstance() {
        return instance;
    }

    @Listener
    public void onPreInitializationEvent(GamePreInitializationEvent event) {
        plugin = Sponge.getPluginManager().getPlugin(Resource.ID).get();
        instance = this;
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        int commandsInitialized = 0;

        new WikiCommand().register();
        commandsInitialized++;
        new VersionsCommand().register();
        commandsInitialized++;
        new LinkingCommand().register();
        commandsInitialized++;
        new ForumCommand().register();
        commandsInitialized++;
        this.logger.info("Util Generation Completed. " + commandsInitialized + " utils generated.");
    }

    public Logger getLogger() {
        return logger;
    }
    public Configuration getConfig() {
        return config;
    }

}
