package ga.yourmcgeek.util.commands;

import ga.yourmcgeek.util.Util;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;


public class VersionsCommand implements CommandExecutor {
    public VersionsCommand() {}

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (!Util.getInstance().getConfig().versionsEnabled) {
            return CommandResult.empty();
        }
        else {
            PaginationList.Builder builder = PaginationList.builder();
            builder.title(Text.of(Util.getInstance().getConfig().utilPrefix))
                    .contents(Text.of(Util.getInstance().getConfig().utilBody), Text.of(Util.getInstance().getConfig().linksVersions))
                    .padding(Text.of(Util.getInstance().getConfig().utilPadding));
        }
        return CommandResult.success();
    }
    public void register() {
        CommandSpec versions = CommandSpec.builder()
                .description(Text.of("Provides pack versions"))
                .permission("utils.versions")
                .executor(this)
                .build();
        Sponge.getCommandManager().register(Util.getInstance(), versions, "version", "servers", "versions", "packs", "pack");
    }
}
