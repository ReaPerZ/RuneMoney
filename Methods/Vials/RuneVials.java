package Methods.Vials;

import Variables.Vials;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class RuneVials implements Task, Condition {

    public static String currentTask = null;
    public static int money = 0;
    public int vialPrice = 2;
    public int waterPrice = 19;
    public int invyPrice = waterPrice - vialPrice;

    public boolean validate() {
        return true;
    }

    public void run() {
        SceneObject fountain = SceneEntities.getNearest(new Filter<SceneObject>() {
            @Override
            public boolean accept(SceneObject sceneObject) {
                return sceneObject.getId() == Vials.x.FOUNTAIN.getId();
            }
        });

        Item[] emptyVial = Inventory.getItems(new Filter<Item>() {
            @Override
            public boolean accept(Item item) {
                return item.getId() == Vials.x.EMPTYVIAL.getId();
            }
        });

        Item[] fullVial = Inventory.getItems(new Filter<Item>() {
            @Override
            public boolean accept(Item item) {
                return item.getId() == Vials.x.VIALOFWATER.getId();
            }
        });

        NPC banker = NPCs.getNearest(new Filter<NPC>() {
            @Override
            public boolean accept(NPC npc) {
                return npc.getId() == Vials.x.BANK.getId();
            }
        });

        Tile fountainTile = new Tile(3162, 3490, 0);
        Tile bankTile = new Tile(3151, 3481, 0);

        if (Players.getLocal().getAnimation() == -1) {
            if (hasVials()) {
                if (fountain.isOnScreen()) {
                    Time.sleep(500, 1000);
                    Inventory.selectItem(Vials.x.EMPTYVIAL.getId());
                    currentTask = "Selecting Vial";
                    Time.sleep(250, 500);
                    if (Inventory.getSelectedItem().getId() == Vials.x.EMPTYVIAL.getId()) {
                        fountain.click(true);
                        currentTask = "Clicking Fountain";
                        Time.sleep(250, 500);
                    }
                }
                if (!fountain.isOnScreen()) {
                    Walking.walk(fountainTile);
                    Time.sleep(250, 500);
                }
            }
            if (!hasVials()) {
                if (!Bank.isOpen()) {
                    if (banker.isOnScreen()) { banker.interact("Bank"); Time.sleep(250, 1000); currentTask = "Using Banker"; }
                    if (!banker.isOnScreen()) { Walking.walk(bankTile); Time.sleep(250, 1000); currentTask = "Walking to Banker"; }
                }
                if (Bank.isOpen()) {
                    if (hasFull()) { Bank.depositInventory(); Time.sleep(250, 1000); currentTask = "Depositing Inventory"; money += invyPrice; }
                    if (emptyInvy()) { Bank.withdraw(Vials.x.EMPTYVIAL.getId(), 0); Time.sleep(250, 1000); currentTask = "Withdrawing Vials"; }
                    if (hasVials()) { Walking.walk(fountainTile); Time.sleep(250, 1000); currentTask = "Walking to Fountain"; }
                }
            }
        }
    }

    public boolean hasVials() {
        if (Inventory.getCount(Vials.x.EMPTYVIAL.getId()) > 0) {return true;}
        return false;
    }

    public boolean hasFull() {
        if (Inventory.getCount(Vials.x.VIALOFWATER.getId()) == 28) { return true; }
        return false;
    }

    public boolean emptyInvy() {
        if (Inventory.getCount() == 0) { return true; }
        return false;
    }

}
