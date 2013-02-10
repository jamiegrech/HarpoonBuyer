package HarpoonBuyer.Nodes;

import HarpoonBuyer.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: JamieThomas
 * Date: 09/02/13
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class WalkBank extends Node {

    @Override
    public boolean activate() {
        System.out.println(this.getClass().getName().toString());
        if (Widgets.get(11).validate()) {
            return false;
        }
        Item harpoon = Inventory.getItem(311);
        SceneObject bank = SceneEntities.getNearest(36788);
        if (bank != null) {
            if (bank.isOnScreen()) {
                if (bank.getLocation().distanceTo() < 5) {
                    return false;
                }
            }
        }
        if (harpoon != null) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getName().toString() + " executing");
        if (Walking.newTilePath(Variables.myTiles).reverse().getEnd().canReach()) {
            if (Walking.newTilePath(Variables.myTiles).reverse().traverse()) {
                sleep(1000);
            }
        } else {
            SceneObject door = SceneEntities.getNearest(40108);
            if (door != null) {
                door.interact("Open");
                sleep(1000, 3000);
            }
        }

    }
}
