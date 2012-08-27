package jam.ld24.entities;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.entities.Sprite;
import jam.ld24.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author InfiniteDog
 */
public class Wall extends Entity {
    private static int id = 0;
    protected float speed = (Float) C.Logic.ZOMBIE_SPEED.data;
        
    public Wall() {
        this.name = C.Entities.WALL.name + id++;
        group = C.Groups.WALLS.name;
    }
}
