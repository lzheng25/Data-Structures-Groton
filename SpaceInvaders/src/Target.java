import objectdraw.Drawable2DInterface;
import objectdraw.*;
//import objectdraw.FilledRect;
public interface Target {
    public void kill(FilledRect b);
    public boolean checkAlive();
    public Drawable2DInterface getBody();
    public boolean invincibilityStatus();
    //public boolean checkImpact(FilledRect bullet);
}
