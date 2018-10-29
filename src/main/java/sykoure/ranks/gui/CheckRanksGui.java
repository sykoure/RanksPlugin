package sykoure.ranks.gui;

import com.mcsimonflash.sponge.teslalibs.inventory.Layout;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;

import com.mcsimonflash.sponge.teslalibs.inventory.Element;

import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;
import sykoure.ranks.Ranks;

import static org.spongepowered.api.data.type.DyeColors.*;


public class CheckRanksGui {

    private static final Element REDGLASSELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.STAINED_GLASS_PANE)
            .add(Keys.DISPLAY_NAME, Text.of("TEST"))
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

        //TODO GERER LES ECHANGE BDD

        View view = View.builder()
                .archetype(InventoryArchetypes.ENCHANTING_TABLE)
                .property(InventoryTitle.of(Text.of("&8" + sender.getName() + "'s ranks")))
                .build(Ranks.getInstance().getContainer());

        view.open(sender);

        constructGUI(sender,view,0);
    }

    public static void constructGUI(Player player, View view, int page) {
        int line = 1;
        int column = 1;


        //Number of pages
        int maxPages = 2;

        if (page < 0)
            page = 0;
        if (page >= maxPages)
            page = maxPages;

        //Render the new layout
        Layout layout = Layout.builder()
                .row(REDGLASSELEMENT, 0)
                .row(REDGLASSELEMENT, 1)
                .row(BLACKGLASSELEMENT, 2)
                .row(BLACKGLASSELEMENT, 3)
                .row(WHITEGLASSELEMENT, 4)
                .row(WHITEGLASSELEMENT, 5)
                .build();
        view.define(layout);

        }
    }
}
