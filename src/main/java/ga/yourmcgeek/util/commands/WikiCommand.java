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



public class WikiCommand implements CommandExecutor {
    public WikiCommand() {}

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (!Util.getInstance().getConfig().wikiEnabled) {
            Util.getInstance().getLogger().warn("Command not invoked");
            return CommandResult.empty();
        }
        /*catch (NullPointerException e){
            Util.getInstance().getLogger().error("Error when running this command. Report NPE to the developer.");
            e.toString();
        }*/

        PaginationList.Builder builder = PaginationList.builder();
        builder.title(Text.of(Util.getInstance().getConfig().utilPrefix))
                .contents(Text.of(Util.getInstance().getConfig().utilBody), Text.of(Util.getInstance().getConfig().linksWiki))
                .padding(Text.of(Util.getInstance().getConfig().utilPadding));

        return CommandResult.success();
    }

    public void register() {
        CommandSpec wiki = CommandSpec.builder()
                .description(Text.of("Provides a link to the wiki"))
                .permission("utils.wiki")
                .executor(this)
                .build();
        Sponge.getCommandManager().register(Util.getInstance(), wiki, "wiki");
    }
}
