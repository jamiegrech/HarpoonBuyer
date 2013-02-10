package HarpoonBuyer.Nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;

/**
 * Created with IntelliJ IDEA.
 * User: JamieThomas
 * Date: 09/02/13
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class Purchase extends Node {

    public final Area area = new Area(new Tile[]{
            new Tile(3010, 3230, 0), new Tile(3010, 3220, 0),
            new Tile(3016, 3223, 0), new Tile(3017, 3229, 0)
    });

    @Override
    public boolean activate() {
        System.out.println(this.getClass().getName().toString());
        if (Widgets.get(11).validate()) {
            return false;
        }

        NPC garret = NPCs.getNearest(558);
        Item harpoon = Inventory.getItem(311);
        if (harpoon != null) {
            return false;
        }
        if (Widgets.get(1265).validate()) {
            return true;
        }
        if (garret != null) {
            if (garret.getLocation().canReach()) {
                if (garret.isOnScreen()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getName().toString() + " executing");
        if (Widgets.get(1265).validate()) {
            if (Widgets.get(1265).getChild(39).getText().contains("Select an item")) {
                Widgets.get(1265, 20).getChild(3).click(true);
                sleep(1000, 3000);
            } else {
                if (Widgets.get(1265, 72).click(true)) {
                    sleep(1000, 3000);
                    Widgets.get(1265, 80).click(true);
                    sleep(1000, 3000);
                }
            }
        } else {
            NPC garret = NPCs.getNearest(558);
            if (garret != null) {
                if (garret.getLocation().canReach()) {
                    if (garret.isOnScreen()) {
                        if (garret.getLocation().distanceTo() > 3) {
                            Walking.walk(garret);
                            sleep(1000);
                        }
                        garret.interact("Trade");
                        sleep(1000, 3000);
                    }
                }
            }
        }
    }
}
