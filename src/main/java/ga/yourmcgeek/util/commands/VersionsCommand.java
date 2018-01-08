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


public class VersionsCommand implements CommandExecutor {

    private final Util plugin;

    public VersionsCommand(Util plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        if (!plugin.getConfig().versionsEnabled) {
            return CommandResult.empty();
        }

        else {
            src.sendMessage(Text.of(plugin.getConfig().utilHeader));
            src.sendMessage(Text.of(plugin.getConfig().utilBody));
            src.sendMessage(Text.of(plugin.getConfig().linksVersions));
            src.sendMessage(Text.of(plugin.getConfig().utilFooter));

        }


        return CommandResult.success();
    }

    public void register() {
        CommandSpec versions = CommandSpec.builder()
                .description(Text.of("Provides pack versions"))
                .permission("utils.versions")
                .executor(this)
                .build();

        Sponge.getCommandManager().register(this, versions, "version", "servers", "versions", "packs", "pack");

    }

}
