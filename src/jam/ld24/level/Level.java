package jam.ld24.level;

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
import java.util.Scanner;
import java.util.logging.Logger;
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

    public Level(String name) {
        TileSet ts = new TileSet(C.Textures.TILE_SET.name, C.Textures.TILE_SET.path, 
                (Integer)C.Logic.TILE_SIZE.data);
        
        int[][] map = null;
        try {
            map = EditorManager.getInstance().readMap(name);
        } catch (EditorException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        this.name = name;
        this.tm = new TileMap(map, ts);
        this.cm = new CollisionMap(map);
        
        readEntities();
        loadWalls();
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
                    em.addEntity(avatar.getName(), avatar);
                }
                else {
                    Enemy enemy = new Enemy(x, y);
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
    }

    public void update(GameContainer gc, int delta) {
        em.setGameState(this.name);
        // Update all entities
        em.update(gc, delta);
    }

}
