package jam.ld24.level;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author InfiniteDog
 */
public class LevelManager {
    //Static instance from LevelManager
    private static LevelManager lm;
    private ArrayList<Level> levels = new ArrayList<Level>();
    private int loadedLevel = -1;
    
    //Private constructor
    private LevelManager() {
        
    }
    
    //Getter
    public static LevelManager getInstance() {
        if(lm == null) {
            lm = new LevelManager();
        }
        return lm;
    }
    
    public void addLevel(Level level) {
        levels.add(level);
    }
    
    public void loadLevel(int id) {
        loadedLevel = id;
    }
    
    public void loadNextLevel() {
        loadedLevel++;
    }
    
    public void render(GameContainer gc, Graphics g) {
        levels.get(loadedLevel).render(gc, g);
    }

    public void update(GameContainer gc, int delta) {
        levels.get(loadedLevel).update(gc, delta);
    }
}
