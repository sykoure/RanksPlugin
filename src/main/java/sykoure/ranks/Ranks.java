package sykoure.ranks;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import sykoure.ranks.commands.CheckRanks;

@Plugin(
        id = "ranks",
        name = "Ranks",
        description = "Allowing the server to create new ranks ",
        authors = {
                "Sykoure"
        }
)
public class Ranks {

        //AdvancementProgress
    //Player player = new Player();


    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {

        CommandManager commandManager = Sponge.getCommandManager();
        /**CommandSpec gymWarpSpec = CommandSpec.builder()
                .description(Text.of("Warps you to the specified gym location."))
                .permission("agp.command.gymwarp")
                .executor(new Help())
                .arguments(
                        GenericArguments.onlyOne(GymCommandElement.gym()),
                        GenericArguments.onlyOne(GenericArguments.string(Text.of("location"))),
                        GenericArguments.optional(GenericArguments.seq(
                                GenericArguments.optional(GymArenaCommandElement.gymArena()),
                                GenericArguments.optional(GenericArguments.string(Text.of("arenaSubLocation")))
                        ))
                )
                .build();
        commandManager.register(this, gymWarpSpec, "rank");**/

        CommandSpec checkRanks = CommandSpec.builder()
                .description(Text.of("Checks your ranks"))
                .executor(new CheckRanks())
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.playerOrSource(Text.of("target")))
                )
                .build();
        commandManager.register(this, checkRanks, "checkranks", "cb", "ranks");

    }
}
