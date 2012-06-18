package Methods.DragonScale;

import Variables.Scales;
import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.Item;

public class RuneScales implements Task, Condition {

    Item[] item = Inventory.getItems(new Filter<Item>() {
        @Override
        public boolean accept(Item item) {
            return item.getId() == Scales.x.DRAGONSCALE.getId();
        }
    });

    public boolean validate() {
        return true;
    }

    public void run() {
        if (!Bank.isOpen()) {
            if (Inventory.getCount(Scales.x.DRAGONSCALE.getId()) > 0) {
                if (Players.getLocal().getAnimation() == -1) {
                    if (Inventory.getSelectedItem().getId() != Scales.x.DRAGONSCALE.getId()){
                        Inventory.selectItem(Scales.x.DRAGONSCALE.getId());
                        Time.sleep(Random.nextInt(250, 500));
                    } else if (Inventory.getSelectedItem().getId() == Scales.x.DRAGONSCALE.getId()) {
                        Inventory.getItem(Scales.x.PASTLEANDMORTAR.getId()).getWidgetChild().interact("Use");
                        Time.sleep(250, 500);
                    }
                } else {
                    Time.sleep(1000, 2500);
                }
            } else {
                Bank.open();
                Time.sleep(250, 500);
            }
        } else if (Bank.isOpen()) {
            if (Inventory.getCount(Scales.x.CRUSHEDSCALE.getId()) > 0) {
                Bank.depositInventory();
                Time.sleep(250, 500);
            } else if (Inventory.getCount(Scales.x.DRAGONSCALE.getId()) > 0) {
                Bank.close();
                Time.sleep(250, 500);
            } else if ((Inventory.getCount(Scales.x.DRAGONSCALE.getId()) == 0) &&
                    (Inventory.getCount(Scales.x.CRUSHEDSCALE.getId()) == 0)) {
                Bank.withdraw(Scales.x.DRAGONSCALE.getId(), 28);
                Time.sleep(250, 500);
            }
        }
    }

}
