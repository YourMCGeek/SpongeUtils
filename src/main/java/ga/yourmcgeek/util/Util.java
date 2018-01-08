package ga.yourmcgeek.util;

import com.google.inject.Inject;
import ga.yourmcgeek.util.commands.ForumCommand;
import ga.yourmcgeek.util.commands.LinkingCommand;
import ga.yourmcgeek.util.commands.VersionsCommand;
import ga.yourmcgeek.util.commands.WikiCommand;
import ga.yourmcgeek.util.config.Configuration;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.io.File;

@Plugin(
        id = "util",
        name = "Util",
        description = "Utils Plugin",
        authors = "YourMCGeek"
)
public class Util {

    // Sponge provides a default Logger instance to all plugins
    // as long as @Inject is used.
    @Inject
    private Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    public File defaultConfig;

    public Configuration config;




    @Listener
    public void onServerStart(GameStartedServerEvent event) {

        int x = 0;

        this.logger.info("Generating Utils...");

        new WikiCommand(this).register();
        x++;
        this.logger.info("Wiki Util initialized.");


        new VersionsCommand(this).register();
        x++;
        this.logger.info("Versions Util initialized.");


        new LinkingCommand(this).register();
        x++;
        this.logger.info("Linking Util initialized.");


        new ForumCommand(this).register();
        x++;
        this.logger.info("Forum Util initialized.");

        this.logger.info("Util Generation Completed. " + x + " utils generated.");


    }







    public Logger getLogger() {
        return logger;
    }

    public Configuration getConfig() {
        return config;
    }

}
