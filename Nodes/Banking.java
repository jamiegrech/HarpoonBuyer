package HarpoonBuyer.Nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;

/**
 * Created with IntelliJ IDEA.
 * User: JamieThomas
 * Date: 09/02/13
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public class Banking extends Node {
    @Override
    public boolean activate() {
        if (Widgets.get(11).validate()) {
            return true;
        }
        if (!Inventory.contains(new int[] {311})) {
            return false;
        }
        System.out.println(this.getClass().getName().toString());


        SceneObject bank = SceneEntities.getNearest(36788);
        if (bank != null) {
            if (bank.isOnScreen()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute() {
        System.out.println(this.getClass().getName().toString() + " executing");
        if (!Widgets.get(11).validate()) {
            SceneObject bank = SceneEntities.getNearest(36788);
            if (bank != null) {
                if (bank.isOnScreen()) {
                    bank.interact("Deposit");
                    sleep(1000, 3000);
                }

            }
        } else {
            if (Widgets.get(11).validate()) {
                if (Widgets.get(11).getChild(19).click(true)) {
                    sleep(1000, 3000);
                    Widgets.get(11).getChild(15).click(true);
                }

            }


        }
    }
}