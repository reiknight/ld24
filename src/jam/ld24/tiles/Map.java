package jam.ld24.tiles;

import jam.ld24.game.C;

/**
 *
 * @author InfiniteDog
 */
public class Map {
    protected int tileSize = (Integer) C.Logic.TILE_SIZE.data;
    protected int[][] map;

    public Map(int[][] map) {
        this.map = map;
    }
    
    /**
    * 
    * @param x
    * @param y
    * @return -1 if there is no tile in the coordinate (x,y), id of the tile in other case
    */
    public int getId(int x, int y) {
        if(x >= map.length || x < 0 || y >= map[x].length || y <0) {
            return -1;
        } else {
            return map[x][y];
        }
    }
    
    public int[][] getMap() {
        return map;
    }
}
