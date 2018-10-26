package sykoure.ranks.gui;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;

import com.mcsimonflash.sponge.teslalibs.inventory.Element;

import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import static org.spongepowered.api.data.type.DyeColors.*;


public class CheckRanksGui {

    private static final Element REDGLASSELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.STAINED_GLASS_PANE)
            .add(Keys.DISPLAY_NAME, Text.EMPTY)
            .add(Keys.DYE_COLOR, RED).build());

    private static final Element BLACKGLASSELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.STAINED_GLASS_PANE)
            .add(Keys.DISPLAY_NAME, Text.EMPTY)
            .add(Keys.DYE_COLOR, BLACK).build());

    private static final Element WHITEGLASSELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.STAINED_GLASS_PANE)
            .add(Keys.DISPLAY_NAME, Text.EMPTY)
            .add(Keys.DYE_COLOR, WHITE).build());

    public static void openCheckRanksGUI(Player sender) {


    }
}
