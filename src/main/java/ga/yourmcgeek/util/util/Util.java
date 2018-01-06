package ga.yourmcgeek.util.util;

import com.google.inject.Inject;
import ga.yourmcgeek.util.util.commands.VersionsCommand;
import ga.yourmcgeek.util.util.commands.WikiCommand;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

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




    @Listener
    public void onServerStart(GameStartedServerEvent event) {

        this.logger.info("Generating Utils");

        Sponge.getCommandManager().register(this, wiki, "wiki");
        this.logger.info("Wiki Util initialized.");
        Sponge.getCommandManager().register(this, versions, "version", "servers", "versions", "packs", "pack");
        this.logger.info("Verions Util initialized");

        this.logger.info("Util Generation Completed.");


    }

    CommandSpec wiki = CommandSpec.builder()
            .description(Text.of("Provides a link to the wiki"))
            .permission("utils.wiki")
            .executor(new WikiCommand())
            .build();

    CommandSpec versions = CommandSpec.builder()
            .description(Text.of("Provides pack versions"))
            .permission("utils.versions")
            .executor(new VersionsCommand())
            .build();
}
