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


public class LinkingCommand implements CommandExecutor {
    public LinkingCommand() {}

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (!Util.getInstance().getConfig().linkingEnabled){
            return CommandResult.empty();
        }
        else {
            PaginationList.Builder builder = PaginationList.builder();

            builder.title(Text.of(Util.getInstance().getConfig().utilPrefix))
                    .contents(Text.of(Util.getInstance().getConfig().utilBody), Text.of(Util.getInstance().getConfig().linksLinking))
                    .padding(Text.of(Util.getInstance().getConfig().utilPadding));
        }
        return CommandResult.success();
    }

    public void register() {
        CommandSpec link = CommandSpec.builder()
                .description(Text.of("Provides direct url to forum post on linking accounts"))
                .permission("utils.link")
                .executor(this)
                .build();
        Sponge.getCommandManager().register(Util.getInstance(), link, "linking", "accounts", "account", "link");
    }
}
