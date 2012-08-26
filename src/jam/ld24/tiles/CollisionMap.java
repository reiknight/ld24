package jam.ld24.tiles;

import infinitedog.frisky.entities.Entity;
import infinitedog.frisky.physics.PhysicsManager;
import jam.ld24.game.C;

/**
 *
 * @author InfiniteDog
 */
public class CollisionMap extends Map {
    private PhysicsManager pm = PhysicsManager.getInstance();
    
    public CollisionMap(int[][] map) {
        super(map);
    }
    
    public boolean collidesWith(Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        
        // Check quad's top left
        int j1 = (int) (x / tileSize);
        int i1 = (int) (y / tileSize);
        // Check quad's bottom right
        int j2 = (int) ((x + entity.getWidth()) / tileSize);
        int i2 = (int) ((y + entity.getHeight()) / tileSize);
        
        return  map[i1][j1] == 1 || map[i2][j2] == 1;
    }
}