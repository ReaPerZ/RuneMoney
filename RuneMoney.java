import java.awt.*;

import Methods.Chocolate.RuneChocolate;
import Methods.DragonScale.RuneScales;
import Methods.Vials.RuneVials;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(name = "RuneMoney", authors = {"ReaPerZ"}, description = "Your one stop shop for RSGP", version = 1)
public class RuneMoney extends ActiveScript implements PaintListener {

    public void setup() {
        startTime = System.currentTimeMillis();
        RuneVials runeVials = new RuneVials();
        Strategy vialsAction = new Strategy(runeVials, runeVials);
        provide(vialsAction);
    }

    public String bot = "RuneVials";
    public long startTime;

    public String getTask() {
        if (bot.equalsIgnoreCase("RuneVials")) { return RuneVials.currentTask; }
        return null;
    }

    public int getMoney() {
        if (bot.equalsIgnoreCase("RuneVials")) { return RuneVials.money; }
        return 0;
    }

    private final Color color1 = new Color(51, 51, 51);
    private final Color color2 = new Color(0, 0, 0);
    private final Color color3 = new Color(255, 255, 255);

    private final BasicStroke stroke1 = new BasicStroke(1);

    private final Font font1 = new Font("JasmineUPC", 0, 32);

    public void onRepaint(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        g.setColor(color1);
        g.fillRoundRect(164, 10, 147, 36, 16, 16);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRoundRect(164, 10, 147, 36, 16, 16);
        g.setFont(font1);
        g.setColor(color3);
        g.drawString("RuneMoney", 186, 36);
        g.setColor(color1);
        long timeNow = System.currentTimeMillis();
        g.drawString("Time Running: " + (int)(timeNow-startTime)/3600000 + ":" + (int)((timeNow-startTime)-(((timeNow-startTime)/3600000)*3600000))/60000 + ":" + (int)((timeNow-startTime)-(((timeNow-startTime)/3600000)*3600000)-(((timeNow-startTime)-(((timeNow-startTime)/3600000)*3600000))/60000)*60000)/1000, 228, 335);
        g.drawString("Current Task: " + getTask(), 204, 369);
        g.drawString("Money Gained: " + getMoney(), 190, 410);
        g.drawString("Current Method: " + bot, 180, 390);
    }
}
