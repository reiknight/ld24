package jam.ld24.level;

import jam.ld24.entities.Enemy;
import jam.ld24.entities.Zombie;
import jam.ld24.game.C;
import jam.ld24.game.editor.EditorManager;
import jam.ld24.tiles.TileMap;
import jam.ld24.tiles.TileSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author InfiniteDog
 */
public class Level {
    private String name;
    
    private TileMap tm;
    //private CollisionMap cm;
    
    private Enemy[] enemies;
    private Zombie[] zombies;

    public Level(String name) {
        this.name = name;
        //tm = new TileMap(EditorManager.getInstance().readMap(name), new TileSet(C.Textures., name, tileSize));
        readEntities();
    }
    
    private void readEntities() {
        File dir = new File("resources/maps");
        File f = new File(dir,name+".entity");
        try {
            Scanner sc = new Scanner(f);
            if(sc.hasNextInt()) {
                enemies = new Enemy[sc.nextInt()];
            }
            int x = 0, y = 0;
            if (sc.hasNextInt()) {
                x = sc.nextInt();
            }
            if (sc.hasNextInt()) {
                y = sc.nextInt();
            }
            for(int i = 0; i < enemies.length; i++) {
                x = 0;
                y = 0;
                if(sc.hasNextInt()) {
                    x = sc.nextInt();
                }
                if(sc.hasNextInt()) {
                    y = sc.nextInt();
                }
                enemies[i] = new Enemy(x, y);
            }
        } catch(FileNotFoundException fnfe) {
            System.out.println("Error loading the map");
        }
    }

}
