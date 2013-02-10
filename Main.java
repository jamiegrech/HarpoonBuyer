package HarpoonBuyer;

import HarpoonBuyer.Nodes.Banking;
import HarpoonBuyer.Nodes.Purchase;
import HarpoonBuyer.Nodes.WalkBank;
import HarpoonBuyer.Nodes.WalkShop;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.bot.Context;
import org.powerbot.game.client.Client;

import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

@Manifest(authors = {"JamieGrech"}, name = "HarpoonBuyer", description = "Buys Harpoons.")
public class Main extends ActiveScript implements PaintListener, MessageListener {

    private Tree jobContainer = null;

    static Client client;

    private long startTime;
    private int counter;
    private int counterHour;

    @Override
    public void onStart() {
        Mouse.setSpeed(Mouse.Speed.VERY_FAST);
        startTime = System.currentTimeMillis();
    }


    @Override
    public int loop() {

        /*Checks if map is loaded*/
        if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
            return 2500;
        }

        if (client != Context.client()) {
            WidgetCache.purge();
            Context.get().getEventManager().addListener(this);
            client = Context.client();
        }
        if (Game.getClientState() == 11) {
            if (jobContainer != null) {
                final Node job = jobContainer.state();
                if (job != null) {
                    jobContainer.set(job);
                    getContainer().submit(job);
                    job.join();
                }
            } else {
                Variables.jobs.add(new Purchase());
                Variables.jobs.add(new WalkBank());
                Variables.jobs.add(new WalkShop());
                Variables.jobs.add(new Banking());
            }
            jobContainer = new Tree(
                    Variables.jobs.toArray(new Node[Variables.jobs
                            .size()]));
        }
        return 100;
    }

    //START: Code generated using Enfilade's Easel
    private final Color color1 = new Color(153, 153, 153, 129);
    private final Color color2 = new Color(0, 0, 0);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("Arial", 0, 15);
    private final Font font2 = new Font("Arial", 1, 18);

    @Override
    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        counterHour = (int) ((counter) * 3600000D / (System.currentTimeMillis() - startTime));
        long millis = System.currentTimeMillis() - startTime;
        long hours = millis / (1000 * 60 * 60);
        millis -= hours * (1000 * 60 * 60);
        long minutes = millis / (1000 * 60);
        millis -= minutes * (1000 * 60);
        long seconds = millis / 1000;

        g.setColor(color1);
        g.fillRoundRect(548, 264, 188, 118, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(548, 264, 188, 118, 16, 16);
        g.setFont(font1);
        g.drawString("Time Running: " + hours + ":" + minutes + ":" + seconds, 552, 313);
        g.drawString("Harpoon's Baught: " + counter, 552, 340);
        g.drawString("Harpoon's Baught P/H: "+ counterHour, 552, 366);
        g.setFont(font2);
        g.drawString("Harpoon Buyer", 552, 287);
    }
    //END: Code generated using Enfilade's Easel

    @Override
    public void messageReceived(MessageEvent mess) {
        String message = mess.getMessage();
        if (message.contains("1,260 coins")) {
            counter += 28;
        }
    }
}
