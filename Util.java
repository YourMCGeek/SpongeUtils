package ga.yourmcgeek.util.util;

import com.google.inject.Inject;
import ga.yourmcgeek.util.util.commands.ForumCommand;
import ga.yourmcgeek.util.util.commands.LinkingCommand;
import ga.yourmcgeek.util.util.commands.VersionsCommand;
import ga.yourmcgeek.util.util.commands.WikiCommand;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

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

        int x = 0;

        this.logger.info("Generating Utils...");

        Sponge.getCommandManager().register(this, wiki, "wiki");
        x++;
        this.logger.info("Wiki Util initialized.");
        Sponge.getCommandManager().register(this, versions, "version", "servers", "versions", "packs", "pack");
        x++;
        this.logger.info("Versions Util initialized.");
        Sponge.getCommandManager().register(this, link, "linking", "accounts", "account", "link");
        x++;
        this.logger.info("Linking Util initialized.");
        Sponge.getCommandManager().register(this, forum, "forums", "forum");
        x++;
        this.logger.info("Forum Util initialized.");
        this.logger.info("Util Generation Completed. " + x + " utils generated.");


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

    CommandSpec link = CommandSpec.builder()
            .description(Text.of("Provides direct url to forum post on linking accounts"))
            .permission("utils.link")
            .executor(new LinkingCommand())
            .build();

    CommandSpec forum = CommandSpec.builder()
            .description(Text.of("Provides link to forums"))
            .permission("utils.forum")
            .executor(new ForumCommand())
            .build();

}
