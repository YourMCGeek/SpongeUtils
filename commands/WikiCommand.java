package ga.yourmcgeek.util.util.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import static ga.yourmcgeek.util.util.commands.VersionsCommand.*;

public class WikiCommand implements CommandExecutor {


    Text wiki = Text.of(TextColors.DARK_PURPLE, "  >>>>> ", TextColors.GREEN, "https://shadownode.ca/wiki/",
            TextColors.DARK_PURPLE, "<<<<<    ");


    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(header);
        src.sendMessage(body);
        src.sendMessage(wiki);
        src.sendMessage(footer);

        return CommandResult.success();
    }

}
