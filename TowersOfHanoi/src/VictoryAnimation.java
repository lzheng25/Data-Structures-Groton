import java.awt.Color;
import java.util.Stack;
import objectdraw.*;

public class VictoryAnimation extends ActiveObject {

    private Disk[] disks;
    // best, close, worst
    private String animation;

    public VictoryAnimation(Disk[] disks, String animation) {
        this.disks = disks;
        this.animation = animation;
        start();
    }

    public void run() {
        for (Disk d : disks) {
            switch (animation) {
                case "best":
                    d.setColor(new Color(255, 255, 0));
                    break;
                case "close":
                    d.setColor(new Color(255, 0, 255));
                    break;
                case "worst":
                    d.setColor(new Color(255, 0, 0));
                    break;
            }
            // pause 0.5 seconds
            pause(500);
        }

    }

}
