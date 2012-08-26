package jam.ld24.tiles;

/**
 *
 * @author InfiniteDog
 */
public class TileMap {
    private int[][] map;
    private TileSet tileSet;

    public TileMap(int[][] map, TileSet tileSet) {
        this.map = map;
        this.tileSet = tileSet;
    }   
    
    public void render() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                tileSet.render(map[i][j], j, i);
            }
        }
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
    
    
}
