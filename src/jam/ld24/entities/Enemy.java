package jam.ld24.entities;

import infinitedog.frisky.entities.Sprite;
import jam.ld24.game.C;

/**
 *
 * @author InfiniteDog
 */
public abstract class Enemy extends Sprite {
    private static int id = 0;
    private boolean alive;
    private boolean active;

    public Enemy() {
        name =  C.Entities.ENEMY.name + id++;
        group = C.Groups.ENEMIES.name;
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
