package HarpoonBuyer;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.wrappers.Tile;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: JamieThomas
 * Date: 09/02/13
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
public class Variables {
    public static ArrayList<Node> jobs = new ArrayList<Node>();

    public static Tile[] myTiles = new Tile[] { new Tile(3046, 3235, 0), new Tile(3041, 3235, 0), new Tile(3036, 3234, 0),
            new Tile(3031, 3234, 0), new Tile(3026, 3236, 0), new Tile(3026, 3231, 0),
            new Tile(3026, 3226, 0), new Tile(3026, 3221, 0), new Tile(3026, 3216, 0),
            new Tile(3022, 3217, 0), new Tile(3015, 3217, 0), new Tile(3013, 3220, 0),
            new Tile(3013, 3226, 0) };
}