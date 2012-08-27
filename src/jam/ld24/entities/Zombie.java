package jam.ld24.entities;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.entities.EntityManager;
import infinitedog.frisky.entities.Sprite;
import infinitedog.frisky.events.EventManager;
import infinitedog.frisky.physics.PhysicsManager;
import infinitedog.frisky.sounds.SoundManager;
import jam.ld24.game.C;
import jam.ld24.tiles.CollisionMap;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Zombie extends Sprite {
    private static int id = 0;
    protected float speed = (Float) C.Logic.ZOMBIE_SPEED.data;
    private boolean alive;
    private boolean active;
    private boolean dead;
    private int zombieGroup;
    private CollisionMap cm;
    private EntityManager em = EntityManager.getInstance();
    private PhysicsManager pm = PhysicsManager.getInstance();
    private Vector2f direction;
        
    public Zombie() {
        super(C.Textures.ZOMBIE.name);
        name =  C.Entities.ZOMBIE.name + id++;
        group = C.Groups.ZOMBIES.name;
        zombieGroup = 1;
        alive = true;
        direction = new Vector2f(0, 1);
    }
    
    public Zombie(int x, int y) {
        this();
        this.setPosition(new Vector2f(x * (Integer) C.Logic.TILE_SIZE.data,
                y * (Integer) C.Logic.TILE_SIZE.data));
    }
    
     public Zombie(Enemy enemy) {
        this();
        this.setPosition(new Vector2f(enemy.getX(), enemy.getY()));
        enemy.die();
    }
        
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        
        EventManager evm = EventManager.getInstance();
        SoundManager sm = SoundManager.getInstance();
        float x = getX();
        float y = getY();
        float oldX = x;
        float oldY = y;
        float vx = 0;
        float vy = 0;
    
        // We are controlling the zombie
        if(isActive()) {
            // Player movement
            if(evm.isHappening(C.Events.MOVE_LEFT.name, gc)) {
                vx -= speed * delta;
                direction = new Vector2f(-1, 0);
            }else if(evm.isHappening(C.Events.MOVE_RIGHT.name, gc)) {
                vx += speed * delta;
                direction = new Vector2f(1, 0);
            } else if(evm.isHappening(C.Events.MOVE_UP.name, gc)) {
                vy -= speed * delta;
                direction = new Vector2f(0, -1);
            }else if(evm.isHappening(C.Events.MOVE_DOWN.name, gc)) {
                vy += speed * delta;
                direction = new Vector2f(0, 1);
            }
            
            ArrayList<Entity> enemies = em.getEntityGroup(C.Groups.ENEMIES.name);
            for(int i = 0; i < enemies.size(); i++) {
                if(pm.testCollisionPolygon(this, ((Enemy)enemies.get(i)).getVision())) {
                    setAlive(false);
                    return;
                }
            }
            
            // Player action
            if(evm.isHappening(C.Events.ACTION.name, gc)) {
                boolean human_bitten = false;
                
                // Bite
                for(int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = (Enemy) enemies.get(i);
                    if(pm.testCollisionsEntity(this, enemy)) {
                        Zombie zombie = new Zombie(enemy);
                        zombie.setCollisionMap(this.cm);
                        em.addFutureEntity(zombie.name,zombie);
                        em.removeEntity(enemy.getName());
                        
                        sm.playSound(C.Sounds.ZOMBIE_BITE.name);
                        human_bitten = true;
                    }
                }
                
                if(!human_bitten) {
                    // Growl
                    sm.playSound(C.Sounds.ZOMBIE_GROWL.name);
                }
            }

            if(!this.cm.collidesWith(this, vx, vy)) {
                x += vx;
                y += vy;
            }

            setPosition(new Vector2f(x, y));
        }
        else {
            ArrayList<Entity> zombies = em.getEntityGroup(C.Groups.ZOMBIES.name);
            for(int i = 0, l = zombies.size(); i < l; i++) {
                Zombie zombie = (Zombie) zombies.get(i);
                if(zombie.isActive()) {
                    Vector2f zombiePosition = new Vector2f(zombie.getX(), zombie.getY());
                    Vector2f zombieDirection = zombie.getDirection();
                    if(zombieDirection.y == 0) { // looking right or left
                        zombiePosition.x -= zombieDirection.x * 32;
                    }
                    else { // looking up or down
                        zombiePosition.y -= zombieDirection.y * 32;
                    }
                    this.setPosition(zombiePosition);
                    return;
                }
            }
        }

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

    public void setCollisionMap(CollisionMap collisionMap) {
        this.cm = collisionMap;
    }
    
    public Vector2f getDirection() {
        return direction;
    }
}
