package ga.yourmcgeek.util.commands;

import ga.yourmcgeek.util.Util;
import ga.yourmcgeek.util.util.Utils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;

public class ForumCommand implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        PaginationList.builder()
                .title(Utils.toText(Util.getInstance().getConfig().message.prefix))
                .contents(Utils.toText(Util.getInstance().getConfig().message.body), Utils.toText(Util.getInstance().getConfig().forum.link))
                .padding(Utils.toText(Util.getInstance().getConfig().message.padding))
                .sendTo(src);
        return CommandResult.success();
    }

    public void register() {
        if (Util.getInstance().getConfig().forum.enabled) {
            CommandSpec forum = CommandSpec.builder()
                    .description(Text.of("Provides link to forums"))
                    .permission("utils.forum")
                    .executor(this)
                    .build();
            Sponge.getCommandManager().register(Util.getInstance(), forum, "forums", "forum");
        }
    }
}
