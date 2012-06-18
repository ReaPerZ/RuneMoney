package Methods.Chocolate;

import Variables.Chocolate;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class RuneChocolate implements Task, Condition {
    public boolean validate() {
        return true;
    }

    public void run() {

        Item[] chocolate = Inventory.getItems(new Filter<Item>() {
            @Override
            public boolean accept(Item item) {
                return item.getId() == Chocolate.x.CHOCOLATE.getId();
            }
        });

        if (Players.getLocal().getAnimation() == -1) {
            if (hasChocolate()) {
            }
        }
    }


    public boolean hasChocolate() {
        if (Inventory.getCount(Chocolate.x.CHOCOLATE.getId()) > 0) { return true; }
        return false;
    }

    public boolean hasCrushed() {
        if (Inventory.getCount(Chocolate.x.DUST.getId()) > 0) { return true; }
        return false;
    }
}
