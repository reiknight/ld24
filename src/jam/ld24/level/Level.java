package jam.ld24.level;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.entities.EntityManager;
import jam.ld24.entities.Enemy;
import jam.ld24.entities.Wall;
import jam.ld24.entities.Zombie;
import jam.ld24.game.C;
import jam.ld24.game.editor.EditorException;
import jam.ld24.game.editor.EditorManager;
import jam.ld24.tiles.CollisionMap;
import jam.ld24.tiles.TileMap;
import jam.ld24.tiles.TileSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author InfiniteDog
 */
public class Level {
    private String name;
    
    private TileMap tm;
    private CollisionMap cm;
    
    private EntityManager em = EntityManager.getInstance();
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    
    private boolean cleared = false;
    private boolean dead = false;
    
    private int levelTime;
    
    private int elapsedTime = 0;

    public Level(String name) {
        this.name = name;
        restart();
    }
    
    private void readEntities() {
        File dir = new File("resources/maps");
        File f = new File(dir,name+".entity");
        int nEntities = 0;
        int x, y;
        
        em.setGameState(this.name);
        
        try {
            Scanner sc = new Scanner(f);
            
            if(sc.hasNextInt()) {
                nEntities = sc.nextInt();
            }

            for(int i = 0; i < nEntities; i++) {
                x = 0;
                y = 0;
                if(sc.hasNextInt()) {
                    x = sc.nextInt();
                }
                if(sc.hasNextInt()) {
                    y = sc.nextInt();
                }
                
                if(i == 0) {
                    Zombie avatar = new Zombie(x, y);
                    avatar.setActive(true);
                    avatar.setCollisionMap(this.cm);
                    em.addEntity(avatar.getName(), avatar);
                }
                else {
                    Enemy enemy = new Enemy(x, y);
                    enemy.setCollisionMap(this.cm);
                    em.addEntity(enemy.getName(), enemy);
                }
            }
        } catch(FileNotFoundException fnfe) {
            System.out.println("Error loading the map");
        }
    }
    
    private void loadWalls() {
        int[][] map = this.cm.getMap();
        
        // Init walls
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 1) {
                    Wall wall = new Wall();
                    wall.setPosition(new Vector2f(j * (Integer) C.Logic.TILE_SIZE.data, 
                            i * (Integer) C.Logic.TILE_SIZE.data));
                    em.addEntity(wall.getName(), wall);
                }
            }
        }
    }

    public void render(GameContainer gc, Graphics g) {
        em.setGameState(this.name);
        // Render TileMap
        tm.render();
        // Render all entities
        em.render(gc, g);
        
        g.setColor(Color.white);
        g.drawString("Time: " + levelTime / 1000, 700, 10);
        
        if(elapsedTime > 0) {
            if(!dead) {
                g.drawString("Stage cleared!", 275, 225);
            }
            else {
                g.drawString("You are...dead?", 275, 225);
            }
        }
    }

    public void update(GameContainer gc, int delta) {
        em.setGameState(this.name);
        
        if(elapsedTime <= 0) {
            // Update all entities
            em.update(gc, delta);
        }

        // Check victory/defeat conditions
        ArrayList<Entity> enemies = em.getEntityGroup(C.Groups.ENEMIES.name);
        
        if(enemies.isEmpty()) {
            elapsedTime += delta;
            if(elapsedTime > (Integer) C.Logic.NEXT_LEVEL_TIME.data) {
                cleared = true;
            }
        }
        
        ArrayList<Entity> zombies = em.getEntityGroup(C.Groups.ZOMBIES.name);
        for(int i = 0, l = zombies.size(); i < l; i ++) {
            Zombie zombie = (Zombie) zombies.get(i);
            if(zombie.isActive() && !zombie.isAlive()) {
                dead = true;
            }
        }
        
        if(dead || levelTime <= 0) {
            dead = true;
            elapsedTime += delta;
            if(elapsedTime > (Integer) C.Logic.NEXT_LEVEL_TIME.data) {
                restart();
            }
        }
        else {                    
            levelTime -= delta;
        }
        
    }

    public boolean isCleared() {
        return cleared;
    }

    void clear() {
        em.setGameState(this.name);
        em.clear();
    }

    private void restart() {
        clear();
        TileSet ts = new TileSet(C.Textures.TILE_SET.name, C.Textures.TILE_SET.path, 
                (Integer)C.Logic.TILE_SIZE.data);
        
        int[][] map = null;
        try {
            map = EditorManager.getInstance().readMap(name);
        } catch (EditorException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        this.tm = new TileMap(map, ts);
        this.cm = new CollisionMap(map);
        
        readEntities();
        loadWalls();
        
        cleared = false;
        dead = false;
               
        elapsedTime = 0;
        // TODO: load time from file?
        levelTime = 60000;
    }

}
