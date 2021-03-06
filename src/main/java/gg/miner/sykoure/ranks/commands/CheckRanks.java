package gg.miner.sykoure.ranks.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import gg.miner.sykoure.ranks.gui.CheckRanksGui;

public class CheckRanks implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (!(src instanceof Player)) {
            src.sendMessage((Text.of(TextColors.DARK_RED, "ERROR, this command can only be used by a player")));
            return CommandResult.empty();
        }

        Player sender = (Player) src;
        Player target = args.<Player>getOne("target").get();

        if (target.equals(src)) {
            //When a player is checking his proper ranks
            CheckRanksGui.openCheckRanksGUI(sender);
        } else {
            src.sendMessage(Text.of(TextColors.DARK_RED, "ERROR, You can't check the rank of other players"));
        }

        return CommandResult.success();
    }
}
