package HarpoonBuyer.Nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: JamieThomas
 * Date: 09/02/13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class WalkShop extends Node {

    @Override
    public boolean activate() {
        System.out.println(this.getClass().getName().toString());
        if (Widgets.get(11).validate()) {
            return false;
        }
        NPC garret = NPCs.getNearest(558);
        Item harpoon = Inventory.getItem(311);
        if (garret != null) {
            if (garret.isOnScreen()) {
                if (garret.getLocation().canReach()) {
                    if (garret.getLocation().distanceTo() < 4) {
                        return false;
                    }
                }
            }
        }
        if (harpoon == null) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getName().toString() + " executing");
        SceneObject door = SceneEntities.getAt(3013, 3219);
        if (door != null) {
            if (door.isOnScreen()) {
                door.interact("Open");
                sleep(1000, 3000);
            }

        }
        Walking.walk(new Tile(3013, 3219, 0));
        sleep(1000, 2000);
    }
}

