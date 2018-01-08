package ga.yourmcgeek.util.util.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public class VersionsCommand implements CommandExecutor {


    public static Text header = Text.of(TextColors.DARK_GRAY, "==============[", TextColors.DARK_PURPLE,
            "ShadowNode", TextColors.DARK_GRAY, "]===============");
    public static Text body = Text.of(TextColors.DARK_PURPLE, TextStyles.BOLD, "             Click this link");

    Text servers = Text.of(TextColors.DARK_PURPLE, "  >>> ", TextColors.GREEN, "https://shadownode.ca/servers/",
            TextColors.DARK_PURPLE, "<<<");

    public static Text footer = Text.of(TextColors.DARK_GRAY, "=========================================");

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        src.sendMessage(header);
        src.sendMessage(body);
        src.sendMessage(servers);
        src.sendMessage(footer);
        return CommandResult.success();
    }

}
