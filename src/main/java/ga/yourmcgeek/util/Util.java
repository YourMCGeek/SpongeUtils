package ga.yourmcgeek.util;

import com.google.inject.Inject;
import ga.yourmcgeek.util.commands.ForumCommand;
import ga.yourmcgeek.util.commands.LinkingCommand;
import ga.yourmcgeek.util.commands.VersionsCommand;
import ga.yourmcgeek.util.commands.WikiCommand;
import ga.yourmcgeek.util.config.Config;
import ga.yourmcgeek.util.init.Resource;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Plugin(id = Resource.ID, name = Resource.NAME,
        description = Resource.DESCRIPTION, version = Resource.VERSION)
public class Util {

    private static Util instance;

    @Inject
    private Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    public Path defaultConfig;

    @Inject @DefaultConfig(sharedRoot = true)
    ConfigurationLoader<CommentedConfigurationNode> loader;

    public Config config;

    @Listener
    public void onInitialization(GameInitializationEvent event) throws IOException, ObjectMappingException {
        instance = this;
        if (!Files.exists(defaultConfig)) {
            Sponge.getAssetManager().getAsset(this, "default.conf").get().copyToFile(defaultConfig);
        }

        config = loader.load().getValue(Config.type);

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

    @Listener
    public void onReload(GameReloadEvent event) throws IOException, ObjectMappingException {
        if (!Files.exists(defaultConfig)) {
            Sponge.getAssetManager().getAsset(this, "default.conf").get().copyToFile(defaultConfig);
        }
        config = loader.load().getValue(Config.type);
    }

    public Logger getLogger() {
        return logger;
    }

    public Config getConfig() {
        return config;
    }

    public static Util getInstance() {
        return instance;
    }

}
