package ga.yourmcgeek.util.commands;

import ga.yourmcgeek.util.Util;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;


public class LinkingCommand implements CommandExecutor {


    private final Util plugin;

    public LinkingCommand(Util plugin) {
        this.plugin = plugin;
    }


    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        if (!plugin.getConfig().linkingEnabled){
            return CommandResult.empty();
        }

        else {
            src.sendMessage(Text.of(plugin.getConfig().utilHeader));
            src.sendMessage(Text.of(plugin.getConfig().utilBody));
            src.sendMessage(Text.of(plugin.getConfig().linksLinking));
            src.sendMessage(Text.of(plugin.getConfig().utilFooter));

        }

        return CommandResult.success();
    }

    public void register() {
        CommandSpec link = CommandSpec.builder()
                .description(Text.of("Provides direct url to forum post on linking accounts"))
                .permission("utils.link")
                .executor(this)
                .build();

        Sponge.getCommandManager().register(this, link, "linking", "accounts", "account", "link");

    }
}
