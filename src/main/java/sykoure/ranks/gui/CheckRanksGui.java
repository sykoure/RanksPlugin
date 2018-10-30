package sykoure.ranks.gui;

import com.mcsimonflash.sponge.teslalibs.inventory.Action;
import com.mcsimonflash.sponge.teslalibs.inventory.Element;
import com.mcsimonflash.sponge.teslalibs.inventory.Layout;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import sykoure.ranks.Ranks;

import java.util.function.Consumer;

import static org.spongepowered.api.data.type.DyeColors.*;


public class CheckRanksGui {

    private static final Element REDGLASSELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.STAINED_GLASS_PANE)
            .add(Keys.DISPLAY_NAME, Text.of(""))
            .add(Keys.DYE_COLOR, RED).build());

    private static final Element BLACKGLASSELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.STAINED_GLASS_PANE)
            .add(Keys.DISPLAY_NAME, Text.EMPTY)
            .add(Keys.DYE_COLOR, BLACK).build());

    private static final Element WHITEGLASSELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.STAINED_GLASS_PANE)
            .add(Keys.DISPLAY_NAME, Text.EMPTY)
            .add(Keys.DYE_COLOR, WHITE).build());

    private static final Element SHRUBELEMENT = Element.of(ItemStack.builder()
            .itemType(ItemTypes.TALLGRASS)
            .add(Keys.DISPLAY_NAME, Text.of("Alca Torda Rocks"))
            .build());

    public static void openCheckRanksGUI(Player sender) {
        View view = View.builder()
                .archetype(InventoryArchetypes.DOUBLE_CHEST)
                .property(InventoryTitle.of(Text.of(sender.getName() + "'s ranks")))
                .build(Ranks.getInstance().getContainer());

        view.open(sender);

        constructGUI(sender, view, 0);
    }

    public static void constructGUI(Player player, View view, int page) {


        //Number of pages
        int maxPages = 2;

        if (page < 0)
            page = 0;
        if (page >= maxPages)
            page = maxPages;

        //Render the new layout
        Layout layout;
        if (page == 0) {
            layout = Layout.builder()
                    .row(REDGLASSELEMENT, 0)
                    .row(REDGLASSELEMENT, 1)
                    .row(BLACKGLASSELEMENT, 2)
                    .row(BLACKGLASSELEMENT, 3)
                    .row(WHITEGLASSELEMENT, 4)
                    .row(WHITEGLASSELEMENT, 5)
                    .set(SHRUBELEMENT, 22)
                    .set(SHRUBELEMENT, 31)
                    .build();
        } else {
            layout = Layout.builder()
                    .row(REDGLASSELEMENT, 0)
                    .row(REDGLASSELEMENT, 1)
                    .row(BLACKGLASSELEMENT, 2)
                    .row(BLACKGLASSELEMENT, 3)
                    .row(WHITEGLASSELEMENT, 4)
                    .row(WHITEGLASSELEMENT, 5)
                    .build();
        }
        view.define(layout);

        //Next / Prev
        int finalPage = page;
        ItemStack nextStack = ItemStack.of(Sponge.getRegistry().getType(ItemType.class, "pixelmon:trade_holder_right").get(), 1);
        nextStack.offer(Keys.DISPLAY_NAME, Text.of("Next"));
        Consumer<Action.Click> nextAction = click -> Task.builder().execute(task -> constructGUI(player, view, finalPage + 1)).submit(Ranks.getInstance());

        ItemStack prevStack = ItemStack.of(Sponge.getRegistry().getType(ItemType.class, "pixelmon:trade_holder_left").get(), 1);
        prevStack.offer(Keys.DISPLAY_NAME, Text.of("Previous"));
        Consumer<Action.Click> prevAction = click -> Task.builder().execute(task -> constructGUI(player, view, finalPage - 1)).submit(Ranks.getInstance());

        ItemStack backStack = ItemStack.of(Sponge.getRegistry().getType(ItemType.class, "pixelmon:trade_monitor").get(), 1);
        backStack.offer(Keys.DISPLAY_NAME, Text.EMPTY);

        Element prev = Element.of(prevStack, prevAction);
        Element next = Element.of(nextStack, nextAction);
        Element mehh = Element.of(backStack);

        view.setElement(48, prev);
        view.setElement(49, mehh);
        view.setElement(50, next);

    }
}

