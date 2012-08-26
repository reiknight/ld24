package jam.ld24.entities;

import infinitedog.frisky.entities.Sprite;
import infinitedog.frisky.events.EventManager;
import jam.ld24.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Zombie extends Sprite {
    private static int id = 0;
    protected float speed = (Float) C.Logic.ZOMBIE_SPEED.data;
    private boolean alive;
    private boolean active;
    private int zombieGroup;
        
    public Zombie() {
        super(C.Textures.ZOMBIE.name);
        name =  C.Entities.ZOMBIE.name + id++;
        group = C.Groups.ZOMBIES.name;
        zombieGroup = 1;
    }
    
        
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        
        EventManager evm = EventManager.getInstance();
        float x = getX();
        float y = getY();
    
        //Player movement
        if(evm.isHappening(C.Events.MOVE_LEFT.name, gc) && isActive()) {
            x -= speed * delta;
        }else if(evm.isHappening(C.Events.MOVE_RIGHT.name, gc) && isActive()) {
            x += speed * delta;
        } else if(evm.isHappening(C.Events.MOVE_UP.name, gc) && isActive()) {
            y -= speed * delta;
        }else if(evm.isHappening(C.Events.MOVE_DOWN.name, gc) && isActive()) {
            y += speed * delta;
        }
        
        setPosition(new Vector2f(x, y));
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

    public int getZombieGroup() {
        return zombieGroup;
    }

    public void setZombieGroup(int zombieGroup) {
        this.zombieGroup = zombieGroup;
    }

}
