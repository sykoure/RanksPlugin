package sykoure.ranks;

import com.google.inject.Inject;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandManager;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import sykoure.ranks.commands.AdvancementProgress;
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


    private static Ranks instance;
    //AdvancementProgress
    //Player player = new Player();


    @Inject
    private PluginContainer container;


    //Useful to create views
    public static Ranks getInstance() {
        return instance;
    }

    public PluginContainer getContainer() {
        return this.container;
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;

        CommandManager commandManager = Sponge.getCommandManager();

        CommandSpec pokedex = CommandSpec.builder()
                .description(Text.of("Warps you to the specified gym location."))
                .executor(new AdvancementProgress())
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.playerOrSource(Text.of("target")))
                )
                .build();
        commandManager.register(this, pokedex, "pokedex");

        CommandSpec checkRanks = CommandSpec.builder()
                .description(Text.of("Checks your ranks"))
                .executor(new CheckRanks())
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.playerOrSource(Text.of("target")))
                )
                .build();
        commandManager.register(this, checkRanks, "checkranks", "ranks");

    }
}
