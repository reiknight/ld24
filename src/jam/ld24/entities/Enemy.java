package jam.ld24.entities;

import infinitedog.frisky.entities.Sprite;
import jam.ld24.game.C;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author InfiniteDog
 */
public class Enemy extends Sprite {
    private static int id = 0;
    private boolean alive;
    private boolean active;
    private final Vector2f direction;
    private Polygon vision = new Polygon();

    public Enemy() {
        super(C.Textures.ENEMY.name);
        name =  C.Entities.ENEMY.name + id++;
        group = C.Groups.ENEMIES.name;
        alive = true;
        direction = new Vector2f(0, 1);
    }
    
    public Enemy(int x, int y) {
        this();
        this.setPosition(new Vector2f(x * (Integer) C.Logic.TILE_SIZE.data,
                y * (Integer) C.Logic.TILE_SIZE.data));
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

    void die() {
        setAlive(false);
    }
 
    @Override
    public void render(GameContainer gc, Graphics g) {
        g.setColor(Color.yellow);
        //g.drawString(name, posx, posy);
        g.draw(vision);
        
        super.render(gc, g);
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
    }
    
    public void setPosition(Vector2f position) {
        super.setPosition(position);
        
        //Update vision
        float posx = getCenter().x;
        float posy = getCenter().y;
        vision.addPoint(posx, posy);
        if(direction.y == 0) {
            vision.addPoint(posx + 100 * direction.x, posy - 75);
            vision.addPoint(posx + 100 * direction.x, posy + 75);
        }
        else {
            vision.addPoint(posx + 75, posy + 100 * direction.y);
            vision.addPoint(posx - 75, posy + 100 * direction.y);
        }
    }
    
    public Polygon getVision() {
        return vision;
    }
}
